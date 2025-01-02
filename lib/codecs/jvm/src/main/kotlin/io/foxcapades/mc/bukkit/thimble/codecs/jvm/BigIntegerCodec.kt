package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.codecs.UnversionedCodec
import io.foxcapades.mc.bukkit.thimble.DataType
import io.foxcapades.mc.bukkit.thimble.ScalarType
import java.io.OutputStream
import java.math.BigInteger
import java.nio.ByteBuffer

object BigIntegerCodec : UnversionedCodec<BigInteger> {
  override val dataType: DataType
    get() = DataType.Scalar(ScalarType.BigInteger)

  override val javaType: Class<out BigInteger>
    get() = BigInteger::class.java

  override fun create(from: ByteBuffer): BigInteger =
    BigInteger(ByteArrayCodecV1.create(from))

  override fun encodeBody(into: OutputStream, value: BigInteger) {
    ByteArrayCodecV1.encodeBody(into, value.toByteArray())
  }
}
