package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.codecs.UnversionedCodec
import io.foxcapades.mc.bukkit.thimble.types.DataType
import java.io.InputStream
import java.io.OutputStream

object BooleanCodec : UnversionedCodec<Boolean> {
  override val dataType get() = DataType.Boolean
  override fun encodeBody(into: OutputStream, value: Boolean) = into.write(if (value) 1 else 0)
  override fun create(from: InputStream) = from.read() > 0
}
