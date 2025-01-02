package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.meta

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitCodecRegistry.Codec_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitTypeId
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldDecoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.NullableDecoder1
import io.foxcapades.mc.bukkit.thimble.codecs.fields.NullableEncoder
import io.foxcapades.mc.bukkit.thimble.unsafe.SpawnEggMeta
import org.bukkit.inventory.meta.SpawnEggMeta
import java.nio.ByteBuffer


internal class SpawnEggMeta_1_21_3 : ItemMetaBase_1_21_3<SpawnEggMeta>() {
  override val javaType: Class<out SpawnEggMeta>
    get() = SpawnEggMeta::class.java

  override val bukkitTypeId: BukkitTypeId
    get() = BukkitTypeId.SpawnEggMeta

  override val fieldEncoders: Iterator<FieldEncoder<SpawnEggMeta>>
    get() = (baseEncoders() + arrayOf(
      NullableEncoder(SpawnEggMeta::getSpawnedEntity, Codec_1_21_3.EntitySnapshot)
    )).iterator()

  override val fieldDecoders: Iterator<FieldDecoder<SpawnEggMeta>>
    get() = (baseDecoders() + arrayOf(
      NullableDecoder1(SpawnEggMeta::setSpawnedEntity, Codec_1_21_3.EntitySnapshot)
    )).iterator()

  override fun create(from: ByteBuffer): SpawnEggMeta = SpawnEggMeta()
}


