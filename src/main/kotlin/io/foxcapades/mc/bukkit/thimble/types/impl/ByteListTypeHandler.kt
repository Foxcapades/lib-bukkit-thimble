package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

data object ByteListTypeHandler : SimpleListTypeHandler<Byte>() {
  override val typeIndicator: String
    get() = "<b>"

  override val elementType: Class<Byte>
    get() = Byte::class.java

  override fun writeValue(value: Byte, writer: ValueWriter) =
    writer.writeByte(value)

  override fun readValue(reader: ValueAccessor) =
    reader.asByte()
}
