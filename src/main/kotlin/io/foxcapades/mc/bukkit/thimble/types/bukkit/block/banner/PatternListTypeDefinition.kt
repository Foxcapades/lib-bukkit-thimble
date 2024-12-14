package io.foxcapades.mc.bukkit.thimble.types.bukkit.block.banner

import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.impl.SimpleListTypeDefinition
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter
import org.bukkit.Color
import org.bukkit.DyeColor
import org.bukkit.NamespacedKey
import org.bukkit.Registry
import org.bukkit.block.banner.Pattern

open class PatternListTypeDefinition : SimpleListTypeDefinition<Pattern>() {
  override val elementType    get() = Pattern::class.java
  override val typeIdentifier get() = "<b:b:P>"

  override fun writeValue(value: Pattern, writer: ValueWriter) =
    writer.writeString("${value.color.color.asARGB()}:${value.pattern.key}")

  override fun readValue(reader: ValueAccessor): Pattern =
    reader.asString().let {
      val i = it.indexOf(':')
      val color = it.substring(0, i).toInt()
      val key = it.substring(i+1)

      Pattern(
        DyeColor.getByColor(Color.fromARGB(color))!!,
        Registry.BANNER_PATTERN[NamespacedKey.fromString(key)!!]!!
      )
    }
}
