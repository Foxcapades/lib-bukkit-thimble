@file:JvmName("ThimbleIO")
@file:Suppress("NOTHING_TO_INLINE")
package io.foxcapades.mc.bukkit.thimble.io

import io.foxcapades.mc.bukkit.thimble.ThimbleDeserializationException
import io.foxcapades.mc.bukkit.thimble.types.DataType
import java.io.InputStream

inline fun InputStream.readDataType(): DataType =
  DataType.fromTag(readU8())

inline fun badNullableTypeError(expect: DataType, got: DataType) =
  ThimbleDeserializationException("expected $expect or null, got $got")
