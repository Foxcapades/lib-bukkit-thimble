package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.meta.trim

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.*
import io.foxcapades.mc.bukkit.thimble.codecs.fields.BodyEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldDecoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldEncoder
import org.bukkit.Registry
import org.bukkit.inventory.meta.trim.TrimPattern
import java.io.InputStream


internal class TrimPattern_1_21_3 : BukkitTypeCodec<TrimPattern> {
  override val version: BukkitVersion
    get() = v1_21_3

  override val javaType: Class<out TrimPattern>
    get() = TrimPattern::class.java

  override val bukkitTypeId get() = BukkitTypeId.TrimPattern

  override val fieldEncoders: Iterator<FieldEncoder<TrimPattern>>
    get() = iteratorOf(BodyEncoder(TrimPattern::getKey, BukkitCodecRegistry.Codec_1_21_3.NamespacedKey))

  override val fieldDecoders: Iterator<FieldDecoder<TrimPattern>>
    get() = emptyIterator()

  @Suppress("UnstableApiUsage")
  override fun create(from: InputStream): TrimPattern =
    Registry.TRIM_PATTERN.require(BukkitCodecRegistry.Codec_1_21_3.NamespacedKey.create(from))
}
