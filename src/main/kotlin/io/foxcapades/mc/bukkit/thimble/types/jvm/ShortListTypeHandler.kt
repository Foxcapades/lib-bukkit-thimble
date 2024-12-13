package io.foxcapades.mc.bukkit.thimble.types.jvm

import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.impl.SimpleListTypeHandler
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter


/**
 * Basic [List]<[Short]> (de)serialization provider.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since 1.0.0
 */
data object ShortListTypeHandler : SimpleListTypeHandler<Short>() {
  override val elementType   get() = Short::class.java
  override val typeIndicator get() = "<s>"

  override fun writeValue(value: Short, writer: ValueWriter) =
    writer.writeShort(value)

  override fun readValue(reader: ValueAccessor) =
    reader.asShort()
}
