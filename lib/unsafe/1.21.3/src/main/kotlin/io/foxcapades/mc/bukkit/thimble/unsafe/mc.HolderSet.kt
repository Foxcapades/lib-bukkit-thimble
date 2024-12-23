package io.foxcapades.mc.bukkit.thimble.unsafe

import net.minecraft.core.Holder
import net.minecraft.core.HolderSet

typealias HolderSet_Named<T> = HolderSet.a<T>

@Suppress("NOTHING_TO_INLINE")
internal inline fun <T> HolderSet(value: List<Holder<T>>): HolderSet_Named<T> =
  HolderSet.a(value)
