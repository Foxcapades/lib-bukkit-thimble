@file:JvmName("xItemMeta")
package io.foxcapades.mc.bukkit.thimble.hax.meta

import org.bukkit.inventory.meta.ItemMeta

internal val CraftMetaItemClass: Class<*> = Class.forName("org.bukkit.craftbukkit.v1_21_R1.inventory.CraftMetaItem")

fun ItemMeta(): ItemMeta =
  CraftMetaItemClass
    .let { it.getDeclaredConstructor(it) }
    .apply { isAccessible = true }
    .newInstance(null) as ItemMeta
