package io.foxcapades.mc.bukkit.thimble.read

import io.foxcapades.mc.bukkit.thimble.parse.ThimbleDeserializationException
import java.math.BigDecimal
import java.math.BigInteger

interface ValueAccessor {
  val isNumber: Boolean
    get() = false

  val isString: Boolean
    get() = false

  val isBoolean: Boolean
    get() = false

  val isNull: Boolean
    get() = false

  val isComplex: Boolean
    get() = false

  /**
   * Attempt to get this value as a [Byte].
   *
   * @return The value as a [Byte].
   *
   * @throws NumberFormatException If the value is not a [Byte].
   */
  fun asByte(): Byte = asByte(10)

  /**
   * Attempt to get this value as a [Byte].
   *
   * @param radix The radix to be used when parsing the raw value.
   *
   * @return The value as a [Byte].
   *
   * @throws NumberFormatException If the value is not a [Byte].
   */
  fun asByte(radix: Int): Byte = typeErr()

  /**
   * Attempt to get this value as a [Byte].
   *
   * @return This value as a `byte` or `null` if it cannot be parsed as a
   * [Byte].
   */
  fun asByteOrNull(): Byte? = asByteOrNull(10)

  /**
   * Attempt to get this value as a [Byte].
   *
   * @param radix The radix to be used when parsing the raw value.
   *
   * @return This value as a `byte` or `null` if it cannot be parsed as a
   * [Byte].
   */
  fun asByteOrNull(radix: Int): Byte? = null

  /**
   * Attempt to get this value as a [Short].
   *
   * @return The value as a [Short].
   *
   * @throws NumberFormatException If the value is not a [Short].
   */
  fun asShort(): Short = asShort(10)

  /**
   * Attempt to get this value as a [Short].
   *
   * @param radix The radix to be used when parsing the raw value.
   *
   * @return The value as a [Short].
   *
   * @throws NumberFormatException If the value is not a [Short].
   */
  fun asShort(radix: Int): Short = typeErr()

  /**
   * Attempt to get this value as a [Short].
   *
   * @return This value as a `short` or `null` if it cannot be parsed as a
   * [Short].
   */
  fun asShortOrNull(): Short? = asShortOrNull(10)

  /**
   * Attempt to get this value as a [Short].
   *
   * @param radix The radix to be used when parsing the raw value.
   *
   * @return This value as a `short` or `null` if it cannot be parsed as a
   * [Short].
   */
  fun asShortOrNull(radix: Int): Short? = null

  /**
   * Attempt to get this value as a [Int].
   *
   * @return The value as a [Int].
   *
   * @throws NumberFormatException If the value is not a [Int].
   */
  fun asInt(): Int = asInt(10)

  /**
   * Attempt to get this value as a [Int].
   *
   * @param radix The radix to be used when parsing the raw value.
   *
   * @return The value as a [Int].
   *
   * @throws NumberFormatException If the value is not a [Int].
   */
  fun asInt(radix: Int): Int = typeErr()

  /**
   * Attempt to get this value as a [Int].
   *
   * @return This value as a `int` or `null` if it cannot be parsed as an
   * [Int].
   */
  fun asIntOrNull(): Int? = asIntOrNull(10)

  /**
   * Attempt to get this value as a [Int].
   *
   * @param radix The radix to be used when parsing the raw value.
   *
   * @return This value as a `int` or `null` if it cannot be parsed as an
   * [Int].
   */
  fun asIntOrNull(radix: Int): Int? = null

  /**
   * Attempt to get this value as a [Long].
   *
   * @return The value as a [Long].
   *
   * @throws NumberFormatException If the value is not a [Long].
   */
  fun asLong(): Long = asLong(10)

  /**
   * Attempt to get this value as a [Long].
   *
   * @param radix The radix to be used when parsing the raw value.
   *
   * @return The value as a [Long].
   *
   * @throws NumberFormatException If the value is not a [Long].
   */
  fun asLong(radix: Int): Long = typeErr()

