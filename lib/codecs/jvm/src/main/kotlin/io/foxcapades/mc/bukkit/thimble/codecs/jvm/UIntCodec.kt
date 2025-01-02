package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.DataType
import io.foxcapades.mc.bukkit.thimble.ScalarType
import io.foxcapades.mc.bukkit.thimble.codecs.UnversionedCodec
import io.foxcapades.mc.bukkit.thimble.io.mustGetInt
import io.foxcapades.mc.bukkit.thimble.utils.writeInt
import java.io.OutputStream
import java.nio.ByteBuffer

object UIntCodec : UnversionedCodec<UInt> {
  override val dataType: DataType
    get() = DataType.Scalar(ScalarType.Byte)

  override val javaType: Class<out UInt>
    get() = UInt::class.java

  override fun create(from: ByteBuffer): UInt =
    from.mustGetInt().toUInt()

  override fun encodeBody(into: OutputStream, value: UInt) =
    into.writeInt(value.toInt())
}
