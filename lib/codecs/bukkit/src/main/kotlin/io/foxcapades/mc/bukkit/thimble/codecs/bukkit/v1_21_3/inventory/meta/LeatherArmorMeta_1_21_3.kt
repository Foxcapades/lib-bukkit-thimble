package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.meta

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitCodecRegistry.Codec_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitTypeId
import io.foxcapades.mc.bukkit.thimble.codecs.fields.BodyDecoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.BodyEncoder
import org.bukkit.Material
import org.bukkit.inventory.meta.LeatherArmorMeta
import java.nio.ByteBuffer


internal class LeatherArmorMeta_1_21_3 : ItemMetaBase_1_21_3<LeatherArmorMeta>() {
  override val javaType     get() = LeatherArmorMeta::class.java
  override val bukkitTypeId get() = BukkitTypeId.LeatherArmorMeta

  override val fieldEncoders get() = (baseEncoders() + arrayOf(
    BodyEncoder(LeatherArmorMeta::getColor, Codec_1_21_3.Color)
  )).iterator()

  override val fieldDecoders get() = (baseDecoders() + arrayOf(
    BodyDecoder(LeatherArmorMeta::setColor, Codec_1_21_3.Color)
  )).iterator()

  override fun create(from: ByteBuffer): LeatherArmorMeta =
    Material.LEATHER_CHESTPLATE.createMeta()
}
