package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.meta.components

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.*
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitCodecRegistry.Codec_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.fields.BodyEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldDecoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.NullableEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.BooleanCodec
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.CollectionCodecV1
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.FloatCodec
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.ListCodecV1

import io.foxcapades.mc.bukkit.thimble.unsafe.ToolRule

import org.bukkit.inventory.meta.components.ToolComponent.ToolRule

import java.io.InputStream

@Suppress("UnstableApiUsage")
internal class ToolRule_1_21_3 : BukkitTypeCodec<ToolRule> {
  override val version: BukkitVersion
    get() = v1_21_3

  override val javaType: Class<out ToolRule>
    get() = ToolRule::class.java

  override val bukkitTypeId get() = BukkitTypeId.ToolRule

  override val fieldEncoders: Iterator<FieldEncoder<ToolRule>>
    get() = iteratorOf(
      BodyEncoder(ToolRule::getBlocks, CollectionCodecV1(Codec_1_21_3.Material)),
      NullableEncoder(ToolRule::getSpeed, FloatCodec),
      NullableEncoder(ToolRule::isCorrectForDrops, BooleanCodec)
    )

  override val fieldDecoders: Iterator<FieldDecoder<ToolRule>>
    get() = emptyIterator()

  override fun create(from: InputStream): ToolRule =
    ToolRule(
      ListCodecV1(Codec_1_21_3.Material).create(from),
      FloatCodec.nullable(from),
      BooleanCodec.nullable(from),
    )
}
