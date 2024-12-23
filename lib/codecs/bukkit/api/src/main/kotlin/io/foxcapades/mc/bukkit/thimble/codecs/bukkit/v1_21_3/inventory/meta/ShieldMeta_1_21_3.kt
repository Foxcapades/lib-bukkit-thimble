package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.meta

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitCodecRegistry.Codec_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitTypeId
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldDecoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.NullableDecoder1
import io.foxcapades.mc.bukkit.thimble.codecs.fields.NullableEncoder
import org.bukkit.Material
import org.bukkit.inventory.meta.ShieldMeta
import java.io.InputStream

internal class ShieldMeta_1_21_3 : BannerMetaBase_1_21_3<ShieldMeta>() {
  override val javaType: Class<out ShieldMeta>
    get() = ShieldMeta::class.java

  override val bukkitTypeId: BukkitTypeId
    get() = BukkitTypeId.ShieldMeta

  override val fieldEncoders: Iterator<FieldEncoder<ShieldMeta>>
    get() = (baseEncoders() + arrayOf(
      NullableEncoder(ShieldMeta::getBaseColor, Codec_1_21_3.DyeColor)
    )).iterator()

  override val fieldDecoders: Iterator<FieldDecoder<ShieldMeta>>
    get() = (baseDecoders() + arrayOf(
      NullableDecoder1(ShieldMeta::setBaseColor, Codec_1_21_3.DyeColor)
    )).iterator()

  override fun create(from: InputStream): ShieldMeta =
    Material.SHIELD.createMeta()
}
