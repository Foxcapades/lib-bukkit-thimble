package io.foxcapades.mc.bukkit.thimble.codecs.bukkit

import io.foxcapades.mc.bukkit.thimble.codecs.VersionedCodecProvider
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.CodecRegistry_1_21_3
import io.foxcapades.mc.bukkit.thimble.types.DataType
import java.io.InputStream

object BukkitCodecRegistry : VersionedCodecProvider<BukkitTypeCodec<*>, BukkitVersion> {
  internal val Codec_1_21_3 by lazy { CodecRegistry_1_21_3() }

  override fun getCodec(forType: BukkitTypeId, forVersion: BukkitVersion): BukkitTypeCodec<*>? {
    return when (forVersion) {
      Codec_1_21_3. -> Codec_1_21_3
    }
  }

  override fun getCodec(forType: DataType, input: InputStream): BukkitTypeCodec<*>? {
    if (forType != DataType.Bukkit)
      throw IllegalArgumentException("unsupported data type: $forType")
    return getCodec(forType, BukkitVersionCodec.decode(input), input)
  }

  override fun getCodec(forType: DataType, forVersion: BukkitVersion, input: InputStream): BukkitTypeCodec<*>? {
    if (forType != DataType.Bukkit)
      throw IllegalArgumentException("unsupported data type: $forType")

    return when (forVersion) {
      v1_21_3 -> Codec_1_21_3.getCodec(input)
      else    -> throw IllegalStateException("unsupported Bukkit version: $forVersion")
    }
  }
}
