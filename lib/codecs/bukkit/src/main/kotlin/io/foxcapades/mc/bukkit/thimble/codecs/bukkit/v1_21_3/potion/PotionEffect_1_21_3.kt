package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.potion

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.*
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitCodecRegistry.Codec_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.fields.BodyEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldDecoder
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.BooleanCodec
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.IntCodec
import org.bukkit.potion.PotionEffect
import java.nio.ByteBuffer


internal class PotionEffect_1_21_3 : BukkitTypeCodec<PotionEffect> {
  override val version       get() = v1_21_3
  override val javaType      get() = PotionEffect::class.java
  override val bukkitTypeId  get() = BukkitTypeId.PotionEffect

  override val fieldEncoders get() = iteratorOf(
    BodyEncoder(PotionEffect::getType, Codec_1_21_3.PotionEffectType),
    BodyEncoder(PotionEffect::getDuration, IntCodec),
    BodyEncoder(PotionEffect::getAmplifier, IntCodec),
    BodyEncoder(PotionEffect::isAmbient, BooleanCodec),
    BodyEncoder(PotionEffect::hasParticles, BooleanCodec),
    BodyEncoder(PotionEffect::hasIcon, BooleanCodec),
  )

  override val fieldDecoders get() = emptyIterator<FieldDecoder<PotionEffect>>()

  override fun create(from: ByteBuffer) =
    PotionEffect(
      /*      type = */ Codec_1_21_3.PotionEffectType.create(from),
      /*  duration = */ IntCodec.create(from),
      /* amplifier = */ IntCodec.create(from),
      /*   ambient = */ BooleanCodec.create(from),
      /* particles = */ BooleanCodec.create(from),
      /*      icon = */ BooleanCodec.create(from),
    )
}
