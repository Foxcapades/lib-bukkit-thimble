package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.codecs.UnversionedCodec
import io.foxcapades.mc.bukkit.thimble.io.readI16
import io.foxcapades.mc.bukkit.thimble.io.writeI16
import io.foxcapades.mc.bukkit.thimble.types.DataType
import java.io.InputStream
import java.io.OutputStream

object ShortCodec : UnversionedCodec<Short> {
  override val dataType get() = DataType.Short
  override fun encodeBody(into: OutputStream, value: Short) = into.writeI16(value)
  override fun create(from: InputStream) = from.readI16()
}
