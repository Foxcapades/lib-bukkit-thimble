package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.meta

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitCodecRegistry.Codec_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitTypeId
import io.foxcapades.mc.bukkit.thimble.codecs.fields.ConditionalEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldDecoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.NullableDecoder1
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.ListCodecV1
import org.bukkit.Material
import org.bukkit.inventory.meta.SuspiciousStewMeta
import org.bukkit.potion.PotionEffect
import java.nio.ByteBuffer

internal class SuspiciousStewMeta_1_21_3 : ItemMetaBase_1_21_3<SuspiciousStewMeta>() {
  override val javaType: Class<out SuspiciousStewMeta>
    get() = SuspiciousStewMeta::class.java

  override val bukkitTypeId: BukkitTypeId
    get() = BukkitTypeId.SuspiciousStewMeta

  override val fieldEncoders: Iterator<FieldEncoder<SuspiciousStewMeta>>
    get() = (baseEncoders() + arrayOf(
      ConditionalEncoder(
        SuspiciousStewMeta::hasCustomEffects,
        SuspiciousStewMeta::getCustomEffects,
        ListCodecV1(Codec_1_21_3.PotionEffect)
      )
    )).iterator()

  override val fieldDecoders: Iterator<FieldDecoder<SuspiciousStewMeta>>
    get() = (baseDecoders() + arrayOf(
      NullableDecoder1(::addEffects, ListCodecV1(Codec_1_21_3.PotionEffect))
    )).iterator()

  override fun create(from: ByteBuffer): SuspiciousStewMeta =
    Material.SUSPICIOUS_STEW.createMeta()

  private fun addEffects(into: SuspiciousStewMeta, from: List<PotionEffect>) {
    from.forEach { into.addCustomEffect(it, true) }
  }
}
