package io.foxcapades.mc.bukkit.thimble.codecs.bukkit

import io.foxcapades.mc.bukkit.thimble.codecs.CodecVersion

sealed interface BukkitVersion : CodecVersion {
  override val length: UInt
    get() = 3u

  val major: Int
  val minor: Int
  val patch: Int
}
