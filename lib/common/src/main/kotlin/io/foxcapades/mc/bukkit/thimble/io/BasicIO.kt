@file:JvmName("BasicIO")
@file:Suppress("NOTHING_TO_INLINE")
package io.foxcapades.mc.bukkit.thimble.io

import io.foxcapades.mc.bukkit.thimble.ThimbleDeserializationException
import java.io.EOFException
import java.io.InputStream
import java.io.OutputStream


inline fun InputStream.mustRead(): Int = when (val v = read()) {
  -1   -> throw ThimbleDeserializationException("unexpected EOF")
  else -> v
}

inline fun InputStream.mustRead(buffer: ByteArray) {
  var red = 0
  while (red < buffer.size) {
    red = read(buffer, red, buffer.size - red)
    if (red < 0)
      throw EOFException("unexpected EOF")
  }
}

inline fun InputStream.readI8(): Byte =
  mustRead().toByte()

inline fun InputStream.readI16(): Short =
  mustRead()
    .shl(8).or(mustRead())
    .toShort()

inline fun InputStream.readI32(): Int =
  mustRead()
    .shl(8).or(mustRead())
    .shl(8).or(mustRead())
    .shl(8).or(mustRead())

inline fun InputStream.readI64(): Long =
  readI32().toLong()
    .shl(32).or(readI32().toLong())

inline fun InputStream.readU8(): UByte =
  mustRead().toUByte()

inline fun InputStream.readU16(): UShort =
  mustRead().shl(8).or(mustRead()).toUShort()

inline fun InputStream.readU16I() =
  mustRead().shl(8).or(mustRead())

//
//
//

inline fun Int.chop() = and(0xFF)

inline fun Long.chop() = toInt().chop()

inline fun OutputStream.writeI8(value: Byte) =
  write(value.toInt())

@Suppress("NAME_SHADOWING")
inline fun OutputStream.writeI16(value: Short) {
  val value = value.toInt()
  write(value.shr(8).chop())
  write(value.chop())
}

inline fun OutputStream.writeI32(value: Int) {
  write(value.shr(24).chop())
  write(value.shr(16).chop())
  write(value.shr(8).chop())
  write(value.chop())
}

inline fun OutputStream.writeI64(value: Long) {
  write(value.shr(56).chop())
  write(value.shr(48).chop())
  write(value.shr(40).chop())
  write(value.shr(32).chop())
  write(value.shr(24).chop())
  write(value.shr(16).chop())
  write(value.shr(8).chop())
  write(value.chop())
}

inline fun OutputStream.writeU8(value: UByte) {
  write(value.toInt())
}

inline fun OutputStream.writeU16(value: Int) {
  write(value.and(0xFF00).ushr(8))
  write(value.and(0xFF))
}