  /**
   * Attempt to get this value as a [Long].
   *
   * @return This value as a `long` or `null` if it cannot be parsed as an
   * [Long].
   */
  fun asLongOrNull(): Long? = asLongOrNull(10)

  /**
   * Attempt to get this value as a [Long].
   *
   * @param radix The radix to be used when parsing the raw value.
   *
   * @return This value as a `long` or `null` if it cannot be parsed as an
   * [Long].
   */
  fun asLongOrNull(radix: Int): Long? = null

  /**
   * Attempt to get this value as a [BigInteger].
   *
   * @return The value as a [BigInteger].
   *
   * @throws NumberFormatException If the value is not a [BigInteger].
   */
  fun asBigInteger(): BigInteger = asBigInteger(10)

  /**
   * Attempt to get this value as a [BigInteger].
   *
   * @param radix The radix to be used when parsing the raw value.
   *
   * @return The value as a [BigInteger].
   *
   * @throws NumberFormatException If the value is not a [BigInteger].
   */
  fun asBigInteger(radix: Int): BigInteger = typeErr()

  /**
   * Attempt to get this value as a [BigInteger].
   *
   * @return This value as a `BigInteger` or `null` if it cannot be parsed as an
   * [BigInteger].
   */
  fun asBigIntegerOrNull(): BigInteger? = asBigIntegerOrNull(10)

  /**
   * Attempt to get this value as a [BigInteger].
   *
   * @param radix The radix to be used when parsing the raw value.
   *
   * @return This value as a `BigInteger` or `null` if it cannot be parsed as an
   * [BigInteger].
   */
  fun asBigIntegerOrNull(radix: Int): BigInteger? = null

  /**
   * Attempt to get this value as a [Float].
   *
   * @return The value as a [Float].
   *
   * @throws NumberFormatException If the value is not a [Float].
   */
  fun asFloat(): Float = typeErr()

  /**
   * Attempt to get this value as a [Float].
   *
   * @return This value as a `float` or `null` if it cannot be parsed as an
   * [Float].
   */
  fun asFloatOrNull(): Float? = null

  /**
   * Attempt to get this value as a [Double].
   *
   * @return The value as a [Double].
   *
   * @throws NumberFormatException If the value is not a [Double].
   */
  fun asDouble(): Double = typeErr()

  /**
   * Attempt to get this value as a [Double].
   *
   * @return This value as a `double` or `null` if it cannot be parsed as an
   * [Double].
   */
  fun asDoubleOrNull(): Double? = null

  /**
   * Attempt to get this value as a [BigDecimal].
   *
   * @return The value as a [BigDecimal].
   *
   * @throws NumberFormatException If the value is not a [BigDecimal].
   */
  fun asBigDecimal(): BigDecimal = typeErr()

  /**
   * Attempt to get this value as a [BigDecimal].
   *
   * @return This value as a `BigDecimal` or `null` if it cannot be parsed as an
   * [BigDecimal].
   */
  fun asBigDecimalOrNull(): BigDecimal? = asBigDecimalOrNull()

  /**
   * Attempt to get this value as a [Boolean].
   *
   * @return The value as a [Boolean].
   */
  fun asBoolean(): Boolean = typeErr()

  /**
   * Attempt to get this value as a [Boolean].
   *
   * @return This value as a `boolean` or `null` if it cannot be parsed as an
   * [Boolean].
   */
  fun asBooleanOrNull(): Boolean? = null

  fun asString(): String = typeErr()

  fun asStringOrNull(): String? = null

  fun <T> asNull(): T? =
    throw ThimbleDeserializationException("wrapped value is not null")

  fun asComplex(): ComplexValueAccessor =
    throw ThimbleDeserializationException("cannot unwrap value as a complex value")

  /**
   * Attempt to get this [ValueAccessor] as a [ComplexValueAccessor].
   *
   * @return This `ValueAccessor` as a `ComplexValueAccessor` or `null` if it
   * does not contain a complex value.
   */
  fun asComplexOrNull(): ComplexValueAccessor? = null
}

private inline fun <reified T : Any> typeErr(): T =
  throw ThimbleDeserializationException("failed to unwrap value as ${T::class.simpleName}")
