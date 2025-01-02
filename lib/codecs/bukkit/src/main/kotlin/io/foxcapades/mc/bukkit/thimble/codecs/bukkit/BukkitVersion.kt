package io.foxcapades.mc.bukkit.thimble.codecs.bukkit

import io.foxcapades.mc.bukkit.thimble.codecs.CodecVersion

sealed interface BukkitVersion : CodecVersion {
  override val length: Int
    get() = 3

  val major: Int
  val minor: Int
  val patch: Int
}
