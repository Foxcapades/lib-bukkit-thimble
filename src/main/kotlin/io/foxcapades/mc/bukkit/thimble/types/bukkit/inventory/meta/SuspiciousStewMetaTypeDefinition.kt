package io.foxcapades.mc.bukkit.thimble.types.bukkit.inventory.meta

import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.read.asType
import io.foxcapades.mc.bukkit.thimble.util.B1
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.meta.SuspiciousStewMeta
import org.bukkit.potion.PotionEffect

open class SuspiciousStewMetaTypeDefinition : ItemMetaTypeDefinitionBase<SuspiciousStewMeta>() {
  override val actualType     get() = SuspiciousStewMeta::class.java
  override val typeIdentifier get() = "b:m:SSM"

  override fun serialize(value: SuspiciousStewMeta, writer: ValueWriter) {
    if (value.hasCustomEffects())
      writer.writeComplex(value.customEffects)
    else
      writer.writeInt(0)

    super.serialize(value, writer)
  }

  override fun deserializerFor(version: Byte): ComplexDeserializer<out SuspiciousStewMeta>? =
    when (version) {
      B1 -> object : ItemMetaDeserializerBaseV1<SuspiciousStewMeta>() {
        private var customEffects: List<PotionEffect>? = null

        override fun append(index: Int, value: ValueAccessor) {
          when (index) {
            0    -> if (value.isComplex) customEffects = value.asComplex().asType()
            else -> super.append(index-1, value)
          }
        }

        override fun newItemMetaInstance(): SuspiciousStewMeta =
          Bukkit.getItemFactory().getItemMeta(Material.SUSPICIOUS_STEW) as SuspiciousStewMeta

        override fun populateItemMeta(itemMeta: SuspiciousStewMeta) {
          customEffects?.forEach { itemMeta.addCustomEffect(it, true) }
          super.populateItemMeta(itemMeta)
        }
      }

      else -> null
    }
}
