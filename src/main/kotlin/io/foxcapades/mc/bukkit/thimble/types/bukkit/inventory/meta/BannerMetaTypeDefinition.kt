package io.foxcapades.mc.bukkit.thimble.types.bukkit.inventory.meta

import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.util.B1
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.meta.BannerMeta

open class BannerMetaTypeDefinition : BannerMetaTypeDefinitionBase<BannerMeta>() {
  override val actualType     get() = BannerMeta::class.java
  override val typeIdentifier get() = "b:m:BAN"

  override fun serialize(value: BannerMeta, writer: ValueWriter) {
    writer.writeComplex(value.patterns)
    super.serialize(value, writer)
  }

  override fun deserializerFor(version: Byte): ComplexDeserializer<out BannerMeta>? =
    when (version) {
      B1   -> BannerMetaDeserializerV1()
      else -> null
    }
}

private class BannerMetaDeserializerV1 : BannerMetaDeserializerBaseV1<BannerMeta>() {
  override fun newItemMetaInstance(): BannerMeta =
    Bukkit.getItemFactory().getItemMeta(Material.WHITE_BANNER) as BannerMeta
}
