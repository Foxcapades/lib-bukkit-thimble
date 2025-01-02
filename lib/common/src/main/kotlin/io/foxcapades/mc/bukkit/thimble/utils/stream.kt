@file:JvmName("OutputStreamExtensions")
package io.foxcapades.mc.bukkit.thimble.utils

import java.io.OutputStream

@Suppress("NOTHING_TO_INLINE")
private inline fun chop(value: Int) = value and 0xFF
@Suppress("NOTHING_TO_INLINE")
private inline fun chop(value: Long) = chop(value.toInt())

fun OutputStream.writeShort(value: Short) {
  with(value.toInt()) {
    write(chop(shr(8)))
    write(chop(this))
  }
}

fun OutputStream.writeInt(value: Int) {
  write(chop(value.shr(24)))
  write(chop(value.shr(16)))
  write(chop(value.shr(8)))
  write(chop(value))
}

fun OutputStream.writeLong(value: Long) {
  write(chop(value.shr(56)))
  write(chop(value.shr(48)))
  write(chop(value.shr(40)))
  write(chop(value.shr(32)))
  write(chop(value.shr(24)))
  write(chop(value.shr(16)))
  write(chop(value.shr(8)))
  write(chop(value))
}
