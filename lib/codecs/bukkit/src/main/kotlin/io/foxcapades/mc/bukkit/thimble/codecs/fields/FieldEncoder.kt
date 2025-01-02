package io.foxcapades.mc.bukkit.thimble.codecs.fields

import java.io.OutputStream

interface FieldEncoder<T> {
  val name: String

  fun encode(stream: OutputStream, instance: T)
}
