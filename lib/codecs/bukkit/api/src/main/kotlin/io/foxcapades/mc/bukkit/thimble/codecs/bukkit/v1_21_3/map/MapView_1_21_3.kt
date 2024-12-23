package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.map

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitCodecRegistry.Codec_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitTypeCodec
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitTypeId
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.nullable
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.fields.*
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.BooleanCodec
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.IntCodec
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.UUIDCodec
import io.foxcapades.mc.bukkit.thimble.unsafe.MapView
import io.foxcapades.mc.bukkit.thimble.unsafe.getWorldKey
import org.bukkit.map.MapView
import java.io.InputStream
import java.io.OutputStream


internal class MapView_1_21_3 : BukkitTypeCodec<MapView> {
  override val version       get() = v1_21_3
  override val javaType      get() = MapView::class.java
  override val bukkitTypeId  get() = BukkitTypeId.MapView
  override val fieldEncoders get() = encoders.iterator()
  override val fieldDecoders get() = decoders.iterator()


  override fun create(from: InputStream) =
    MapView(Codec_1_21_3.NamespacedKey.create(from), UUIDCodec.nullable(from))

  private companion object {
    private val enumIndex = arrayOf(
      MapView.Scale.CLOSEST,
      MapView.Scale.CLOSE,
      MapView.Scale.NORMAL,
      MapView.Scale.FAR,
      MapView.Scale.FARTHEST,
    )

    val encoders = arrayOf(
      WorldEncoder(),
      EnumByteBodyEncoder(MapView::getScale, enumIndex),
      BodyEncoder(MapView::getCenterX, IntCodec),
      BodyEncoder(MapView::getCenterZ, IntCodec),
      BodyEncoder(MapView::isTrackingPosition, BooleanCodec),
      BodyEncoder(MapView::isUnlimitedTracking, BooleanCodec),
      BodyEncoder(MapView::isLocked, BooleanCodec)
    )

    val decoders = arrayOf(
      EnumByteBodyDecoder(MapView::setScale, enumIndex),
      BodyDecoder(MapView::setCenterX, IntCodec),
      BodyDecoder(MapView::setCenterZ, IntCodec),
      BodyDecoder(MapView::setTrackingPosition, BooleanCodec),
      BodyDecoder(MapView::setUnlimitedTracking, BooleanCodec),
      BodyDecoder(MapView::setLocked, BooleanCodec),
    )
  }

  internal class WorldEncoder : FieldEncoder<MapView> {
    override val name: String
      get() = "getWorld"

    override fun encode(stream: OutputStream, instance: MapView) {
      Codec_1_21_3.NamespacedKey.encodeBody(stream, instance.getWorldKey())
      UUIDCodec.nullable(stream, instance.world?.uid)
    }
  }
}
