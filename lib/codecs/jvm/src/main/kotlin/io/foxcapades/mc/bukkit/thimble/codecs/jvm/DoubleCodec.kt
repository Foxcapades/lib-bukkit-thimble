package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.codecs.UnversionedCodec
import io.foxcapades.mc.bukkit.thimble.DataType
import io.foxcapades.mc.bukkit.thimble.ScalarType
import io.foxcapades.mc.bukkit.thimble.io.mustGetLong
import io.foxcapades.mc.bukkit.thimble.utils.writeLong
import java.io.OutputStream
import java.nio.ByteBuffer

object DoubleCodec : UnversionedCodec<Double> {
  override val dataType: DataType
    get() = DataType.Scalar(ScalarType.Double)

  override val javaType: Class<Double>
    get() = Double::class.java

  override fun encodeBody(into: OutputStream, value: Double) =
    into.writeLong(value.toRawBits())

  override fun create(from: ByteBuffer) =
    Double.fromBits(from.mustGetLong())
}
