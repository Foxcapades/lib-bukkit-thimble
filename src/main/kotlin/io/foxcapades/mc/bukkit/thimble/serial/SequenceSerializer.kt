package io.foxcapades.mc.bukkit.thimble.serial

import io.foxcapades.mc.bukkit.thimble.values.SequenceValue
import io.foxcapades.mc.bukkit.thimble.write.*
import io.foxcapades.mc.bukkit.thimble.write.BooleanWriter
import io.foxcapades.mc.bukkit.thimble.write.ComplexStringWriter
import io.foxcapades.mc.bukkit.thimble.write.HeadlessBooleanWriter
import io.foxcapades.mc.bukkit.thimble.write.BigFloatWriter
import io.foxcapades.mc.bukkit.thimble.write.BigIntWriter
import io.foxcapades.mc.bukkit.thimble.write.writeString
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter
import java.io.OutputStream
import kotlin.reflect.KClass

interface SequenceSerializer<T : Any> {
  fun writeValue(value: T)
  fun writeNull()
}

class SequenceSerializerImpl<T : Any>(
  val valueType: ValueType,
  val valueClass: KClass<out T>,
  val nullable: Boolean,
  val stream: OutputStream,
) : SequenceSerializer<T> {
  private val sequenceTypeHeader: ByteArray

  private val valueWriter: ValueWriter<out Any>

  init {
    when (valueType) {
      ValueType.String -> {
        valueWriter = if (valueClass == String::class)
          writeString
        else
          ComplexStringWriter

        sequenceTypeHeader = byteArrayOf(valueType.toByte())
      }

      ValueType.Boolean -> {
        if (nullable) {
          valueWriter = BooleanWriter
          sequenceTypeHeader = byteArrayOf() // TODO: compound boolean or null
        } else {
          valueWriter = HeadlessBooleanWriter
          sequenceTypeHeader = byteArrayOf()
        }
      }

      ValueType.Sequence -> ::SequenceValue

      ValueType.DictSequence -> ::DictSequenceValue

      ValueType.Complex -> ::ComplexValue

      ValueType.Compound -> ::CompoundValue

      ValueType.BigInt -> BigIntWriter

      ValueType.BigFloat -> BigFloatWriter

      ValueType.Unknown -> UnknownValueWriter()

      else -> throw IllegalStateException()
    }
  }

  override fun writeValue(value: T) {
    if (needsHeaders) {

    }
    TODO("Not yet implemented")
  }

  override fun writeNull() {
    TODO("Not yet implemented")
  }

  private fun writeHeader() {

  }

  private fun writeWithHeader(value: T) {

  }
}

interface RawValueSerializer {
  fun writeU8(value: Byte)
  fun writeU8(value: Int)

  fun writeBinary(value: ByteArray)
}
