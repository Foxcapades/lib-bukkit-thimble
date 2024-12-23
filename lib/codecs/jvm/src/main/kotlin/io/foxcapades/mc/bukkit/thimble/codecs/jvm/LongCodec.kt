package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.codecs.UnversionedCodec
import io.foxcapades.mc.bukkit.thimble.io.readI64
import io.foxcapades.mc.bukkit.thimble.io.writeI64
import io.foxcapades.mc.bukkit.thimble.types.DataType
import java.io.InputStream
import java.io.OutputStream

object LongCodec : UnversionedCodec<Long> {
  override val dataType get() = DataType.Long
  override fun encodeBody(into: OutputStream, value: Long) = into.writeI64(value)
  override fun create(from: InputStream) = from.readI64()
}
