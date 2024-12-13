package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.parse.ThimbleDeserializationException
import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.ComplexTypeHandler
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

data object ItemStackTypeHandler : ComplexTypeHandler<ItemStack> {
  override val typeIndicator: String
    get() = "b:i:IS"

  override val currentVersion: Int
    get() = 1

  override val actualType: Class<ItemStack>
    get() = ItemStack::class.java

  override fun serialize(value: ItemStack, writer: ValueWriter) {
    writer.writeString(value.type.key.toString())
    writer.writeInt(value.amount)
    // skip material data
    if (value.hasItemMeta())
      writer.writeComplex(value.itemMeta!!)
    else
      writer.writeNull()
  }

  override fun deserializerFor(version: Int): ComplexDeserializer<out ItemStack> =
    object : ComplexDeserializer<ItemStack> {
      var material: Material? = null
      var amount = 0
      var meta: ItemMeta? = null

      override fun append(index: Int, value: ValueAccessor) {
        when (index) {
          0    -> material = Material.getMaterial(value.asString()) ?: throw ThimbleDeserializationException("unrecognized material ${value.asString()}")
          1    -> amount = value.asInt()
          2    -> if (!value.isNull) meta = value.asComplex().asType(ItemMeta::class.java)
          else -> throw ThimbleDeserializationException("invalid value index: $index")
        }
      }

      override fun build(): ItemStack {
        return ItemStack(
          material ?: throw ThimbleDeserializationException("incomplete ItemStack record"),
          amount
        ).also { if (meta != null) it.itemMeta = meta }
      }
    }
}
