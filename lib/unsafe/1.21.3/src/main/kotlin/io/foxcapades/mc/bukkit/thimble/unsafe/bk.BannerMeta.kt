package io.foxcapades.mc.bukkit.thimble.unsafe

import org.bukkit.craftbukkit.v1_21_R2.inventory.CraftMetaBanner

import org.bukkit.inventory.meta.BannerMeta

fun BannerMeta(): BannerMeta =
  CraftMetaBanner::class.java
    .getConstructor(CraftMetaItemClass)
    .apply { isAccessible = true }
    .newInstance(null)
