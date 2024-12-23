package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.codecs.UnversionedCodec
import io.foxcapades.mc.bukkit.thimble.types.DataType
import io.foxcapades.mc.bukkit.thimble.io.BasicIO.readI32
import io.foxcapades.mc.bukkit.thimble.io.BasicIO.writeI32
import java.io.InputStream
import java.io.OutputStream

object IntCodec : UnversionedCodec<Int> {
  override val dataType get() = DataType.Int
  override fun encodeBody(into: OutputStream, value: Int) = into.writeI32(value)
  override fun create(from: InputStream) = from.readI32()
}
