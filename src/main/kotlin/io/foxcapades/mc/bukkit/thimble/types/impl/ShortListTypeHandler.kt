package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

data object ShortListTypeHandler : SimpleListTypeHandler<Short>() {
  override val typeIndicator: String
    get() = "<s>"

  override val elementType: Class<Short>
    get() = Short::class.java

  override fun writeValue(value: Short, writer: ValueWriter) =
    writer.writeShort(value)

  override fun readValue(reader: ValueAccessor) =
    reader.asShort()
}
