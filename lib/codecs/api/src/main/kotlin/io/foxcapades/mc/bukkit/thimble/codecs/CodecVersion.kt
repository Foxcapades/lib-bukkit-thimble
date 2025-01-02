package io.foxcapades.mc.bukkit.thimble.codecs

import java.io.OutputStream

interface CodecVersion {
  val length: Int

  fun writeTo(into: OutputStream)
}
