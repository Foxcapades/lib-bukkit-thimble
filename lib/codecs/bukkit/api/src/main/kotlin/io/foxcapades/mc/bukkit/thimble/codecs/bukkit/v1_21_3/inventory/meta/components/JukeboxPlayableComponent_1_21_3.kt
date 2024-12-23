package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.meta.components

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.*
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitCodecRegistry.Codec_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.fields.BodyEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldDecoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.BooleanCodec

import io.foxcapades.mc.bukkit.thimble.unsafe.JukeboxPlayableComponent

import org.bukkit.inventory.meta.components.JukeboxPlayableComponent

import java.io.InputStream


@Suppress("UnstableApiUsage")
internal class JukeboxPlayableComponent_1_21_3: BukkitTypeCodec<JukeboxPlayableComponent> {
  override val version      get() = v1_21_3
  override val javaType     get() = JukeboxPlayableComponent::class.java
  override val bukkitTypeId get() = BukkitTypeId.JukeboxPlayableComponent

  override val fieldEncoders: Iterator<FieldEncoder<JukeboxPlayableComponent>>
    get() = iteratorOf(
      BodyEncoder(JukeboxPlayableComponent::getSongKey, Codec_1_21_3.NamespacedKey),
      BodyEncoder(JukeboxPlayableComponent::isShowInTooltip, BooleanCodec),
    )

  override val fieldDecoders: Iterator<FieldDecoder<JukeboxPlayableComponent>>
    get() = emptyIterator()

  override fun create(from: InputStream): JukeboxPlayableComponent =
    JukeboxPlayableComponent(Codec_1_21_3.NamespacedKey.create(from), BooleanCodec.create(from))
}
