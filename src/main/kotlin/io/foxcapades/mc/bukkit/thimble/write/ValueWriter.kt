package io.foxcapades.mc.bukkit.thimble.write

import org.bukkit.NamespacedKey
import java.math.BigDecimal
import java.math.BigInteger

interface ValueWriter {
  // region Complex Types

  fun writeComplex(value: Any)

  fun writeComplexOrNull(value: Any?, asType: Class<*>)

  fun writeBinary(value: ByteArray)

  fun writeBinaryOrNull(value: ByteArray?)

  fun writeBinaryList(value: List<ByteArray>)

  fun writeBinaryListOrNull(value: List<ByteArray>?)

  fun writeBooleanArray(value: BooleanArray)

  fun writeBooleanArrayOrNull(value: BooleanArray?)

  fun writeBooleanList(value: List<Boolean>)

  fun writeBooleanListOrNull(value: List<Boolean>?)

  fun writeByteList(value: List<Byte>)

  fun writeByteListOrNull(value: List<Byte>?)

  fun writeShortArray(value: ShortArray)

  fun writeShortArrayOrNull(value: ShortArray?)

  fun writeShortList(value: List<Short>)

  fun writeShortListOrNull(value: List<Short>?)

  fun writeIntArray(value: IntArray)

  fun writeIntArrayOrNull(value: IntArray?)

  fun writeIntList(value: List<Int>)

  fun writeIntListOrNull(value: List<Int>?)

  fun writeIntArrayList(value: List<IntArray>)

  fun writeIntArrayListOrNull(value: List<IntArray>?)

  fun writeLongList(value: List<Long>)

  fun writeLongListOrNull(value: List<Long>?)

  fun writeLongArray(value: LongArray)

  fun writeLongArrayOrNull(value: LongArray?)

  fun writeLongArrayList(value: List<LongArray>)

  fun writeLongArrayListOrNull(value: List<LongArray>?)

  fun writeFloatArray(value: FloatArray)

  fun writeFloatArrayOrNull(value: FloatArray?)

  fun writeFloatList(value: List<Float>)

  fun writeFloatListOrNull(value: List<Float>?)

  fun writeDoubleArray(value: DoubleArray)

  fun writeDoubleArrayOrNull(value: DoubleArray?)

  fun writeDoubleList(value: List<Double>)

  fun writeDoubleListOrNull(value: List<Double>?)

  fun writeStringList(value: List<String>)

  fun writeStringListOrNull(value: List<String>?)

  fun writeStringArray(value: Array<String>)

  fun writeStringArrayOrNull(value: Array<String>?)

  // endregion Complex Types

  // region Scalar Types

  fun writeBoolean(value: Boolean)

  fun writeBooleanOrNull(value: Boolean?)

  fun writeByte(value: Byte)

  fun writeByteOrNull(value: Byte?)

  fun writeShort(value: Short)

  fun writeShortOrNull(value: Short?)

  fun writeInt(value: Int)

  fun writeIntOrNull(value: Int?)

  fun writeLong(value: Long)

  fun writeLongOrNull(value: Long?)

  fun writeBigInteger(value: BigInteger)

  fun writeBigIntegerOrNull(value: BigInteger?)

  fun writeFloat(value: Float)

  fun writeFloatOrNull(value: Float?)

  fun writeDouble(value: Double)

  fun writeDoubleOrNull(value: Double?)

  fun writeBigDecimal(value: BigDecimal)

  fun writeBigDecimalOrNull(value: BigDecimal?)

  fun writeString(value: String)

  fun writeStringOrNull(value: String?)

  fun writeNull()

  // endregion Scalar Types

  fun writeRaw(value: String)

  fun writeRawOrNull(value: String?)

  fun writeKey(value: NamespacedKey)

  fun writeKeyOrNull(value: NamespacedKey?)
}
