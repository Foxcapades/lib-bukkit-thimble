package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.meta

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitCodecRegistry.Codec_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitTypeId
import io.foxcapades.mc.bukkit.thimble.codecs.fields.*
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.ListCodecV1
import org.bukkit.Material
import org.bukkit.inventory.meta.CrossbowMeta
import java.io.InputStream

internal class CrossbowMeta_1_21_3 : ItemMetaBase_1_21_3<CrossbowMeta>() {
  override val javaType: Class<out CrossbowMeta>
    get() = CrossbowMeta::class.java

  override val bukkitTypeId get() = BukkitTypeId.CrossbowMeta

  override val fieldEncoders: Iterator<FieldEncoder<CrossbowMeta>>
    get() = (baseEncoders() + arrayOf(
      ConditionalEncoder(
        CrossbowMeta::hasChargedProjectiles,
        CrossbowMeta::getChargedProjectiles,
        ListCodecV1(Codec_1_21_3.ItemStack)
      ),
    )).iterator()

  override val fieldDecoders: Iterator<FieldDecoder<CrossbowMeta>>
    get() = (baseDecoders() + arrayOf(
      NullableDecoder1(CrossbowMeta::setChargedProjectiles, ListCodecV1(Codec_1_21_3.ItemStack)),
    )).iterator()

  override fun create(from: InputStream): CrossbowMeta =
    Material.CROSSBOW.createMeta()
}
