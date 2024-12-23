package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.*
import io.foxcapades.mc.bukkit.thimble.codecs.fields.BodyEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldDecoder
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.IntCodec
import org.bukkit.Color
import java.io.InputStream

internal class Color_1_21_3: BukkitTypeCodec<Color> {
  override val version
    get() = v1_21_3

  override val javaType
    get() = Color::class.java

  override val bukkitTypeId
    get() = BukkitTypeId.Color

  override val fieldEncoders
    get() = iteratorOf(BodyEncoder(Color::asARGB, IntCodec))

  override val fieldDecoders
    get() = emptyIterator<FieldDecoder<Color>>()

  override fun create(from: InputStream) =
    Color.fromARGB(IntCodec.create(from))
}
