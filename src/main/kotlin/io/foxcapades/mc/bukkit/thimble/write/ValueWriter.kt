package io.foxcapades.mc.bukkit.thimble.write

import java.math.BigDecimal
import java.math.BigInteger

interface ValueWriter {
  fun writeBinary(value: ByteArray)

  fun writeBinaryOrNull(value: ByteArray?)

  fun writeBoolean(value: Boolean)

  fun writeBooleanOrNull(value: Boolean?)

  fun writeBooleanArray(value: BooleanArray)

  fun writeBooleanArrayOrNull(value: BooleanArray?)

  fun writeByte(value: Byte)

  fun writeByteOrNull(value: Byte?)

  fun writeShort(value: Short)

  fun writeShortOrNull(value: Short?)

  fun writeShortArray(value: ShortArray)

  fun writeShortArrayOrNull(value: ShortArray?)

  fun writeInt(value: Int)

  fun writeIntOrNull(value: Int?)

  fun writeIntArray(value: IntArray)

  fun writeIntArrayOrNull(value: IntArray?)

  fun writeLong(value: Long)

  fun writeLongOrNull(value: Long?)

  fun writeLongArray(value: LongArray)

  fun writeLongArrayOrNull(value: LongArray?)

  fun writeBigInteger(value: BigInteger)

  fun writeBigIntegerOrNull(value: BigInteger?)

  fun writeFloat(value: Float)

  fun writeFloatOrNull(value: Float?)

  fun writeFloatArray(value: FloatArray)

  fun writeFloatArrayOrNull(value: FloatArray?)

  fun writeDouble(value: Double)

  fun writeDoubleOrNull(value: Double?)

  fun writeDoubleArray(value: DoubleArray)

  fun writeDoubleArrayOrNull(value: DoubleArray?)

  fun writeBigDecimal(value: BigDecimal)

  fun writeBigDecimalOrNull(value: BigDecimal?)

  fun writeString(value: String)

  fun writeStringOrNull(value: String?)

  fun writeStringArray(value: Array<String>)

  fun writeStringArrayOrNull(value: Array<String>?)

  fun writeNull()

  fun writeComplex(value: Any)

  fun writeComplexOrNull(value: Any?, asType: Class<*>)
  fun writeBinaryList(value: List<ByteArray>)
  fun writeBinaryListOrNull(value: List<ByteArray>?)
  fun writeBooleanList(value: List<Boolean>)
  fun writeBooleanListOrNull(value: List<Boolean>?)
  fun writeByteList(value: List<Byte>)
  fun writeByteListOrNull(value: List<Byte>?)
  fun writeShortList(value: List<Short>)
  fun writeShortListOrNull(value: List<Short>?)
  fun writeIntList(value: List<Int>)
  fun writeIntListOrNull(value: List<Int>?)
  fun writeIntArrayList(value: List<IntArray>)
  fun writeIntArrayListOrNull(value: List<IntArray>?)
  fun writeLongList(value: List<Long>)
  fun writeLongListOrNull(value: List<Long>?)
  fun writeLongArrayList(value: List<LongArray>)
  fun writeLongArrayListOrNull(value: List<LongArray>?)
  fun writeFloatList(value: List<Float>)
  fun writeFloatListOrNull(value: List<Float>?)
  fun writeDoubleList(value: List<Double>)
  fun writeDoubleListOrNull(value: List<Double>?)
  fun writeStringList(value: List<String>)
  fun writeStringListOrNull(value: List<String>?)
  fun writeRaw(value: String)
  fun writeRawOrNull(value: String?)
}
