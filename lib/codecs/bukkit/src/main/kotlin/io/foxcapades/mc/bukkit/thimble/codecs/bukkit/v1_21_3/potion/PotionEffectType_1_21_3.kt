package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.potion

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.*
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitCodecRegistry.Codec_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.fields.BodyEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldDecoder
import org.bukkit.Registry
import org.bukkit.potion.PotionEffectType
import java.nio.ByteBuffer

internal class PotionEffectType_1_21_3 : BukkitTypeCodec<PotionEffectType> {
  override val version      get() = v1_21_3
  override val javaType     get() = PotionEffectType::class.java
  override val bukkitTypeId get() = BukkitTypeId.PotionEffectType

  override val fieldEncoders get() = iteratorOf(BodyEncoder(PotionEffectType::getKey, Codec_1_21_3.NamespacedKey))
  override val fieldDecoders get() = emptyIterator<FieldDecoder<PotionEffectType>>()

  override fun create(from: ByteBuffer): PotionEffectType =
    Registry.EFFECT.require(Codec_1_21_3.NamespacedKey.create(from))
}
