package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.meta

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitCodecRegistry.Codec_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitTypeId
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldDecoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.NullableDecoder1
import io.foxcapades.mc.bukkit.thimble.codecs.fields.NullableEncoder
import org.bukkit.Material
import org.bukkit.inventory.meta.SkullMeta
import java.io.InputStream

internal class SkullMeta_1_21_3 : ItemMetaBase_1_21_3<SkullMeta>() {
  override val javaType: Class<out SkullMeta>
    get() = SkullMeta::class.java

  override val bukkitTypeId: BukkitTypeId
    get() = BukkitTypeId.SkullMeta

  override val fieldEncoders: Iterator<FieldEncoder<SkullMeta>>
    get() = (baseEncoders() + arrayOf(
      NullableEncoder(SkullMeta::getOwningPlayer, Codec_1_21_3.OfflinePlayer),
      NullableEncoder(SkullMeta::getOwnerProfile, Codec_1_21_3.PlayerProfile),
      NullableEncoder(SkullMeta::getNoteBlockSound, Codec_1_21_3.NamespacedKey),
    )).iterator()

  override val fieldDecoders: Iterator<FieldDecoder<SkullMeta>>
    get() = (baseDecoders() + arrayOf(
      NullableDecoder1(SkullMeta::setOwningPlayer, Codec_1_21_3.OfflinePlayer),
      NullableDecoder1(SkullMeta::setOwnerProfile, Codec_1_21_3.PlayerProfile),
      NullableDecoder1(SkullMeta::setNoteBlockSound, Codec_1_21_3.NamespacedKey),
    )).iterator()

  override fun create(from: InputStream): SkullMeta =
    Material.PLAYER_HEAD.createMeta()
}
