package io.foxcapades.mc.bukkit.thimble.types.bukkit

import io.foxcapades.mc.bukkit.thimble.types.ComplexTypeDefinition
import io.foxcapades.mc.bukkit.thimble.util.B1
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

import org.bukkit.inventory.meta.ItemMeta


abstract class ItemMetaTypeDefinitionBase<T : ItemMeta> : ComplexTypeDefinition<T> {
  override val currentVersion get() = B1

  override fun serialize(value: T, writer: ValueWriter) {
    if (value.hasDisplayName()) // 0
      writer.writeString(value.displayName)
    else
      writer.writeInt(0)

    if (value.hasItemName()) // 1
      writer.writeString(value.itemName)
    else
      writer.writeInt(0)

    if (value.hasLore() && value.lore!!.isNotEmpty()) // 2
      writer.writeStringList(value.lore!!)
    else
      writer.writeInt(0)

    if (value.hasCustomModelData()) // 3
      writer.writeInt(value.customModelData)
    else
      writer.writeNull()

    if (value.hasEnchants()) { // 4
      val sb = StringBuilder(256)
      value.enchants.forEach { (en, lvl) -> sb.append(en.key.toString()).append('|').append(lvl).append(';') }
      writer.writeString(sb.substring(0, sb.length-1))
    } else {
      writer.writeInt(0)
    }

    // let-wrap because this is a computed field
    writer.writeInt(value.itemFlags.let { flags -> // 5
      var oct = 0

      if (flags.isNotEmpty()) {
        val it = flags.iterator()
        oct = it.next().ordinal

        while (it.hasNext()) {
          oct *= 8
          oct += it.next().ordinal
        }
      }

      oct
    })

    if (value.isHideTooltip) // 6
      writer.writeInt(1)
    else
      writer.writeInt(0)

    if (value.isUnbreakable) // 7
      writer.writeInt(1)
    else
      writer.writeInt(0)

    if (value.hasEnchantmentGlintOverride()) // 8
      writer.writeInt(if (value.enchantmentGlintOverride) 1 else 0)
    else
      writer.writeInt(2)

    if (value.isFireResistant) // 9
      writer.writeInt(1)
    else
      writer.writeInt(0)

    if (value.hasMaxStackSize()) // 10
      writer.writeInt(value.maxStackSize)
    else
      writer.writeInt(0)

    if (value.hasRarity()) // 11
      writer.writeInt(value.rarity.ordinal)
    else
      writer.writeInt(-1)

    if (value.hasFood()) // 12
      writer.writeComplex(value.food)
    else
      writer.writeInt(0)

    if (value.hasTool()) // 13
      writer.writeComplex(value.tool)
    else
      writer.writeInt(0)

    if (value.hasJukeboxPlayable()) // 14
      writer.writeComplex(value.jukeboxPlayable!!)
    else
      writer.writeInt(0)

    if (value.hasAttributeModifiers() && !value.attributeModifiers!!.isEmpty) // 15
      writer.writeComplex(value.attributeModifiers!!)
    else
      writer.writeInt(0)
  }
}

