package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.types.ComplexTypeHandler
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter
import org.bukkit.inventory.meta.ItemMeta

open class ItemMetaTypeHandlerBase<T : ItemMeta> : ComplexTypeHandler<T> {

  override fun serialize(value: T, writer: ValueWriter) {
    if (value.hasDisplayName())
      writer.writeString(value.displayName)
    else
      writer.writeInt(0)

    if (value.hasItemName())
      writer.writeString(value.itemName)
    else
      writer.writeInt(0)

    if (value.hasLore() && value.lore!!.isNotEmpty())
      writer.writeStringList(value.lore!!)
    else
      writer.writeInt(0)

    if (value.hasCustomModelData())
      writer.writeInt(value.customModelData)
    else
      writer.writeNull()

    if (value.hasEnchants()) {
      val sb = StringBuilder(256)
      value.enchants.forEach { (en, lvl) -> sb.append(en.key.toString()).append('|').append(lvl).append(';') }
      writer.writeString(sb.substring(0, sb.length-1))
    } else {
      writer.writeInt(0)
    }

    if (value.itemFlags.isNotEmpty())
      writer.writeString(value.itemFlags.joinToString(",") { it.name })
    else
      writer.writeInt(0)

    if (value.isHideTooltip)
      writer.writeInt(1)
    else
      writer.writeInt(0)

    if (value.isUnbreakable)
      writer.writeInt(1)
    else
      writer.writeInt(0)

    if (value.hasEnchantmentGlintOverride())
      writer.writeInt(if (value.enchantmentGlintOverride) 1 else 0)
    else
      writer.writeInt(2)

    if (value.isFireResistant)
      writer.writeInt(1)
    else
      writer.writeInt(0)

    if (value.hasMaxStackSize())
      writer.writeInt(value.maxStackSize)
    else
      writer.writeInt(0)

    if (value.hasRarity())
      writer.writeString(value.rarity.name)
    else
      writer.writeInt(0)

    if (value.hasFood())

  }

}
