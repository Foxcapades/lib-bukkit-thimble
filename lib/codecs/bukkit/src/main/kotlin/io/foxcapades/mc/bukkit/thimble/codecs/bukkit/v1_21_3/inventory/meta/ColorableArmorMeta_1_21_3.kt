package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.meta

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitCodecRegistry.Codec_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitTypeId
import io.foxcapades.mc.bukkit.thimble.codecs.fields.*
import io.foxcapades.mc.bukkit.thimble.unsafe.ColorableArmorMeta
import org.bukkit.inventory.meta.ColorableArmorMeta
import java.nio.ByteBuffer

internal class ColorableArmorMeta_1_21_3 : ItemMetaBase_1_21_3<ColorableArmorMeta>() {
  override val javaType: Class<out ColorableArmorMeta>
    get() = ColorableArmorMeta::class.java

  override val bukkitTypeId get() = BukkitTypeId.ColorableArmorMeta

  override val fieldEncoders: Iterator<FieldEncoder<ColorableArmorMeta>>
    get() = (baseEncoders() + arrayOf(
      ConditionalEncoder(ColorableArmorMeta::getTrim, Codec_1_21_3.ArmorTrim::enforceNN, ColorableArmorMeta::hasTrim),
      BodyEncoder(ColorableArmorMeta::getColor, Codec_1_21_3.Color),
    )).iterator()

  override val fieldDecoders: Iterator<FieldDecoder<ColorableArmorMeta>>
    get() = (baseDecoders() + arrayOf(
      NullableDecoder1(ColorableArmorMeta::setTrim, Codec_1_21_3.ArmorTrim),
      BodyDecoder(ColorableArmorMeta::setColor, Codec_1_21_3.Color),
    )).iterator()

  override fun create(from: ByteBuffer): ColorableArmorMeta =
    ColorableArmorMeta()
}
