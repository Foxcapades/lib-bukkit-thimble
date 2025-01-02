package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.meta.trim

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.*
import io.foxcapades.mc.bukkit.thimble.codecs.fields.BodyEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldDecoder
import org.bukkit.Registry
import org.bukkit.inventory.meta.trim.TrimMaterial
import java.nio.ByteBuffer

internal class TrimMaterial_1_21_3 : BukkitTypeCodec<TrimMaterial> {
  override val version       get() = v1_21_3
  override val javaType      get() = TrimMaterial::class.java
  override val bukkitTypeId  get() = BukkitTypeId.TrimMaterial
  override val fieldEncoders get() =
    iteratorOf(BodyEncoder(TrimMaterial::getKey, BukkitCodecRegistry.Codec_1_21_3.NamespacedKey))

  override val fieldDecoders get() = emptyIterator<FieldDecoder<TrimMaterial>>()

  @Suppress("UnstableApiUsage")
  override fun create(from: ByteBuffer): TrimMaterial =
    Registry.TRIM_MATERIAL.require(BukkitCodecRegistry.Codec_1_21_3.NamespacedKey.create(from))
}
