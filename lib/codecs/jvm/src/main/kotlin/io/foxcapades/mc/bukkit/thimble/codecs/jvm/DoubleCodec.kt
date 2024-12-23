package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.codecs.UnversionedCodec
import io.foxcapades.mc.bukkit.thimble.types.DataType
import io.foxcapades.mc.bukkit.thimble.io.BasicIO.readI64
import io.foxcapades.mc.bukkit.thimble.io.BasicIO.writeI64
import java.io.InputStream
import java.io.OutputStream

object DoubleCodec : UnversionedCodec<Double> {
  override val dataType get() = DataType.Double
  override fun encodeBody(into: OutputStream, value: Double) = into.writeI64(value.toRawBits())
  override fun create(from: InputStream) = Double.fromBits(from.readI64())
}
