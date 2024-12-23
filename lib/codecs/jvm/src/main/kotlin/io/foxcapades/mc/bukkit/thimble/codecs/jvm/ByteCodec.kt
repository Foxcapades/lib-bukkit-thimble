package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.codecs.UnversionedCodec
import io.foxcapades.mc.bukkit.thimble.types.DataType
import io.foxcapades.mc.bukkit.thimble.io.writeI8
import io.foxcapades.mc.bukkit.thimble.io.readI8
import java.io.InputStream
import java.io.OutputStream

object ByteCodec : UnversionedCodec<Byte> {
  override val dataType get() = DataType.Byte

  override val javaType: Class<out Byte>
    get() = Byte::class.java

  override fun decodeBody(from: InputStream, into: Byte) {}
  override fun encodeBody(into: OutputStream, value: Byte) = into.writeI8(value)
  override fun create(from: InputStream) = from.readI8()
}
