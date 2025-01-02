package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitCodecRegistry.Codec_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitTypeCodec
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitTypeId
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.iteratorOf
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.meta.ItemMetaBase_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.fields.*
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.IntCodec
import io.foxcapades.mc.bukkit.thimble.DataType
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import java.io.InputStream
import java.io.OutputStream
import java.nio.ByteBuffer

internal class ItemStack_1_21_3 : BukkitTypeCodec<ItemStack> {
  override val version      get() = v1_21_3
  override val javaType     get() = ItemStack::class.java
  override val bukkitTypeId get() = BukkitTypeId.ItemStack

  override val fieldEncoders: Iterator<FieldEncoder<ItemStack>>
    get() = iteratorOf(
      BodyEncoder(ItemStack::getType, Codec_1_21_3.Material),
      BodyEncoder(ItemStack::getAmount, IntCodec),
      ConditionalEncoder(ItemStack::hasItemMeta, ItemStack::getItemMeta, ::writeItemMeta),
    )

  override val fieldDecoders: Iterator<FieldDecoder<ItemStack>>
    get() = iteratorOf(NullableDecoder1(FullDecoder(ItemStack::setItemMeta, DataType.Bukkit, ::readItemMeta)))

  override fun create(from: ByteBuffer): ItemStack =
    ItemStack(Codec_1_21_3.Material.create(from), IntCodec.create(from))

  // Polymorphic deserialization.
  private fun readItemMeta(stream: InputStream): ItemMeta {
    return with(ItemMetaBase_1_21_3.getCodec<ItemMeta>(stream, 1u)) {
      create(stream).also { decodeBody(stream, it) }
    }
  }

  private fun writeItemMeta(stream: OutputStream, value: ItemMeta?) =
    ItemMetaBase_1_21_3.getCodec(value!!).encode(stream, value)
}
