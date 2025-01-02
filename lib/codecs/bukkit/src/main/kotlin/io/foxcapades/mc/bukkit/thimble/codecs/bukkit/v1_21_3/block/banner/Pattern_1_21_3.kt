package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.block.banner

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.*
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitCodecRegistry.Codec_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.fields.BodyEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldDecoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.MappingBodyEncoder
import org.bukkit.Registry
import org.bukkit.block.banner.Pattern
import org.bukkit.block.banner.PatternType
import java.nio.ByteBuffer

internal class Pattern_1_21_3 : BukkitTypeCodec<Pattern> {
  override val version      get() = v1_21_3
  override val javaType     get() = Pattern::class.java
  override val bukkitTypeId get() = BukkitTypeId.Pattern

  override val fieldEncoders get() = iteratorOf(
    BodyEncoder(Pattern::getColor, Codec_1_21_3.DyeColor),
    MappingBodyEncoder(Pattern::getPattern, PatternType::getKey, Codec_1_21_3.NamespacedKey),
  )

  override val fieldDecoders get() = emptyIterator<FieldDecoder<Pattern>>()

  override fun create(from: ByteBuffer) =
    Pattern(
      Codec_1_21_3.DyeColor.create(from),
      Registry.BANNER_PATTERN.require(Codec_1_21_3.NamespacedKey.create(from)),
    )
}
