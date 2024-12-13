package io.foxcapades.mc.bukkit.thimble.types.bukkit

import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.parse.ThimbleDeserializationException
import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.read.asType
import io.foxcapades.mc.bukkit.thimble.types.ComplexTypeDefinition
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

import java.util.Optional

import org.bukkit.Material
import org.bukkit.inventory.meta.components.ToolComponent.ToolRule

// UNSAFE!!
import net.minecraft.core.HolderSet
import net.minecraft.world.item.component.Tool
import org.bukkit.craftbukkit.v1_21_R1.inventory.components.CraftToolComponent.CraftToolRule
import org.bukkit.craftbukkit.v1_21_R1.util.CraftMagicNumbers


@Suppress("UnstableApiUsage")
data object ToolRuleTypeDefinition : ComplexTypeDefinition<ToolRule> {
  override val currentVersion: Byte
    get() = 1

  override val typeIdentifier: String
    get() = "b:m:TR"

  override val actualType: Class<ToolRule>
    get() = ToolRule::class.java

  override fun serialize(value: ToolRule, writer: ValueWriter) {
    if (value.blocks.isNotEmpty())
      writer.writeComplex(value.blocks.map { it.key.toString() })
    else
      writer.writeNull()

    writer.writeFloatOrNull(value.speed)
    writer.writeBooleanOrNull(value.isCorrectForDrops)
  }

  override fun deserializerFor(version: Byte): ComplexDeserializer<out ToolRule> =
    object : ComplexDeserializer<ToolRule> {
      var blocks = emptyList<String>()
      var speed: Float? = null
      var correct: Boolean? = null

      override fun append(index: Int, value: ValueAccessor) {
        when (index) {
          0    -> if (!value.isNull) blocks = value.asComplex().asType()
          1    -> if (!value.isNull) speed = value.asFloat()
          2    -> if (!value.isNull) correct = value.asBoolean()
          else -> throw ThimbleDeserializationException("invalid value index: $index")
        }
      }

      override fun build(): ToolRule = CraftToolRule(Tool.a(
        HolderSet.a(blocks.map { CraftMagicNumbers.getBlock(Material.getMaterial(it)).s() }),
        Optional.ofNullable(speed),
        Optional.ofNullable(correct),
      ))
    }
}
