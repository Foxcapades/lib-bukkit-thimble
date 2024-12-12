package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

data object FloatListTypeHandler : SimpleListTypeHandler<Float>() {
  override val typeIndicator: String
    get() = "<f>"

  override val elementType: Class<Float>
    get() = Float::class.java

  override fun writeValue(value: Float, writer: ValueWriter) =
    writer.writeRaw(FloatTypeHandler.serializeToRaw(value))

  override fun readValue(reader: ValueAccessor) =
    reader.asFloat()
}
