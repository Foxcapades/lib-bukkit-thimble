package io.foxcapades.mc.bukkit.thimble.unsafe

import org.bukkit.craftbukkit.v1_21_R2.inventory.CraftMetaMusicInstrument

import org.bukkit.inventory.meta.MusicInstrumentMeta

fun MusicInstrumentMeta(): MusicInstrumentMeta =
  CraftMetaMusicInstrument::class.java
    .getConstructor(CraftMetaItemClass)
    .apply { isAccessible = true }
    .newInstance(null)
