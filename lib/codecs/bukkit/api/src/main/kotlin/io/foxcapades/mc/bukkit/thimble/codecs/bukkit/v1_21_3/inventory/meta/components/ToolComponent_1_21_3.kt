package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.meta.components

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.*
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitCodecRegistry.Codec_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.fields.BodyEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldDecoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.FloatCodec
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.IntCodec
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.ListCodecV1
import io.foxcapades.mc.bukkit.thimble.unsafe.ToolComponent
import org.bukkit.inventory.meta.components.ToolComponent
import java.io.InputStream

@Suppress("UnstableApiUsage")
internal class ToolComponent_1_21_3 : BukkitTypeCodec<ToolComponent> {
  override val version: BukkitVersion
    get() = v1_21_3

  override val javaType: Class<out ToolComponent>
    get() = ToolComponent::class.java

  override val bukkitTypeId get() = BukkitTypeId.ToolComponent

  override val fieldEncoders: Iterator<FieldEncoder<ToolComponent>>
    get() = iteratorOf(
      BodyEncoder(ToolComponent::getRules, ListCodecV1(Codec_1_21_3.ToolRule)),
      BodyEncoder(ToolComponent::getDefaultMiningSpeed, FloatCodec),
      BodyEncoder(ToolComponent::getDamagePerBlock, IntCodec)
    )

  override val fieldDecoders: Iterator<FieldDecoder<ToolComponent>>
    get() = emptyIterator()

  override fun create(from: InputStream): ToolComponent {
    return ToolComponent(
      ListCodecV1(Codec_1_21_3.ToolRule).create(from),
      FloatCodec.create(from),
      IntCodec.create(from),
    )
  }
}
