package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.meta

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitCodecRegistry.Codec_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.fields.BodyDecoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.BodyEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.ListCodecV1
import org.bukkit.inventory.meta.BannerMeta

private val patternCodec = ListCodecV1(Codec_1_21_3.Pattern)

internal abstract class BannerMetaBase_1_21_3<T : BannerMeta> : ItemMetaBase_1_21_3<T>() {
  override fun baseEncoders() =
    super.baseEncoders() + arrayOf(BodyEncoder(BannerMeta::getPatterns, patternCodec))

  override fun baseDecoders() =
    super.baseDecoders() + arrayOf(BodyDecoder(BannerMeta::setPatterns, patternCodec))
}
