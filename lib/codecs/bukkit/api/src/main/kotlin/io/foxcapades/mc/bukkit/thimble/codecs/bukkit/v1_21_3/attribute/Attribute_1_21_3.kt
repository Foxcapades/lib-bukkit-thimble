package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.attribute

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.*
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitCodecRegistry.Codec_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.fields.BodyEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldDecoder
import org.bukkit.Registry
import org.bukkit.attribute.Attribute
import java.io.InputStream

internal class Attribute_1_21_3: BukkitTypeCodec<Attribute> {
  override val version
    get() = v1_21_3

  override val javaType
    get() = Attribute::class.java

  override val bukkitTypeId get() = BukkitTypeId.Attribute

  override val fieldEncoders
    get() = iteratorOf(BodyEncoder(Attribute::getKey, Codec_1_21_3.NamespacedKey))

  override val fieldDecoders
    get() = emptyIterator<FieldDecoder<Attribute>>()

  override fun create(from: InputStream): Attribute =
    Registry.ATTRIBUTE.require(Codec_1_21_3.NamespacedKey.create(from))
}
