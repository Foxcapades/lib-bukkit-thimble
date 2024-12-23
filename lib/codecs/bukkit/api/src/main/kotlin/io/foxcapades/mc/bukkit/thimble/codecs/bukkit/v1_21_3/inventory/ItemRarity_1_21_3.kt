package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.*
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldDecoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.MappingBodyEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.ByteCodec
import org.bukkit.inventory.ItemRarity
import java.io.InputStream

internal class ItemRarity_1_21_3 : BukkitTypeCodec<ItemRarity> {
  override val version      get() = v1_21_3
  override val javaType     get() = ItemRarity::class.java
  override val bukkitTypeId get() = BukkitTypeId.ItemRarity

  override val fieldEncoders: Iterator<FieldEncoder<ItemRarity>>
    get() = iteratorOf(MappingBodyEncoder(ItemRarity::ordinal::get, Int::toByte, ByteCodec))

  override val fieldDecoders: Iterator<FieldDecoder<ItemRarity>>
    get() = emptyIterator()

  override fun create(from: InputStream): ItemRarity =
    ItemRarity.entries[ByteCodec.create(from).toInt()]
}
