package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.codecs.UnversionedCodec
import io.foxcapades.mc.bukkit.thimble.DataType
import io.foxcapades.mc.bukkit.thimble.ScalarType
import io.foxcapades.mc.bukkit.thimble.io.mustGetShort
import io.foxcapades.mc.bukkit.thimble.utils.writeShort
import java.io.OutputStream
import java.nio.ByteBuffer

object ShortCodec : UnversionedCodec<Short> {
  override val dataType: DataType
    get() = DataType.Scalar(ScalarType.Short)

  override val javaType: Class<Short>
    get() = Short::class.java

  override fun encodeBody(into: OutputStream, value: Short) =
    into.writeShort(value)

  override fun create(from: ByteBuffer): Short =
    from.mustGetShort()
}
