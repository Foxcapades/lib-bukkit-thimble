package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.codecs.UnversionedCodec
import io.foxcapades.mc.bukkit.thimble.DataType
import io.foxcapades.mc.bukkit.thimble.ScalarType
import io.foxcapades.mc.bukkit.thimble.io.mustGetInt
import io.foxcapades.mc.bukkit.thimble.utils.writeInt
import java.io.OutputStream
import java.math.BigDecimal
import java.nio.ByteBuffer

object BigDecimalCodec : UnversionedCodec<BigDecimal> {
  override val dataType: DataType
    get() = DataType.Scalar(ScalarType.BigDecimal)

  override val javaType: Class<out BigDecimal>
    get() = BigDecimal::class.java

  override fun encodeBody(into: OutputStream, value: BigDecimal) {
    BigIntegerCodec.encodeBody(into, value.unscaledValue())
    into.writeInt(value.scale())
  }

  override fun create(from: ByteBuffer): BigDecimal =
    BigDecimal(BigIntegerCodec.create(from), from.mustGetInt())
}
