package io.foxcapades.mc.bukkit.thimble.types.bukkit.inventory.meta

import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.read.asType
import org.bukkit.block.banner.Pattern
import org.bukkit.inventory.meta.BannerMeta

abstract class BannerMetaDeserializerBaseV1<T : BannerMeta> : ItemMetaDeserializerBaseV1<T>() {
  private lateinit var patterns: List<Pattern>

  override val fieldCount: Int
    get() = super.fieldCount + 1

  override fun append(index: Int, value: ValueAccessor) {
    if (index == 0)
      patterns = value.asComplex().asType()
    else
      super.append(index - 1, value)
  }

  override fun populateItemMeta(itemMeta: T) {
    itemMeta.patterns = patterns
    super.populateItemMeta(itemMeta)
  }
}
