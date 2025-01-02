package io.foxcapades.mc.bukkit.thimble.codecs.bukkit

interface BukkitCodecProvider {
  fun getCodec(type: BukkitTypeId): BukkitTypeCodec<*>?

  fun requireCodec(type: BukkitTypeId): BukkitTypeCodec<*>

  fun getCodec(type: BukkitTypeId, version: BukkitVersion): BukkitTypeCodec<*>?

  fun requireCodec(type: BukkitTypeId, version: BukkitVersion): BukkitTypeCodec<*>

  fun <T: Any> getCodec(type: Class<T>): BukkitTypeCodec<T>?

  fun <T: Any> requireCodec(type: Class<T>): BukkitTypeCodec<T>

  fun <T: Any> getCodec(type: Class<T>, version: BukkitVersion): BukkitTypeCodec<T>?

  fun <T: Any> requireCodec(type: Class<T>, version: BukkitVersion): BukkitTypeCodec<T>
}
