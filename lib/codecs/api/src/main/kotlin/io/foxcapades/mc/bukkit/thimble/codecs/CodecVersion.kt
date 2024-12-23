package io.foxcapades.mc.bukkit.thimble.codecs

import java.io.OutputStream

interface CodecVersion {
  val length: UInt

  fun writeTo(into: OutputStream)
}
