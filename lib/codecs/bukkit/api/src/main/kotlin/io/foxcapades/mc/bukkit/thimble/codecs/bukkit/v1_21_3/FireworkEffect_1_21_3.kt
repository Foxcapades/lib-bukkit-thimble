package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.*
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitCodecRegistry.Codec_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.fields.*
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.BooleanCodec
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.ListCodecV1
import org.bukkit.FireworkEffect
import java.io.InputStream


internal class FireworkEffect_1_21_3 : BukkitTypeCodec<FireworkEffect> {
  override val version: BukkitVersion
    get() = v1_21_3

  override val javaType: Class<out FireworkEffect>
    get() = FireworkEffect::class.java

  override val bukkitTypeId get() = BukkitTypeId.FireworkEffect

  override val fieldEncoders: Iterator<FieldEncoder<FireworkEffect>>
    get() = iteratorOf(
      BodyEncoder(FireworkEffect::hasFlicker, BooleanCodec),
      BodyEncoder(FireworkEffect::hasTrail, BooleanCodec),
      BodyEncoder(FireworkEffect::getColors, colorListCodec),
      BodyEncoder(FireworkEffect::getFadeColors, colorListCodec),
      EnumByteBodyEncoder(FireworkEffect::getType, enumIndex),
    )

  override val fieldDecoders: Iterator<FieldDecoder<FireworkEffect>>
    get() = emptyIterator()

  override fun create(from: InputStream): FireworkEffect =
    FireworkEffect.builder().apply {
      if (BooleanCodec.create(from))
        withFlicker()
      if (BooleanCodec.create(from))
        withTrail()
      withColor(colorListCodec.create(from).also { colorListCodec.decodeBody(from, it) })
      withFade(colorListCodec.create(from).also { colorListCodec.decodeBody(from, it) })
      with(EnumByteBodyDecoder.decodeBody(from, enumIndex))
    }.build()


  companion object {
    private val colorListCodec = ListCodecV1(Codec_1_21_3.Color)

    private val enumIndex = arrayOf(
      FireworkEffect.Type.BALL,
      FireworkEffect.Type.BALL_LARGE,
      FireworkEffect.Type.STAR,
      FireworkEffect.Type.BURST,
      FireworkEffect.Type.CREEPER,
    )
  }
}
