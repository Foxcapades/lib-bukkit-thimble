package io.foxcapades.mc.bukkit.thimble.codecs.fields

import io.foxcapades.mc.bukkit.thimble.DataType
import java.nio.ByteBuffer

interface FieldDecoder<T> {
  val name: String

  val dataType: DataType

  fun decode(from: ByteBuffer, into: T, offset: UInt = 0u)
}
