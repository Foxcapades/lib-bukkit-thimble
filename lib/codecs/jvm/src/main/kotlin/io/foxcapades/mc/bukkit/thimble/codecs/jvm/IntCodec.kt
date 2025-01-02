package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.codecs.UnversionedCodec
import io.foxcapades.mc.bukkit.thimble.DataType
import io.foxcapades.mc.bukkit.thimble.ScalarType
import io.foxcapades.mc.bukkit.thimble.io.mustGetInt
import io.foxcapades.mc.bukkit.thimble.utils.writeInt
import java.io.OutputStream
import java.nio.ByteBuffer

object IntCodec : UnversionedCodec<Int> {
  override val dataType: DataType
    get() = DataType.Scalar(ScalarType.Int)

  override val javaType: Class<Int>
    get() = Int::class.java

  override fun encodeBody(into: OutputStream, value: Int) =
    into.writeInt(value)

  override fun create(from: ByteBuffer): Int =
    from.mustGetInt()
}
