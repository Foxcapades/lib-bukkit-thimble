package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.DataType
import io.foxcapades.mc.bukkit.thimble.ScalarType
import io.foxcapades.mc.bukkit.thimble.codecs.UnversionedCodec
import io.foxcapades.mc.bukkit.thimble.io.mustGetLong
import io.foxcapades.mc.bukkit.thimble.utils.writeLong
import java.io.OutputStream
import java.nio.ByteBuffer

object ULongCodec : UnversionedCodec<ULong> {
  override val dataType: DataType
    get() = DataType.Scalar(ScalarType.Byte)

  override val javaType: Class<out ULong>
    get() = ULong::class.java

  override fun create(from: ByteBuffer): ULong =
    from.mustGetLong().toULong()

  override fun encodeBody(into: OutputStream, value: ULong) =
    into.writeLong(value.toLong())
}
