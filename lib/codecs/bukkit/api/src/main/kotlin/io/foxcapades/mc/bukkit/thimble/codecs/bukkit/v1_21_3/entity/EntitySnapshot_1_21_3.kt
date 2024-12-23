package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.entity

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.*
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitCodecRegistry.Codec_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.fields.BodyEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldDecoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.ByteArrayCodecV1
import io.foxcapades.mc.bukkit.thimble.unsafe.EntitySnapshot
import io.foxcapades.mc.bukkit.thimble.unsafe.getNBT
import org.bukkit.entity.EntitySnapshot
import java.io.InputStream

internal class EntitySnapshot_1_21_3 : BukkitTypeCodec<EntitySnapshot> {
  override val version: BukkitVersion
    get() = v1_21_3

  override val javaType: Class<out EntitySnapshot>
    get() = EntitySnapshot::class.java

  override val bukkitTypeId get() = BukkitTypeId.EntitySnapshot

  override val fieldEncoders: Iterator<FieldEncoder<EntitySnapshot>>
    get() = iteratorOf(
      BodyEncoder(EntitySnapshot::getEntityType, Codec_1_21_3.EntityType),
      BodyEncoder(EntitySnapshot::getNBT, ByteArrayCodecV1)
    )

  override val fieldDecoders: Iterator<FieldDecoder<EntitySnapshot>>
    get() = emptyIterator()

  override fun create(from: InputStream): EntitySnapshot =
    EntitySnapshot(Codec_1_21_3.EntityType.create(from), ByteArrayCodecV1.create(from))
}
