package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

data object LongListTypeHandler : SimpleListTypeHandler<Long>() {
  override val typeIndicator: String
    get() = "<l>"

  override val elementType: Class<Long>
    get() = Long::class.java

  override fun writeValue(value: Long, writer: ValueWriter) =
    writer.writeLong(value)

  override fun readValue(reader: ValueAccessor) =
    reader.asLong()
}
