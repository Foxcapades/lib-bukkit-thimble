package io.foxcapades.mc.bukkit.thimble.types.jvm

import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.impl.SimpleListTypeHandler
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter


/**
 * Basic [List]<[Float]> (de)serialization provider.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since 1.0.0
 */
data object FloatListTypeHandler : SimpleListTypeHandler<Float>() {
  override val elementType   get() = Float::class.java
  override val typeIndicator get() = "<f>"

  override fun writeValue(value: Float, writer: ValueWriter) =
    writer.writeRaw(FloatTypeHandler.serializeToRaw(value))

  override fun readValue(reader: ValueAccessor) =
    reader.asFloat()
}
