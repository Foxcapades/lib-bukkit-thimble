package io.foxcapades.mc.bukkit.thimble.write

import com.google.gson.stream.JsonWriter
import io.foxcapades.mc.bukkit.thimble.types.*
import java.math.BigDecimal
import java.math.BigInteger

internal class ValueWriterImpl(
  private val registry: TypeHandlerRegistry,
  private val writer: JsonWriter
) : ValueWriter {
  override fun writeBinary(value: ByteArray) = write(value)
  override fun writeBinaryOrNull(value: ByteArray?) = writeNullable(value)
  override fun writeBinaryList(value: List<ByteArray>) = write(value)
  override fun writeBinaryListOrNull(value: List<ByteArray>?) = writeNullable(value)

  override fun writeBoolean(value: Boolean) = write(value)
  override fun writeBooleanOrNull(value: Boolean?) = writeNullable(value)
  override fun writeBooleanArray(value: BooleanArray) = write(value)
  override fun writeBooleanArrayOrNull(value: BooleanArray?) = writeNullable(value)
  override fun writeBooleanList(value: List<Boolean>) = write(value)
  override fun writeBooleanListOrNull(value: List<Boolean>?) = writeNullable(value)

  override fun writeByte(value: Byte) = write(value)
  override fun writeByteOrNull(value: Byte?) = writeNullable(value)
  override fun writeByteList(value: List<Byte>) = write(value)
  override fun writeByteListOrNull(value: List<Byte>?) = writeNullable(value)

  override fun writeShort(value: Short) = write(value)
  override fun writeShortOrNull(value: Short?) = writeNullable(value)
  override fun writeShortArray(value: ShortArray) = write(value)
  override fun writeShortArrayOrNull(value: ShortArray?) = writeNullable(value)
  override fun writeShortList(value: List<Short>) = write(value)
  override fun writeShortListOrNull(value: List<Short>?) = writeNullable(value)

  override fun writeInt(value: Int) = write(value)
  override fun writeIntOrNull(value: Int?) = writeNullable(value)
  override fun writeIntArray(value: IntArray) = write(value)
  override fun writeIntArrayOrNull(value: IntArray?) = writeNullable(value)
  override fun writeIntList(value: List<Int>) = write(value)
  override fun writeIntListOrNull(value: List<Int>?) = writeNullable(value)
  override fun writeIntArrayList(value: List<IntArray>) = write(value)
  override fun writeIntArrayListOrNull(value: List<IntArray>?) = writeNullable(value)

  override fun writeLong(value: Long) = write(value)
  override fun writeLongOrNull(value: Long?) = writeNullable(value)
  override fun writeLongArray(value: LongArray) = write(value)
  override fun writeLongArrayOrNull(value: LongArray?) = writeNullable(value)
  override fun writeLongList(value: List<Long>) = write(value)
  override fun writeLongListOrNull(value: List<Long>?) = writeNullable(value)
  override fun writeLongArrayList(value: List<LongArray>) = write(value)
  override fun writeLongArrayListOrNull(value: List<LongArray>?) = writeNullable(value)

  override fun writeBigInteger(value: BigInteger) = write(value)
  override fun writeBigIntegerOrNull(value: BigInteger?) = writeNullable(value)

  override fun writeFloat(value: Float) = write(value)
  override fun writeFloatOrNull(value: Float?) = writeNullable(value)
  override fun writeFloatArray(value: FloatArray) = write(value)
  override fun writeFloatArrayOrNull(value: FloatArray?) = writeNullable(value)
  override fun writeFloatList(value: List<Float>) = write(value)
  override fun writeFloatListOrNull(value: List<Float>?) = writeNullable(value)

  override fun writeDouble(value: Double) = write(value)
  override fun writeDoubleOrNull(value: Double?) = writeNullable(value)
  override fun writeDoubleArray(value: DoubleArray) = write(value)
  override fun writeDoubleArrayOrNull(value: DoubleArray?) = writeNullable(value)
  override fun writeDoubleList(value: List<Double>) = write(value)
  override fun writeDoubleListOrNull(value: List<Double>?) = writeNullable(value)

  override fun writeBigDecimal(value: BigDecimal) = write(value)
  override fun writeBigDecimalOrNull(value: BigDecimal?) = writeNullable(value)

  override fun writeString(value: String) = write(value)
  override fun writeStringOrNull(value: String?) = writeNullable(value)
  override fun writeStringArray(value: Array<String>) = write(value)
  override fun writeStringArrayOrNull(value: Array<String>?) = writeNullable(value)
  override fun writeStringList(value: List<String>) = write(value)
  override fun writeStringListOrNull(value: List<String>?) = writeNullable(value)

  override fun writeNull() { writer.nullValue() }

  override fun writeRaw(value: String) { writer.jsonValue(value) }

  override fun writeRawOrNull(value: String?)  { writer.jsonValue(value) }

  override fun writeComplex(value: Any) {
    if (value is List<*>)
      return handleUnknownList(value)

    @Suppress("UNCHECKED_CAST")
    when (val handler = registry.requireTypeHandlerFor(value::class.java)) {
      is SimpleTypeHandler<*, *> ->
        writer.withType(handler as SimpleTypeHandler<*, Any>) {
          if (it is RawTypeSerializer<*>)
            jsonValue((handler as RawTypeSerializer<Any>).serializeToRaw(value))
          else
            simpleValue(it.serialize(value), value::class.java)
        }

      is ComplexTypeHandler<*> ->
        writer.complexValue(value, this@ValueWriterImpl, (handler as ComplexTypeHandler<Any>))

      else -> throw IllegalStateException()
    }
  }

  override fun writeComplexOrNull(value: Any?, asType: Class<*>) {
    if (List::class.java.isAssignableFrom(asType)) {
      if (value == null) {
        registry.requireListTypeHandlerFor(Any::class.java).serializeNull(this)
      } else {
        handleUnknownList(value as List<*>)
      }

      return
    }

    @Suppress("UNCHECKED_CAST")
    when (value) {
      null -> when (val handler = registry.requireTypeHandlerFor(asType)) {
        is SimpleTypeHandler<*, *> ->
          writer.withType(handler as SimpleTypeHandler<*, Any>) { simpleValue(it.serializeNull(), asType) }

        is ComplexTypeHandler<*> ->
          handler.serializeNull(this)

        is ListTypeHandler<*> -> throw IllegalStateException()
      }

      else -> write(value)
    }
  }

  @Suppress("UNCHECKED_CAST")
  private fun handleUnknownList(value: List<*>) {
    val handler = if (value.isEmpty())
      registry.requireListTypeHandlerFor(Any::class.java)
    else
      registry.requireListTypeHandlerFor(value.findListType()) as ListTypeHandler<Any>

    handler.serialize(value as List<Any>, this)
  }

  private inline fun <reified T : Any> writeNullable(value: T?) {
    when (value) {
      null -> when (val handler = registry.requireTypeHandlerFor(T::class.java)) {
        is SimpleTypeHandler<*, T> -> writer.simpleValue(handler.serializeNull(), T::class.java)
        is ComplexTypeHandler<T>   -> handler.serializeNull(this)
        is ListTypeHandler<*>      -> handler.serializeNull(this)
      }

      else -> write(value)
    }
  }

  private inline fun <reified T : Any> writeNullable(value: List<T>?) {
    if (value == null)
      registry.requireListTypeHandlerFor(T::class.java).serializeNull(this)
    else
      write(value)
  }

  private inline fun <reified T : Any> write(value: List<T>) {
    writer.listValue(value, this@ValueWriterImpl, registry.requireListTypeHandlerFor(T::class.java))
  }

  private inline fun <reified T : Any> write(value: T) {
    @Suppress("UNCHECKED_CAST")
    when (val handler = registry.requireTypeHandlerFor(T::class.java)) {
      is RawTypeSerializer<*>   -> writer.jsonValue((handler as RawTypeSerializer<Any>).serializeToRaw(value))
      is SimpleTypeHandler<*, T> -> writer.simpleValue(handler.serialize(value), T::class.java)
      is ComplexTypeHandler<T>   -> writer.complexValue(value, this@ValueWriterImpl, handler)
      else -> throw IllegalStateException()
    }
  }
}
