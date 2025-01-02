package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.codecs.UnversionedCodec
import io.foxcapades.mc.bukkit.thimble.DataType
import io.foxcapades.mc.bukkit.thimble.ScalarType
import io.foxcapades.mc.bukkit.thimble.io.mustGetLong
import io.foxcapades.mc.bukkit.thimble.utils.writeLong
import java.io.OutputStream
import java.nio.ByteBuffer

object LongCodec : UnversionedCodec<Long> {
  override val dataType: DataType
    get() = DataType.Scalar(ScalarType.Long)

  override val javaType: Class<out Long>
    get() = Long::class.java

  override fun encodeBody(into: OutputStream, value: Long) =
    into.writeLong(value)

  override fun create(from: ByteBuffer): Long =
    from.mustGetLong()
}
