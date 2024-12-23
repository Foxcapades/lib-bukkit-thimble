package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.meta

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitCodecRegistry.Codec_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitTypeId
import io.foxcapades.mc.bukkit.thimble.codecs.fields.ConditionalEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldDecoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.NullableDecoder1
import org.bukkit.Material
import org.bukkit.inventory.meta.FireworkEffectMeta
import java.io.InputStream

internal class FireworkEffectMeta_1_21_3 : ItemMetaBase_1_21_3<FireworkEffectMeta>() {
  override val javaType: Class<out FireworkEffectMeta>
    get() = FireworkEffectMeta::class.java

  override val bukkitTypeId get() = BukkitTypeId.FireworkEffectMeta

  override val fieldEncoders: Iterator<FieldEncoder<FireworkEffectMeta>>
    get() = (baseEncoders() + arrayOf(
      ConditionalEncoder(
        FireworkEffectMeta::getEffect,
        Codec_1_21_3.FireworkEffect::enforceNN,
        FireworkEffectMeta::hasEffect
      )
    )).iterator()

  override val fieldDecoders: Iterator<FieldDecoder<FireworkEffectMeta>>
    get() = (baseDecoders() + arrayOf(
      NullableDecoder1(FireworkEffectMeta::setEffect, Codec_1_21_3.FireworkEffect),
    )).iterator()

  override fun create(from: InputStream): FireworkEffectMeta =
    Material.FIRE_CHARGE.createMeta()
}
