package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.DataType
import io.foxcapades.mc.bukkit.thimble.ScalarType
import io.foxcapades.mc.bukkit.thimble.codecs.UnversionedCodec
import io.foxcapades.mc.bukkit.thimble.io.mustGetShort
import io.foxcapades.mc.bukkit.thimble.utils.writeShort
import java.io.OutputStream
import java.nio.ByteBuffer

object UShortCodec : UnversionedCodec<UShort> {
  override val dataType: DataType
    get() = DataType.Scalar(ScalarType.Byte)

  override val javaType: Class<out UShort>
    get() = UShort::class.java

  override fun create(from: ByteBuffer): UShort =
    from.mustGetShort().toUShort()

  override fun encodeBody(into: OutputStream, value: UShort) =
    into.writeShort(value.toShort())
}
