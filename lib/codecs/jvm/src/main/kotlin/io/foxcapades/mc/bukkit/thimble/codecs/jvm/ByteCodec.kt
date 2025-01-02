package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.codecs.UnversionedCodec
import io.foxcapades.mc.bukkit.thimble.DataType
import io.foxcapades.mc.bukkit.thimble.ScalarType
import io.foxcapades.mc.bukkit.thimble.io.mustGetByte
import java.io.OutputStream
import java.nio.ByteBuffer

object ByteCodec : UnversionedCodec<Byte> {

  override val dataType: DataType
    get() = DataType.Scalar(ScalarType.Byte)

  override val javaType: Class<out Byte>
    get() = Byte::class.java

  override fun encodeBody(into: OutputStream, value: Byte) =
    into.write(value.toInt())

  override fun create(from: ByteBuffer) =
    from.mustGetByte()
}
