package io.foxcapades.mc.bukkit.thimble.types.bukkit.block.banner

import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.ComplexTypeDefinition
import io.foxcapades.mc.bukkit.thimble.util.B1
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter
import org.bukkit.Color
import org.bukkit.DyeColor
import org.bukkit.NamespacedKey
import org.bukkit.Registry
import org.bukkit.block.banner.Pattern
import org.bukkit.block.banner.PatternType

open class PatternTypeDefinition : ComplexTypeDefinition<Pattern> {
  override val actualType     get() = Pattern::class.java
  override val typeIdentifier get() = "b:b:P"
  override val currentVersion get() = B1

  override fun serialize(value: Pattern, writer: ValueWriter) {
    writer.writeInt(value.color.color.asARGB())
    writer.writeKey(value.pattern.key)
  }

  override fun deserializerFor(version: Byte): ComplexDeserializer<out Pattern>? =
    when (version) {
      B1   -> PatternDeserializerV1()
      else -> null
    }
}

private class PatternDeserializerV1() : ComplexDeserializer<Pattern> {
  override val fieldCount: Int
    get() = 2

  private lateinit var color: DyeColor
  private lateinit var pattern: PatternType

  override fun append(index: Int, value: ValueAccessor) {
    when (index) {
      0 -> color = DyeColor.getByColor(Color.fromARGB(value.asInt()))!!
      1 -> pattern = Registry.BANNER_PATTERN[NamespacedKey.fromString(value.asString())!!]!!
    }
  }

  override fun build(): Pattern = Pattern(color, pattern)
}
