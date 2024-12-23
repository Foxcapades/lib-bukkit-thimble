package io.foxcapades.mc.bukkit.thimble.codecs.fields

import io.foxcapades.mc.bukkit.thimble.types.DataType
import java.io.InputStream

interface FieldDecoder<T> {
  val name: String
  
  val dataType: DataType

  fun decode(from: InputStream, into: T, offset: UInt = 0u)
}
