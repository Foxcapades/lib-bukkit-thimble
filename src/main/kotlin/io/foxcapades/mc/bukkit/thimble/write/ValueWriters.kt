@file:Suppress("FunctionName")

package io.foxcapades.mc.bukkit.thimble.write

import io.foxcapades.mc.bukkit.thimble.values.*
import java.io.OutputStream
import java.math.BigDecimal
import java.math.BigInteger


// region BigDecimal

internal fun BigFloatWriter(value: BigDecimal, stream: OutputStream) {
  stream.writeWithHeader(BigFloatValue(value))
}

// endregion BigDecimal

// region BigInteger

internal fun BigIntWriter(value: BigInteger, stream: OutputStream) {
  stream.writeWithHeader(BigIntValue(value))
}

// endregion BigInteger

// region Boolean

internal fun BooleanWriter(value: Boolean, stream: OutputStream) {
  stream.writeWithHeader(BooleanValue(value))
}

internal fun HeadlessBooleanWriter(value: Boolean, stream: OutputStream) {
  BooleanValue(value).writeTo(stream)
}

// endregion Boolean

// region Byte

internal fun ByteWriter(value: Byte, stream: OutputStream) {
  stream.writeWithHeader(ByteValue(value))
}

// endregion Byte

// region Double

internal fun DoubleWriter(value: Double, stream: OutputStream) {
  stream.writeWithHeader(DoubleValue(value))
}

// endregion Double

// region Float

internal fun FloatWriter(value: Float, stream: OutputStream) {
  stream.writeWithHeader(FloatValue(value))
}

// endregion Float

// region Int

internal fun IntWriter(value: Int, stream: OutputStream) {
  stream.writeWithHeader(IntValue(value))
}

// endregion Int

// region Long

internal fun LongWriter(value: Long, stream: OutputStream) {
  stream.writeWithHeader(LongValue(value))
}


// endregion Long

// region Null

internal fun OutputStream.writeNull() {
  writeWithHeader(NullValue())
}

// endregion Null

// region Short

internal fun ShortWriter(value: Short, stream: OutputStream) {
  stream.writeWithHeader(ShortValue(value))
}

// endregion Short

// region String

@Deprecated("what")
internal fun writeString(v: String, s: OutputStream) {
  s.writeWithHeader(StringValue(v))
}

internal fun OutputStream.writeString(v: String) {
  writeWithHeader(StringValue(v))
}

// endregion String

// region Utils

@Suppress("NOTHING_TO_INLINE")
private inline fun OutputStream.writeWithHeader(value: ThimbleValue) {
  value.typeHeader.writeTo(this)
  value.writeTo(this)
}

// endregion Utils
