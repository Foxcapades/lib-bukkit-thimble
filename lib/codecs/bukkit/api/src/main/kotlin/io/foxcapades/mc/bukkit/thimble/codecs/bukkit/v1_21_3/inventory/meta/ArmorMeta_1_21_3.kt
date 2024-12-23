package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.meta

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitCodecRegistry.Codec_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitTypeId
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.enforceNN
import io.foxcapades.mc.bukkit.thimble.codecs.fields.ConditionalEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldDecoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.NullableDecoder1
import org.bukkit.Material
import org.bukkit.inventory.meta.ArmorMeta
import java.io.InputStream


internal class ArmorMeta_1_21_3 : ItemMetaBase_1_21_3<ArmorMeta>() {
  override val javaType: Class<out ArmorMeta>
    get() = ArmorMeta::class.java

  override val bukkitTypeId get() = BukkitTypeId.ArmorMeta

  override val fieldEncoders: Iterator<FieldEncoder<ArmorMeta>>
    get() = (baseEncoders() + arrayOf(
      ConditionalEncoder(ArmorMeta::hasTrim, ArmorMeta::getTrim, Codec_1_21_3.ArmorTrim::enforceNN)
    )).iterator()

  override val fieldDecoders: Iterator<FieldDecoder<ArmorMeta>>
    get() = (baseDecoders() + arrayOf(
      NullableDecoder1(ArmorMeta::setTrim, Codec_1_21_3.ArmorTrim)
    )).iterator()

  override fun create(from: InputStream): ArmorMeta =
    Material.IRON_CHESTPLATE.createMeta()
}

