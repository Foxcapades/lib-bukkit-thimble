package io.foxcapades.mc.bukkit.thimble.types.jvm

import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.impl.SimpleListTypeHandler
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter


/**
 * Basic [List]<[Double]> (de)serialization provider.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since 1.0.0
 */
data object DoubleListTypeHandler : SimpleListTypeHandler<Double>() {
  override val elementType   get() = Double::class.java
  override val typeIndicator get() = "<d>"

  override fun writeValue(value: Double, writer: ValueWriter) =
    writer.writeRaw(DoubleTypeHandler.serializeToRaw(value))

  override fun readValue(reader: ValueAccessor) =
    reader.asDouble()
}
