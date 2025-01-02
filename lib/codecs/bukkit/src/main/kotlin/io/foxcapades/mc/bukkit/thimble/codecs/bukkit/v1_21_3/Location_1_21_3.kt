package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.*
import io.foxcapades.mc.bukkit.thimble.codecs.fields.BodyEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.CustomEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldDecoder
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.DoubleCodec
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.FloatCodec
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.NullCodec
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.UUIDCodec
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.World
import java.io.OutputStream
import java.nio.ByteBuffer


internal class Location_1_21_3: BukkitTypeCodec<Location> {
  override val version      get() = v1_21_3
  override val javaType     get() = Location::class.java
  override val bukkitTypeId get() = BukkitTypeId.Location

  override val fieldEncoders get() = iteratorOf(
    CustomEncoder(Location::getWorld, ::writeWorld),
    BodyEncoder(Location::getX, DoubleCodec),
    BodyEncoder(Location::getY, DoubleCodec),
    BodyEncoder(Location::getZ, DoubleCodec),
    BodyEncoder(Location::getYaw, FloatCodec),
    BodyEncoder(Location::getPitch, FloatCodec),
  )

  override val fieldDecoders get() = emptyIterator<FieldDecoder<Location>>()

  override fun create(from: ByteBuffer) =
    Location(
      UUIDCodec.nullable(from)?.let(Bukkit::getWorld),
      DoubleCodec.create(from),
      DoubleCodec.create(from),
      DoubleCodec.create(from),
      FloatCodec.create(from),
      FloatCodec.create(from),
    )

  private fun writeWorld(into: OutputStream, value: World?) =
    if (value == null) {
      NullCodec.encode(into)
    } else {
      UUIDCodec.encode(into, value.uid)
    }
}
