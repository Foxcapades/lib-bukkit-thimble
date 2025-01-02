package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.entity

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.*
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitCodecRegistry.Codec_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.fields.BodyEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldDecoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldEncoder

import org.bukkit.Registry
import org.bukkit.entity.EntityType

import java.nio.ByteBuffer

internal class EntityType_1_21_3 : BukkitTypeCodec<EntityType> {
  override val version: BukkitVersion
    get() = v1_21_3

  override val javaType: Class<out EntityType>
    get() = EntityType::class.java

  override val bukkitTypeId get() = BukkitTypeId.EntityType

  override val fieldEncoders: Iterator<FieldEncoder<EntityType>>
    get() = iteratorOf(BodyEncoder(EntityType::getKey, Codec_1_21_3.NamespacedKey))

  override val fieldDecoders: Iterator<FieldDecoder<EntityType>>
    get() = emptyIterator()

  override fun create(from: ByteBuffer): EntityType =
    Registry.ENTITY_TYPE.require(Codec_1_21_3.NamespacedKey.create(from))
}
