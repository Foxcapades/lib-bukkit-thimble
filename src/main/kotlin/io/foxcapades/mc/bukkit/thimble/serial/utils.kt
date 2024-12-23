@file:Suppress("NOTHING_TO_INLINE")

package io.foxcapades.mc.bukkit.thimble.serial

import java.io.EOFException
import java.io.InputStream
import java.io.OutputStream

// region Writing

internal inline fun OutputStream.writeType(type: ValueType) {
  write(type.identifier)
}

// endregion Writing
