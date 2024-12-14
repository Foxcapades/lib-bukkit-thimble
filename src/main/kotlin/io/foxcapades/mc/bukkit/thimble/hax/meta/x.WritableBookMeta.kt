@file:JvmName("xWritableBookMeta")
package io.foxcapades.mc.bukkit.thimble.hax.meta

import org.bukkit.inventory.meta.WritableBookMeta

fun WritableBookMeta(): WritableBookMeta =
  Class.forName("org.bukkit.craftbukkit.v1_21_R1.inventory.CraftMetaBook")
    .getDeclaredConstructor(CraftMetaItemClass)
    .apply { isAccessible = true }
    .newInstance(null) as WritableBookMeta
