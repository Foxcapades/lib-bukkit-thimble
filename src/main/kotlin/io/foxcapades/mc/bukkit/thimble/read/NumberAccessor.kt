package io.foxcapades.mc.bukkit.thimble.read

@JvmInline
internal value class NumberAccessor(val rawValue: String) : ValueAccessor {
  override val isNumber: Boolean
    get() = true

  override fun asByte(radix: Int) = rawValue.toByte(radix)

  override fun asByteOrNull(radix: Int) = rawValue.toByteOrNull(radix)

  override fun asShort(radix: Int) = rawValue.toShort(radix)

  override fun asShortOrNull(radix: Int) = rawValue.toShortOrNull(radix)

  override fun asInt(radix: Int) = rawValue.toInt(radix)

  override fun asIntOrNull(radix: Int) = rawValue.toIntOrNull(radix)

  override fun asLong(radix: Int) = rawValue.toLong(radix)

  override fun asLongOrNull(radix: Int) = rawValue.toLongOrNull(radix)

  override fun asBigInteger(radix: Int) = rawValue.toBigInteger(radix)

  override fun asBigIntegerOrNull(radix: Int) = rawValue.toBigInteger(radix)

  override fun asFloat() = rawValue.toFloat()

  override fun asFloatOrNull() = rawValue.toFloatOrNull()

  override fun asDouble() = rawValue.toDouble()

  override fun asDoubleOrNull() = rawValue.toDoubleOrNull()

  override fun asBigDecimal() = rawValue.toBigDecimal()

  override fun asBigDecimalOrNull() = rawValue.toBigDecimalOrNull()
}
