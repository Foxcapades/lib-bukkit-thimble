package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.*
import io.foxcapades.mc.bukkit.thimble.codecs.fields.BodyEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldDecoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.MappingBodyEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.StringCodecV1
import org.bukkit.NamespacedKey
import java.io.InputStream

internal class NamespacedKey_1_21_3 : BukkitTypeCodec<NamespacedKey> {
  override val version       get() = v1_21_3
  override val javaType      get() = NamespacedKey::class.java
  override val bukkitTypeId  get() = BukkitTypeId.NamespacedKey
  override val fieldEncoders get() = iteratorOf(
    MappingBodyEncoder(NamespacedKey::getNamespace, ::mapNS, StringCodecV1),
    BodyEncoder(NamespacedKey::getKey, StringCodecV1)
  )

  override val fieldDecoders get() = emptyIterator<FieldDecoder<NamespacedKey>>()


  @Suppress("UnstableApiUsage")
  override fun create(from: InputStream): NamespacedKey =
    when (val ns = StringCodecV1.create(from)) {
      "", NamespacedKey.MINECRAFT -> NamespacedKey.minecraft(StringCodecV1.create(from))
      else -> NamespacedKey(ns, StringCodecV1.create(from))
    }

  private fun mapNS(value: String) =
    if (value == NamespacedKey.MINECRAFT) "" else value
}
