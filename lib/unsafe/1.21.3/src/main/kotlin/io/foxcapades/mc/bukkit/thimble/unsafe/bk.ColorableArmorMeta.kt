package io.foxcapades.mc.bukkit.thimble.unsafe

import org.bukkit.craftbukkit.v1_21_R2.inventory.CraftMetaColorableArmor
import org.bukkit.inventory.meta.ColorableArmorMeta

fun ColorableArmorMeta(): ColorableArmorMeta =
  CraftMetaColorableArmor::class.java
    .getConstructor(CraftMetaItemClass)
    .apply { isAccessible = true }
    .newInstance(null)
