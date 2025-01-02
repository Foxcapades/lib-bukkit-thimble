package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.codecs.UnversionedCodec
import io.foxcapades.mc.bukkit.thimble.DataType
import io.foxcapades.mc.bukkit.thimble.ScalarType
import io.foxcapades.mc.bukkit.thimble.io.mustGetInt
import io.foxcapades.mc.bukkit.thimble.utils.writeInt
import java.io.OutputStream
import java.nio.ByteBuffer

object FloatCodec : UnversionedCodec<Float> {
  override val dataType: DataType
    get() = DataType.Scalar(ScalarType.Float)

  override val javaType: Class<Float>
    get() = Float::class.java

  override fun encodeBody(into: OutputStream, value: Float) =
    into.writeInt(value.toRawBits())

  override fun create(from: ByteBuffer) =
    Float.fromBits(from.mustGetInt())
}
