package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.potion

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.*
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldDecoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.MappingBodyEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.ByteCodec
import org.bukkit.potion.PotionType
import java.nio.ByteBuffer


internal class PotionType_1_21_3 : BukkitTypeCodec<PotionType> {
  override val version: BukkitVersion
    get() = v1_21_3

  override val javaType: Class<out PotionType>
    get() = PotionType::class.java

  override val bukkitTypeId: BukkitTypeId
    get() = BukkitTypeId.PotionType

  override val fieldEncoders: Iterator<FieldEncoder<PotionType>>
    get() = iteratorOf(MappingBodyEncoder(PotionType::ordinal::get, Int::toByte, ByteCodec))

  override val fieldDecoders: Iterator<FieldDecoder<PotionType>>
    get() = emptyIterator()

  override fun create(from: ByteBuffer) =
    PotionType.entries[ByteCodec.create(from).toInt()]
}
