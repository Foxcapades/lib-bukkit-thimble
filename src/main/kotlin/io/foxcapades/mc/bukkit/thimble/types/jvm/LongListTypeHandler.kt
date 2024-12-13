package io.foxcapades.mc.bukkit.thimble.types.jvm

import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.impl.SimpleListTypeHandler
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter


/**
 * Basic [List]<[Long]> (de)serialization provider.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since 1.0.0
 */
data object LongListTypeHandler : SimpleListTypeHandler<Long>() {
  override val elementType   get() = Long::class.java
  override val typeIndicator get() = "<l>"

  override fun writeValue(value: Long, writer: ValueWriter) =
    writer.writeLong(value)

  override fun readValue(reader: ValueAccessor) =
    reader.asLong()
}
