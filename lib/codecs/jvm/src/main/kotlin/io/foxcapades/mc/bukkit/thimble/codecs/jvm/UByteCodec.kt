package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.DataType
import io.foxcapades.mc.bukkit.thimble.ScalarType
import io.foxcapades.mc.bukkit.thimble.codecs.UnversionedCodec
import io.foxcapades.mc.bukkit.thimble.io.mustGetByte
import java.io.OutputStream
import java.nio.ByteBuffer

object UByteCodec : UnversionedCodec<UByte> {
  override val dataType: DataType
    get() = DataType.Scalar(ScalarType.Byte)

  override val javaType: Class<out UByte>
    get() = UByte::class.java

  override fun create(from: ByteBuffer): UByte =
    from.mustGetByte().toUByte()

  override fun encodeBody(into: OutputStream, value: UByte) =
    into.write(value.toInt())
}
