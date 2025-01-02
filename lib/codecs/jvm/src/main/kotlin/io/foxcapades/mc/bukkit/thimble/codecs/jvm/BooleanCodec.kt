package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.codecs.UnversionedCodec
import io.foxcapades.mc.bukkit.thimble.DataType
import io.foxcapades.mc.bukkit.thimble.ScalarType
import io.foxcapades.mc.bukkit.thimble.io.mustGetByte
import java.io.OutputStream
import java.nio.ByteBuffer

object BooleanCodec : UnversionedCodec<Boolean> {
  override val dataType
    get() = DataType.Scalar(ScalarType.Boolean)

  override val javaType
    get() = Boolean::class.java

  override fun encodeBody(into: OutputStream, value: Boolean) =
    into.write(if (value) 1 else 0)

  override fun create(from: ByteBuffer) =
    from.mustGetByte() > 0
}
