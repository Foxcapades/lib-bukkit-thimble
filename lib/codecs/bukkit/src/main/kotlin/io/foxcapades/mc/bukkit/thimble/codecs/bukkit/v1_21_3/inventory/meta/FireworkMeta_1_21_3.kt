package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.meta

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitCodecRegistry.Codec_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitTypeId
import io.foxcapades.mc.bukkit.thimble.codecs.fields.ConditionalEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldDecoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.NullableDecoder1
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.IntCodec
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.ListCodecV1
import org.bukkit.Material
import org.bukkit.inventory.meta.FireworkMeta
import java.nio.ByteBuffer

internal class FireworkMeta_1_21_3 : ItemMetaBase_1_21_3<FireworkMeta>() {
  override val javaType: Class<out FireworkMeta>
    get() = FireworkMeta::class.java

  override val bukkitTypeId get() = BukkitTypeId.FireworkMeta

  override val fieldEncoders: Iterator<FieldEncoder<FireworkMeta>>
    get() = (baseEncoders() + arrayOf(
      ConditionalEncoder(FireworkMeta::hasPower, FireworkMeta::getPower, IntCodec),
      ConditionalEncoder(FireworkMeta::hasEffects, FireworkMeta::getEffects, ListCodecV1(Codec_1_21_3.FireworkEffect))
    )).iterator()

  override val fieldDecoders: Iterator<FieldDecoder<FireworkMeta>>
    get() = (baseDecoders() + arrayOf(
      NullableDecoder1(FireworkMeta::setPower, IntCodec),
      NullableDecoder1(FireworkMeta::addEffects, ListCodecV1(Codec_1_21_3.FireworkEffect)),
    )).iterator()

  override fun create(from: ByteBuffer): FireworkMeta =
    Material.FIREWORK_ROCKET.createMeta()
}
