package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.meta

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitTypeId
import io.foxcapades.mc.bukkit.thimble.codecs.fields.ConditionalEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.NullableDecoder1
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.IntCodec
import org.bukkit.Material
import org.bukkit.inventory.meta.OminousBottleMeta
import java.nio.ByteBuffer

internal class OminousBottleMeta_1_21_3 : ItemMetaBase_1_21_3<OminousBottleMeta>() {
  override val javaType     get() = OminousBottleMeta::class.java
  override val bukkitTypeId get() = BukkitTypeId.OminousBottleMeta

  override val fieldEncoders get() = (baseEncoders() + arrayOf(
    ConditionalEncoder(OminousBottleMeta::hasAmplifier, OminousBottleMeta::getAmplifier, IntCodec)
  )).iterator()

  override val fieldDecoders get() = (baseDecoders() + arrayOf(
    NullableDecoder1(OminousBottleMeta::setAmplifier, IntCodec)
  )).iterator()

  override fun create(from: ByteBuffer) = Material.OMINOUS_BOTTLE.createMeta()
}
