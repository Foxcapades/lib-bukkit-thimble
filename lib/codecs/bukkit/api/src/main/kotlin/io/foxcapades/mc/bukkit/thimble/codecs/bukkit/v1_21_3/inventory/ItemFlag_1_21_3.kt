package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.*
import io.foxcapades.mc.bukkit.thimble.codecs.fields.*
import org.bukkit.inventory.ItemFlag
import java.io.InputStream
import java.io.OutputStream

internal class ItemFlag_1_21_3 : BukkitTypeCodec<ItemFlag> {
  override val version get() = v1_21_3

  override val javaType get() = ItemFlag::class.java

  override val bukkitTypeId get() = BukkitTypeId.ItemFlag

  override val fieldEncoders: Iterator<FieldEncoder<ItemFlag>>
    get() = iteratorOf(CustomEncoder("id", { it }, Companion::write))

  override val fieldDecoders: Iterator<FieldDecoder<ItemFlag>>
    get() = emptyIterator()

  override fun create(from: InputStream) =
    EnumByteBodyDecoder.decodeBody(from, enumIndex)

  companion object {
    private val enumIndex = arrayOf(
      ItemFlag.HIDE_ENCHANTS,
      ItemFlag.HIDE_ATTRIBUTES,
      ItemFlag.HIDE_UNBREAKABLE,
      ItemFlag.HIDE_DESTROYS,
      ItemFlag.HIDE_PLACED_ON,
      ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
      ItemFlag.HIDE_DYE,
      ItemFlag.HIDE_ARMOR_TRIM,
    )

    private fun write(into: OutputStream, value: ItemFlag) =
      EnumByteBodyEncoder.encodeBody(into, enumIndex, value)
  }
}
