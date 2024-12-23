package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.meta.components

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.*
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitCodecRegistry.Codec_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.fields.BodyEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldDecoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.NullableEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.FloatCodec
import io.foxcapades.mc.bukkit.thimble.unsafe.UseCooldownComponent
import org.bukkit.inventory.meta.components.UseCooldownComponent
import java.io.InputStream

@Suppress("UnstableApiUsage")
internal class UseCooldownComponent_1_21_3 : BukkitTypeCodec<UseCooldownComponent> {
  override val version       get() = v1_21_3
  override val javaType      get() = UseCooldownComponent::class.java
  override val bukkitTypeId  get() = BukkitTypeId.UseCooldownComponent
  override val fieldEncoders get() = iteratorOf(
    BodyEncoder(UseCooldownComponent::getCooldownSeconds, FloatCodec),
    NullableEncoder(UseCooldownComponent::getCooldownGroup, Codec_1_21_3.NamespacedKey),
  )
  override val fieldDecoders get() = emptyIterator<FieldDecoder<UseCooldownComponent>>()

  override fun create(from: InputStream): UseCooldownComponent =
    UseCooldownComponent(FloatCodec.create(from), Codec_1_21_3.NamespacedKey.nullable(from))
}
