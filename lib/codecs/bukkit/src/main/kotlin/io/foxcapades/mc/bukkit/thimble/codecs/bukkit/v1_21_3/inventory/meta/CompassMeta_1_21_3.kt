package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.meta

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitCodecRegistry.Codec_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitTypeId
import io.foxcapades.mc.bukkit.thimble.codecs.fields.*
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.BooleanCodec
import org.bukkit.Material
import org.bukkit.inventory.meta.CompassMeta
import java.nio.ByteBuffer

internal class CompassMeta_1_21_3 : ItemMetaBase_1_21_3<CompassMeta>() {
  override val javaType: Class<out CompassMeta>
    get() = CompassMeta::class.java

  override val bukkitTypeId get() = BukkitTypeId.CompassMeta

  override val fieldEncoders: Iterator<FieldEncoder<CompassMeta>>
    get() = (baseEncoders() + arrayOf(
      ConditionalEncoder(CompassMeta::getLodestone, Codec_1_21_3.Location::enforceNN, CompassMeta::hasLodestone),
      BodyEncoder(CompassMeta::isLodestoneTracked, BooleanCodec),
    )).iterator()

  override val fieldDecoders: Iterator<FieldDecoder<CompassMeta>>
    get() = (baseDecoders() + arrayOf(
      NullableDecoder1(CompassMeta::setLodestone, Codec_1_21_3.Location),
      BodyDecoder(CompassMeta::setLodestoneTracked, BooleanCodec),
    )).iterator()

  override fun create(from: ByteBuffer): CompassMeta =
    Material.COMPASS.createMeta()
}
