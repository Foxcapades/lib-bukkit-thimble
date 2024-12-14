package io.foxcapades.mc.bukkit.thimble.types.bukkit

import io.foxcapades.mc.bukkit.thimble.hax.meta.WritableBookMeta
import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.read.asType
import org.bukkit.inventory.meta.WritableBookMeta

open class WriteableBookMetaDeserializerV1 @JvmOverloads constructor(private val indexOffset: Int = 0)
  : ItemMetaDeserializerBaseV1<WritableBookMeta>(indexOffset + 1)
{
  protected var pages: List<String>? = null

  override fun append(index: Int, value: ValueAccessor) {
    when (index - indexOffset) {
      0    -> pages = value.asComplex().asType()
      else -> super.append(index, value)
    }
  }

  override fun newItemMetaInstance(): WritableBookMeta = WritableBookMeta()

  override fun populateItemMeta(itemMeta: WritableBookMeta) {
    super.populateItemMeta(itemMeta)
    pages?.also(itemMeta::setPages)
  }
}
