package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.ScalarType
import io.foxcapades.mc.bukkit.thimble.codecs.Codec
import io.foxcapades.mc.bukkit.thimble.codecs.ScalarCodecProvider
import io.foxcapades.mc.bukkit.thimble.codecs.U8CodecVersion
import io.foxcapades.mc.bukkit.thimble.codecs.reflect.ComplexType
import io.foxcapades.mc.bukkit.thimble.codecs.reflect.resolve
import java.math.BigDecimal
import java.math.BigInteger

class DefaultScalarCodecProvider : ScalarCodecProvider {
  override fun getCodec(forType: ScalarType): Codec<*> =
    when (forType) {
      ScalarType.Null       -> NullCodec
      ScalarType.Boolean    -> BooleanCodec
      ScalarType.Byte       -> ByteCodec
      ScalarType.Short      -> ShortCodec
      ScalarType.Int        -> IntCodec
      ScalarType.Long       -> LongCodec
      ScalarType.Float      -> FloatCodec
      ScalarType.Double     -> DoubleCodec
      ScalarType.String     -> StringCodecV1
      ScalarType.BigInteger -> BigIntegerCodec
      ScalarType.BigDecimal -> BigDecimalCodec
    }

  override fun getCodec(forType: ScalarType, version: U8CodecVersion): Codec<*>? =
    if (version.value == 1.toUByte())
      getCodec(forType)
    else
      null

  @Suppress("UNCHECKED_CAST")
  override fun <T> getCodec(forType: Class<T>): Codec<T>? =
    when (forType.packageName) {
      "java.lang" -> when ((forType as Class<out Any>).kotlin) {
        Void::class    -> NullCodec
        Byte::class    -> ByteCodec
        String::class  -> StringCodecV1
        Int::class     -> IntCodec
        Double::class  -> DoubleCodec
        Long::class    -> LongCodec
        Float::class   -> FloatCodec
        Boolean::class -> BooleanCodec
        Short::class   -> ShortCodec
        else           -> when {
          CharSequence::class.java.isAssignableFrom(forType) -> StringCodecV1
          else -> null
        }
      }

      "java.math" -> when (forType) {
        BigInteger::class.java -> BigIntegerCodec
        BigDecimal::class.java -> BigDecimalCodec
        else                   -> null
      }

      "kotlin" -> when ((forType as Class<out Any>).kotlin) {
        Unit::class   -> NullCodec
        UByte::class  -> UByteCodec
        UInt::class   -> UIntCodec
        ULong::class  -> ULongCodec
        UShort::class -> UShortCodec
        else          -> null
      }
      else -> null
    } as Codec<T>?

  override fun <T> getCodec(forType: Class<T>, version: U8CodecVersion): Codec<T>? =
    if (version.value == 1.toUByte())
      getCodec(forType)
    else
      null

  @Suppress("UNCHECKED_CAST")
  override fun <T: Any> getCodec(forType: ComplexType<T>): Codec<T>? =
    getCodec(forType.resolve().type) as Codec<T>?

  override fun <T: Any> getCodec(forType: ComplexType<T>, version: U8CodecVersion): Codec<T>? =
    if (version.value == 1.toUByte())
      getCodec(forType)
    else
      null
}
