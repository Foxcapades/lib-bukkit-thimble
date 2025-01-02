package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.meta

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitCodecRegistry.Codec_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitTypeId
import io.foxcapades.mc.bukkit.thimble.codecs.fields.ConditionalEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.NullableDecoder1
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.ListCodecV1
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.StringCodecV1
import org.bukkit.Material
import org.bukkit.inventory.meta.PotionMeta
import org.bukkit.potion.PotionEffect
import java.nio.ByteBuffer

internal class PotionMeta_1_21_3 : ItemMetaBase_1_21_3<PotionMeta>() {
  override val javaType     get() = PotionMeta::class.java
  override val bukkitTypeId get() = BukkitTypeId.PotionMeta

  override val fieldEncoders get() = (baseEncoders() + arrayOf(
    ConditionalEncoder(PotionMeta::getBasePotionType, Codec_1_21_3.PotionType::enforceNN, PotionMeta::hasBasePotionType),
    ConditionalEncoder(
      PotionMeta::hasCustomEffects,
      PotionMeta::getCustomEffects,
      ListCodecV1(Codec_1_21_3.PotionEffect)
    ),
    ConditionalEncoder(PotionMeta::getColor, Codec_1_21_3.Color::enforceNN, PotionMeta::hasColor),
    ConditionalEncoder(PotionMeta::getCustomName, StringCodecV1::enforceNN, PotionMeta::hasCustomName)
  )).iterator()

  override val fieldDecoders get() = (baseDecoders() + arrayOf(
    NullableDecoder1(PotionMeta::setBasePotionType, Codec_1_21_3.PotionType),
    NullableDecoder1(::addEffects, ListCodecV1(Codec_1_21_3.PotionEffect)),
    NullableDecoder1(PotionMeta::setColor, Codec_1_21_3.Color),
    NullableDecoder1(PotionMeta::setCustomName, StringCodecV1),
  )).iterator()

  override fun create(from: ByteBuffer): PotionMeta =
    Material.POTION.createMeta()

  private fun addEffects(meta: PotionMeta, effects: List<PotionEffect>) {
    effects.forEach { meta.addCustomEffect(it, true) }
  }
}
