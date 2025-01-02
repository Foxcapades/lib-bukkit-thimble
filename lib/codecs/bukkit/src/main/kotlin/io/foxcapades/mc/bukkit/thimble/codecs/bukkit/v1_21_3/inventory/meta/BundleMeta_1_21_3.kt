package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.meta

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitCodecRegistry.Codec_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitTypeId
import io.foxcapades.mc.bukkit.thimble.codecs.fields.ConditionalEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.NullableDecoder1
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.ListCodecV1
import org.bukkit.Material
import org.bukkit.inventory.meta.BundleMeta
import java.nio.ByteBuffer

@Suppress("UnstableApiUsage")
internal class BundleMeta_1_21_3 : ItemMetaBase_1_21_3<BundleMeta>() {
  override val javaType
    get() = BundleMeta::class.java

  override val bukkitTypeId
    get() = BukkitTypeId.BundleMeta

  override val fieldEncoders
    get() = (baseEncoders() + arrayOf(
      ConditionalEncoder(BundleMeta::hasItems, BundleMeta::getItems, ListCodecV1(Codec_1_21_3.ItemStack)),
    )).iterator()

  override val fieldDecoders
    get() = (baseDecoders() + arrayOf(
      NullableDecoder1(BundleMeta::setItems, ListCodecV1(Codec_1_21_3.ItemStack)),
    )).iterator()

  override fun create(from: ByteBuffer): BundleMeta =
    Material.BUNDLE.createMeta()
}
