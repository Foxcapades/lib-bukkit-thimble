package io.foxcapades.mc.bukkit.thimble.types.bukkit.inventory.meta

import io.foxcapades.mc.bukkit.thimble.write.ValueWriter
import org.bukkit.inventory.meta.BannerMeta

abstract class BannerMetaTypeDefinitionBase<T : BannerMeta> : ItemMetaTypeDefinitionBase<T>() {
  override fun serialize(value: T, writer: ValueWriter) {
    writer.writeComplex(value.patterns)
    super.serialize(value, writer)
  }
}
