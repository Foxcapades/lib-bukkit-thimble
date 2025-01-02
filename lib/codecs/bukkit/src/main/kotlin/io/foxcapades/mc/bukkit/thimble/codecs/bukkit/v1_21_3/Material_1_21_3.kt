package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.*
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitCodecRegistry.Codec_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.fields.BodyEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldDecoder
import org.bukkit.Material
import org.bukkit.Registry
import java.nio.ByteBuffer

internal class Material_1_21_3 : BukkitTypeCodec<Material> {
  override val version
    get() = v1_21_3

  override val javaType
    get() = Material::class.java

  override val bukkitTypeId
    get() = BukkitTypeId.Material

  override val fieldEncoders
    get() = iteratorOf(BodyEncoder(Material::getKey, Codec_1_21_3.NamespacedKey))

  override val fieldDecoders
    get() = emptyIterator<FieldDecoder<Material>>()

  override fun create(from: ByteBuffer): Material =
    Registry.MATERIAL.require(Codec_1_21_3.NamespacedKey.create(from))
}
