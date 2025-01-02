package io.foxcapades.mc.bukkit.thimble.codecs.bukkit

import io.foxcapades.mc.bukkit.thimble.ThimbleException

interface VersionedBukkitCodecProvider {
  val version: BukkitVersion

  fun getCodec(forType: BukkitTypeId): BukkitTypeCodec<*>?

  fun requireCodec(forType: BukkitTypeId): BukkitTypeCodec<*> =
    getCodec(forType) ?: throw ThimbleException("no codec found for type $forType and bukkit version $version")
}
