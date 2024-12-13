package io.foxcapades.mc.bukkit.thimble.types.jvm

import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.impl.SimpleListTypeDefinition
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

/**
 * Basic [List]<[Byte]> (de)serialization provider.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since 1.0.0
 */
data object ByteListTypeDefinition : SimpleListTypeDefinition<Byte>() {
  override val elementType   get() = Byte::class.java
  override val typeIdentifier get() = "<b>"

  override fun writeValue(value: Byte, writer: ValueWriter) =
    writer.writeByte(value)

  override fun readValue(reader: ValueAccessor) =
    reader.asByte()
}
