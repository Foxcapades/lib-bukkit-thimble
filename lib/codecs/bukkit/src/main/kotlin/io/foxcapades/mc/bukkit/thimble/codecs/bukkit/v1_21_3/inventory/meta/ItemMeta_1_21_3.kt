package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.meta

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitTypeId
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldDecoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldEncoder
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.meta.ItemMeta
import java.nio.ByteBuffer

internal class ItemMeta_1_21_3 : ItemMetaBase_1_21_3<ItemMeta>() {
  override val javaType: Class<out ItemMeta>
    get() = ItemMeta::class.java

  override val bukkitTypeId get() = BukkitTypeId.ItemMeta

  override val fieldEncoders: Iterator<FieldEncoder<ItemMeta>>
    get() = baseEncoders().iterator()

  override val fieldDecoders: Iterator<FieldDecoder<ItemMeta>>
    get() = baseDecoders().iterator()

  override fun create(from: ByteBuffer): ItemMeta = Bukkit.getItemFactory().getItemMeta(Material.STONE)!!
}
