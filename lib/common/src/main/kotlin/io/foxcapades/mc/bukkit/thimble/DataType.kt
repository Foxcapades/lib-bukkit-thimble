package io.foxcapades.mc.bukkit.thimble

import io.foxcapades.mc.bukkit.thimble.utils.exert
import kotlin.experimental.and


@JvmInline
value class DataType private constructor(val rawValue: Byte) {
  inline val kind get() = DataKind.entries[(rawValue and 3).toInt()]

  inline val scalarType get() = exert(kind == DataKind.Scalar) { ScalarType.entries[rawValue.toInt() shl 2] }

  inline val recordType get() = exert(kind == DataKind.Record) { RecordType.entries[rawValue.toInt() shl 2] }

  inline val isScalar get() = kind == DataKind.Scalar

  inline val isRecord get() = kind == DataKind.Record

  inline val isUnknown get() = kind == DataKind.Unknown

  override fun toString() =
    when (kind) {
      DataKind.Scalar -> "Scalar:$scalarType"
      DataKind.Record -> "Record:$recordType"
      else -> "Unknown"
    }

  companion object {
    fun Scalar(type: ScalarType) = DataType(type.ordinal.shr(2).and(DataKind.Scalar.ordinal).toByte())

    fun Record(type: RecordType) = DataType(type.ordinal.shr(2).and(DataKind.Record.ordinal).toByte())

    inline val Unknown get() = DataType.ofRaw(DataKind.Unknown.ordinal.toByte())

    fun ofRaw(raw: Byte) = DataType(raw)
  }
}
