package io.foxcapades.mc.bukkit.thimble.types.bukkit

import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.util.B1

import org.bukkit.inventory.meta.ItemMeta

data object ItemMetaTypeHandler : ItemMetaTypeHandlerBase<ItemMeta>() {
  override val actualType    get() = ItemMeta::class.java
  override val typeIndicator get() = "b:m:IM"

  override fun deserializerFor(version: Byte): ComplexDeserializer<out ItemMeta>? =
    when (version) {
      B1   -> ItemMetaDeserializerBaseV1()
      else -> null
    }
}
