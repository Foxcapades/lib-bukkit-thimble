package io.foxcapades.mc.bukkit.thimble.types.bukkit.inventory.meta

import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.util.B1
import org.bukkit.Bukkit
import org.bukkit.Material

import org.bukkit.inventory.meta.ItemMeta

open class ItemMetaTypeDefinition : ItemMetaTypeDefinitionBase<ItemMeta>() {
  override val actualType     get() = ItemMeta::class.java
  override val typeIdentifier get() = "b:m:IM"

  override fun deserializerFor(version: Byte): ComplexDeserializer<out ItemMeta>? =
    when (version) {
      B1 -> object : ItemMetaDeserializerBaseV1<ItemMeta>() {
        override fun newItemMetaInstance(): ItemMeta =
          Bukkit.getItemFactory().getItemMeta(Material.STONE)!! // Just need some material with no meta of its own
      }

      else -> null
    }
}
