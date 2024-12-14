package io.foxcapades.mc.bukkit.thimble.types.jvm

import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.impl.SimpleListTypeDefinition
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter


/**
 * Basic [List]<[String]> (de)serialization provider.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since 1.0.0
 */
open class StringListTypeDefinition : SimpleListTypeDefinition<String>() {
  override val elementType    get() = String::class.java
  override val typeIdentifier get() = "<S>"

  override fun writeValue(value: String, writer: ValueWriter) =
    writer.writeString(value)

  override fun readValue(reader: ValueAccessor) =
    reader.asString()
}
