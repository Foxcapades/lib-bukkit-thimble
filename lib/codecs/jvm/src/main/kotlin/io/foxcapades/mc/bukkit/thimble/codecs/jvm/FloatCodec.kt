package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.codecs.UnversionedCodec
import io.foxcapades.mc.bukkit.thimble.types.DataType
import io.foxcapades.mc.bukkit.thimble.io.BasicIO.readI32
import io.foxcapades.mc.bukkit.thimble.io.BasicIO.writeI32
import java.io.InputStream
import java.io.OutputStream

object FloatCodec : UnversionedCodec<Float> {
  override val dataType get() = DataType.Float
  override fun encodeBody(into: OutputStream, value: Float) = into.writeI32(value.toRawBits())
  override fun create(from: InputStream) = Float.fromBits(from.readI32())
}
