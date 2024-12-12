package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

data object DoubleListTypeHandler : SimpleListTypeHandler<Double>() {
  override val typeIndicator: String
    get() = "<d>"

  override val elementType: Class<Double>
    get() = Double::class.java

  override fun writeValue(value: Double, writer: ValueWriter) =
    writer.writeRaw(DoubleTypeHandler.serializeToRaw(value))

  override fun readValue(reader: ValueAccessor) =
    reader.asDouble()
}
