package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.meta

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitCodecRegistry.Codec_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitTypeId
import io.foxcapades.mc.bukkit.thimble.codecs.fields.NullableDecoder1
import io.foxcapades.mc.bukkit.thimble.codecs.fields.NullableEncoder
import io.foxcapades.mc.bukkit.thimble.unsafe.MusicInstrumentMeta
import org.bukkit.inventory.meta.MusicInstrumentMeta
import java.io.InputStream

internal class MusicInstrumentMeta_1_21_3 : ItemMetaBase_1_21_3<MusicInstrumentMeta>() {
  override val javaType     get() = MusicInstrumentMeta::class.java
  override val bukkitTypeId get() = BukkitTypeId.MusicInstrumentMeta

  override val fieldEncoders get() = (baseEncoders() + arrayOf(
    NullableEncoder(MusicInstrumentMeta::getInstrument, Codec_1_21_3.MusicInstrument)
  )).iterator()

  override val fieldDecoders get() = (baseDecoders() + arrayOf(
    NullableDecoder1(MusicInstrumentMeta::setInstrument, Codec_1_21_3.MusicInstrument)
  )).iterator()

  override fun create(from: InputStream) =
    MusicInstrumentMeta()
}
