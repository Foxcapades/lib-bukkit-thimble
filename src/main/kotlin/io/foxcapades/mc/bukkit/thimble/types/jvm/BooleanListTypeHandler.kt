package io.foxcapades.mc.bukkit.thimble.types.jvm

import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.impl.SimpleListTypeHandler
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter


/**
 * Basic [List]<[Boolean]> (de)serialization provider.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since 1.0.0
 */
data object BooleanListTypeHandler : SimpleListTypeHandler<Boolean>() {
  override val elementType   get() = Boolean::class.java
  override val typeIndicator get() = "<B>"

  override fun writeValue(value: Boolean, writer: ValueWriter) =
    writer.writeBoolean(value)

  override fun readValue(reader: ValueAccessor) =
    reader.asBoolean()
}
