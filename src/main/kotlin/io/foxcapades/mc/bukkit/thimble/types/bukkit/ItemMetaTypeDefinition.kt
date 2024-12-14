package io.foxcapades.mc.bukkit.thimble.types.bukkit

import io.foxcapades.mc.bukkit.thimble.hax.meta.ItemMeta
import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.util.B1

import org.bukkit.inventory.meta.ItemMeta

data object ItemMetaTypeDefinition : ItemMetaTypeDefinitionBase<ItemMeta>() {
  override val actualType    get() = ItemMeta::class.java
  override val typeIdentifier get() = "b:m:IM"

  override fun deserializerFor(version: Byte): ComplexDeserializer<out ItemMeta>? =
    when (version) {
      B1 -> object : ItemMetaDeserializerBaseV1<ItemMeta>(0) {
        override fun newItemMetaInstance() = ItemMeta()
      }

      else -> null
    }
}
