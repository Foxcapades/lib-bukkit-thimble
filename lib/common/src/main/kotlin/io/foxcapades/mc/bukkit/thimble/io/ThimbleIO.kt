@file:JvmName("ThimbleIO")
@file:Suppress("NOTHING_TO_INLINE")
package io.foxcapades.mc.bukkit.thimble.io

import io.foxcapades.mc.bukkit.thimble.ThimbleDeserializationException
import io.foxcapades.mc.bukkit.thimble.DataType
import java.nio.BufferUnderflowException
import java.nio.ByteBuffer

inline fun ByteBuffer.mustGetByte(): Byte =
  try { get() } catch (e: BufferUnderflowException) { throw ThimbleDeserializationException("unexpected EOF") }

inline fun ByteBuffer.mustGetShort(): Short =
  try { getShort() } catch (e: BufferUnderflowException) { throw ThimbleDeserializationException("unexpected EOF") }

inline fun ByteBuffer.mustGetInt(): Int =
  try { getInt() } catch (e: BufferUnderflowException) { throw ThimbleDeserializationException("unexpected EOF") }

inline fun ByteBuffer.mustGetLong(): Long =
  try { getLong() } catch (e: BufferUnderflowException) { throw ThimbleDeserializationException("unexpected EOF") }

inline fun ByteBuffer.readDataType(): DataType =
  DataType.ofRaw(mustGetByte())

internal inline fun badNullableTypeError(expect: DataType, got: DataType) =
  ThimbleDeserializationException("expected $expect or null, got $got")
