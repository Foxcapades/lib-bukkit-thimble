package io.foxcapades.mc.bukkit.thimble.types.jvm

import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.impl.SimpleListTypeDefinition
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter


/**
 * Basic [List]<[Short]> (de)serialization provider.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since 1.0.0
 */
open class ShortListTypeDefinition : SimpleListTypeDefinition<Short>() {
  override val elementType    get() = Short::class.java
  override val typeIdentifier get() = "<s>"

  override fun writeValue(value: Short, writer: ValueWriter) =
    writer.writeShort(value)

  override fun readValue(reader: ValueAccessor) =
    reader.asShort()
}
