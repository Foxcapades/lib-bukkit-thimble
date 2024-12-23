package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.meta


import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitCodecRegistry.Codec_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitTypeId
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.enforceNN
import io.foxcapades.mc.bukkit.thimble.codecs.fields.BodyDecoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.BodyEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.ConditionalEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.NullableDecoder1
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.BooleanCodec
import org.bukkit.Material
import org.bukkit.inventory.meta.MapMeta
import java.io.InputStream

internal class MapMeta_1_21_3 : ItemMetaBase_1_21_3<MapMeta>() {
  override val javaType     get() = MapMeta::class.java
  override val bukkitTypeId get() = BukkitTypeId.MapMeta

  override val fieldEncoders get() = (baseEncoders() + arrayOf(
    ConditionalEncoder(MapMeta::hasMapView, MapMeta::getMapView, Codec_1_21_3.MapView::enforceNN),
    BodyEncoder(MapMeta::isScaling, BooleanCodec),
    ConditionalEncoder(MapMeta::hasColor, MapMeta::getColor, Codec_1_21_3.Color::enforceNN)
  )).iterator()

  override val fieldDecoders get() = (baseDecoders() + arrayOf(
    NullableDecoder1(MapMeta::setMapView, Codec_1_21_3.MapView),
    BodyDecoder(MapMeta::setScaling, BooleanCodec),
    NullableDecoder1(MapMeta::setColor, Codec_1_21_3.Color),
  )).iterator()

  override fun create(from: InputStream): MapMeta =
    Material.MAP.createMeta()
}

