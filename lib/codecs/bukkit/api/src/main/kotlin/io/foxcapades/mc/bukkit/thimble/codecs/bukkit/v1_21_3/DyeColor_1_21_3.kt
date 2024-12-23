package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3

import io.foxcapades.mc.bukkit.thimble.ThimbleDeserializationException
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.*
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitCodecRegistry.Codec_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.fields.BodyEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldDecoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldEncoder
import org.bukkit.DyeColor
import java.io.InputStream

internal class DyeColor_1_21_3: BukkitTypeCodec<DyeColor> {
  override val version: BukkitVersion
    get() = v1_21_3

  override val javaType: Class<out DyeColor>
    get() = DyeColor::class.java

  override val bukkitTypeId get() = BukkitTypeId.DyeColor

  override val fieldEncoders: Iterator<FieldEncoder<DyeColor>>
    get() = iteratorOf(BodyEncoder(DyeColor::getColor, Codec_1_21_3.Color))

  override val fieldDecoders: Iterator<FieldDecoder<DyeColor>>
    get() = emptyIterator()

  override fun create(from: InputStream): DyeColor =
    Codec_1_21_3.Color.create(from).let {
      DyeColor.getByColor(it) ?: throw ThimbleDeserializationException("invalid color for DyeColor: $it")
    }
}
