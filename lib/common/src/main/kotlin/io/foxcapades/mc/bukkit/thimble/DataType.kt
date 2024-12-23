package io.foxcapades.mc.bukkit.thimble.types

enum class DataType {
  Null,
  Boolean,
  Byte,
  Short,
  Int,
  Long,
  Float,
  Double,
  String,
  BigInteger,
  BigDecimal,
  Sequence,
  Dictionary,
  Bukkit,
  Complex,
  Compound,
  Unknown,
  ;

  val isNull
    get() = this == Null

  val isPrimitive
    get() = ordinal in Boolean.ordinal .. Double.ordinal

  val identifier
    get() = ordinal.toUByte()

  companion object {
    @JvmStatic
    fun fromTag(tag: UByte): DataType =
      fromTagOrNull(tag) ?: throw IllegalArgumentException("unrecognized type tag: $tag")

    @JvmStatic
    fun fromTagOrNull(tag: UByte): DataType? =
      when (tag) {
        in Null.identifier .. Unknown.identifier -> entries[tag.toInt()]
        else -> null
      }
  }
}
