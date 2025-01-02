package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.meta

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitCodecRegistry.Codec_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitTypeId
import io.foxcapades.mc.bukkit.thimble.codecs.fields.BodyDecoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.BodyEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.EnumByteBodyDecoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.EnumByteBodyEncoder
import org.bukkit.Material
import org.bukkit.entity.TropicalFish
import org.bukkit.inventory.meta.TropicalFishBucketMeta
import java.nio.ByteBuffer

internal class TropicalFishBucketMeta_1_21_3 : ItemMetaBase_1_21_3<TropicalFishBucketMeta>() {
  private val enumIndex get() = arrayOf(
    TropicalFish.Pattern.KOB,
    TropicalFish.Pattern.SUNSTREAK,
    TropicalFish.Pattern.SNOOPER,
    TropicalFish.Pattern.DASHER,
    TropicalFish.Pattern.BRINELY,
    TropicalFish.Pattern.SPOTTY,
    TropicalFish.Pattern.FLOPPER,
    TropicalFish.Pattern.STRIPEY,
    TropicalFish.Pattern.GLITTER,
    TropicalFish.Pattern.BLOCKFISH,
    TropicalFish.Pattern.BETTY,
    TropicalFish.Pattern.CLAYFISH,
  )

  override val javaType     get() = TropicalFishBucketMeta::class.java
  override val bukkitTypeId get() = BukkitTypeId.TropicalFishBucketMeta

  override val fieldEncoders get() = (baseEncoders() + arrayOf(
    BodyEncoder(TropicalFishBucketMeta::getPatternColor, Codec_1_21_3.DyeColor),
    BodyEncoder(TropicalFishBucketMeta::getBodyColor, Codec_1_21_3.DyeColor),
    EnumByteBodyEncoder(TropicalFishBucketMeta::getPattern, enumIndex),
  )).iterator()

  override val fieldDecoders get() = (baseDecoders() + arrayOf(
    BodyDecoder(TropicalFishBucketMeta::setPatternColor, Codec_1_21_3.DyeColor),
    BodyDecoder(TropicalFishBucketMeta::setBodyColor, Codec_1_21_3.DyeColor),
    EnumByteBodyDecoder(TropicalFishBucketMeta::setPattern, enumIndex),
  )).iterator()

  override fun create(from: ByteBuffer): TropicalFishBucketMeta =
    Material.TROPICAL_FISH_BUCKET.createMeta()
}
