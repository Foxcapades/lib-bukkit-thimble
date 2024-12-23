package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.*
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitCodecRegistry.Codec_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.fields.BodyEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldDecoder
import org.bukkit.MusicInstrument
import org.bukkit.Registry
import java.io.InputStream


internal class MusicInstrument_1_21_3 : BukkitTypeCodec<MusicInstrument> {
  override val version       get() = v1_21_3
  override val javaType      get() = MusicInstrument::class.java
  override val bukkitTypeId  get() = BukkitTypeId.MusicInstrument
  override val fieldEncoders get() = iteratorOf(BodyEncoder(MusicInstrument::getKey, Codec_1_21_3.NamespacedKey))
  override val fieldDecoders get() = emptyIterator<FieldDecoder<MusicInstrument>>()

  override fun create(from: InputStream): MusicInstrument =
    Registry.INSTRUMENT.require(Codec_1_21_3.NamespacedKey.create(from))
}
