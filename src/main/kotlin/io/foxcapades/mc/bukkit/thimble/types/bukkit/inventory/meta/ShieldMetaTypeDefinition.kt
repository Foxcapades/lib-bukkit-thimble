package io.foxcapades.mc.bukkit.thimble.types.bukkit.inventory.meta

import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.util.B1
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.meta.ShieldMeta

open class ShieldMetaTypeDefinition : BannerMetaTypeDefinitionBase<ShieldMeta>() {
  override val actualType     get() = ShieldMeta::class.java
  override val typeIdentifier get() = "b:m:SM1"

  override fun serialize(value: ShieldMeta, writer: ValueWriter) {
    writer.writeIntOrNull(value.baseColor?.color?.asARGB())
    super.serialize(value, writer)
  }

  override fun deserializerFor(version: Byte): ComplexDeserializer<ShieldMeta>? =
    when (version) {
      B1   -> ShieldMetaDeserializer()
      else -> null
    }
}

private class ShieldMetaDeserializer : BannerMetaDeserializerBaseV1<ShieldMeta>() {
  override fun newItemMetaInstance(): ShieldMeta =
    Bukkit.getItemFactory().getItemMeta(Material.SHIELD) as ShieldMeta
}
