package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.damage

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.*
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitCodecRegistry.Codec_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.fields.BodyEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldDecoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldEncoder

import org.bukkit.Registry
import org.bukkit.damage.DamageType

import java.nio.ByteBuffer

@Suppress("UnstableApiUsage")
internal class DamageType_1_21_3 : BukkitTypeCodec<DamageType> {
  override val version: BukkitVersion
    get() = v1_21_3

  override val javaType: Class<out DamageType>
    get() = DamageType::class.java

  override val bukkitTypeId get() = BukkitTypeId.DamageType

  override val fieldEncoders: Iterator<FieldEncoder<DamageType>>
    get() = iteratorOf(BodyEncoder(DamageType::getKey, Codec_1_21_3.NamespacedKey))

  override val fieldDecoders: Iterator<FieldDecoder<DamageType>>
    get() = emptyIterator()

  override fun create(from: ByteBuffer): DamageType =
    Registry.DAMAGE_TYPE.require(Codec_1_21_3.NamespacedKey.create(from))
}
