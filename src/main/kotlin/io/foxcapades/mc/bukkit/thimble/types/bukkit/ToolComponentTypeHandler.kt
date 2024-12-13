package io.foxcapades.mc.bukkit.thimble.types.bukkit

import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.parse.ThimbleDeserializationException
import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.read.asType
import io.foxcapades.mc.bukkit.thimble.types.ComplexTypeHandler
import io.foxcapades.mc.bukkit.thimble.util.B1
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

import org.bukkit.inventory.meta.components.ToolComponent
import org.bukkit.inventory.meta.components.ToolComponent.ToolRule

// UNSAFE!!
import net.minecraft.world.item.component.Tool
import org.bukkit.craftbukkit.v1_21_R1.inventory.components.CraftToolComponent
import org.bukkit.craftbukkit.v1_21_R1.inventory.components.CraftToolComponent.CraftToolRule

@Suppress("UnstableApiUsage")
data object ToolComponentTypeHandler : ComplexTypeHandler<ToolComponent> {
  override val typeIndicator: String
    get() = "b:m:TC"

  override val currentVersion: Byte
    get() = 1

  override val actualType: Class<ToolComponent>
    get() = ToolComponent::class.java

  override fun serialize(value: ToolComponent, writer: ValueWriter) {
    writer.writeFloat(value.defaultMiningSpeed)
    writer.writeInt(value.damagePerBlock)
    if (value.rules.isNotEmpty())
      writer.writeComplex(value.rules)
    else
      writer.writeInt(0)
  }

  override fun deserializerFor(version: Byte): ComplexDeserializer<out ToolComponent>? =
    when (version) {
      B1   -> ToolComponentDeserializerV1()
      else -> null
    }
}

@Suppress("UnstableApiUsage")
private class ToolComponentDeserializerV1 : ComplexDeserializer<ToolComponent> {
  /** Index `0` */
  var defaultMiningSpeed = 0f

  /** Index `1` */
  var damagePerBlock = 0

  /**
   * Index `2`
   *
   * `List<ToolRule>` or `0`
   */
  var rules = emptyList<ToolRule>()

  override fun append(index: Int, value: ValueAccessor) {
    when (index) {
      0    -> defaultMiningSpeed = value.asFloat()
      1    -> damagePerBlock = value.asInt()
      2    -> if (value.isComplex) rules = value.asComplex().asType()
      else -> throw ThimbleDeserializationException("invalid value index: $index")
    }
  }

  override fun build(): ToolComponent =
    CraftToolComponent(Tool(
      rules.map { (it as CraftToolRule).handle },
      defaultMiningSpeed,
      damagePerBlock
    ))
}

