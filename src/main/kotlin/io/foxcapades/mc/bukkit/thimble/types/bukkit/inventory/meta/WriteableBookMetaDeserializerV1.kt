package io.foxcapades.mc.bukkit.thimble.types.bukkit.inventory.meta

import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.read.asType
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.meta.WritableBookMeta

open class WriteableBookMetaDeserializerV1 : ItemMetaDeserializerBaseV1<WritableBookMeta>() {
  protected var pages: List<String>? = null

  override fun append(index: Int, value: ValueAccessor) {
    when (index) {
      0    -> pages = value.asComplex().asType()
      else -> super.append(index-1, value)
    }
  }

  override fun newItemMetaInstance(): WritableBookMeta =
    Bukkit.getItemFactory().getItemMeta(Material.WRITABLE_BOOK) as WritableBookMeta

  override fun populateItemMeta(itemMeta: WritableBookMeta) {
    super.populateItemMeta(itemMeta)
    pages?.also(itemMeta::setPages)
  }
}
