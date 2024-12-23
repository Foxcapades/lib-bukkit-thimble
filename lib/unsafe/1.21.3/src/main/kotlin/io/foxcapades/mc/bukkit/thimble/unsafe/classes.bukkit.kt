@file:Suppress("UNCHECKED_CAST")
package io.foxcapades.mc.bukkit.thimble.unsafe

import org.bukkit.inventory.meta.ItemMeta


inline val CraftBukkitPackagePrefix get() = "org.bukkit.craftbukkit.v1_21_R2"

internal inline val CraftHolderUtilClass: Class<*>
  get() = Class.forName("$CraftBukkitPackagePrefix.inventory.components.CraftHolderUtil")

internal inline val CraftMetaItemClass
  get() = Class.forName("$CraftBukkitPackagePrefix.inventory.CraftMetaItem")
    as Class<out ItemMeta>
