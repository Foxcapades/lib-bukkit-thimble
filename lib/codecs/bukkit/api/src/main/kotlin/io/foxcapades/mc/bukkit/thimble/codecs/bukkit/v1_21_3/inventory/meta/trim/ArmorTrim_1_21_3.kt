package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.meta.trim

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.*
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitCodecRegistry.Codec_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.fields.BodyEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldDecoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldEncoder
import org.bukkit.inventory.meta.trim.ArmorTrim
import java.io.InputStream

internal class ArmorTrim_1_21_3 : BukkitTypeCodec<ArmorTrim> {
  override val version: BukkitVersion
    get() = v1_21_3

  override val javaType: Class<out ArmorTrim>
    get() = ArmorTrim::class.java

  override val bukkitTypeId get() = BukkitTypeId.ArmorTrim

  override val fieldEncoders: Iterator<FieldEncoder<ArmorTrim>>
    get() = iteratorOf(
      BodyEncoder(ArmorTrim::getMaterial, Codec_1_21_3.TrimMaterial),
      BodyEncoder(ArmorTrim::getPattern, Codec_1_21_3.TrimPattern),
    )

  override val fieldDecoders: Iterator<FieldDecoder<ArmorTrim>>
    get() = emptyIterator()

  override fun create(from: InputStream): ArmorTrim =
    ArmorTrim(Codec_1_21_3.TrimMaterial.create(from), Codec_1_21_3.TrimPattern.create(from))
}

