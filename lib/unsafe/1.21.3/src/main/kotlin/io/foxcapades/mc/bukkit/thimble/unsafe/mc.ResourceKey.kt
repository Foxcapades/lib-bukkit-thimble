package io.foxcapades.mc.bukkit.thimble.unsafe

import net.minecraft.core.IRegistry
import net.minecraft.resources.MinecraftKey
import net.minecraft.resources.ResourceKey

internal fun <T> ResourceKey(registry: ResourceKey<out IRegistry<T>>, mcKey: MinecraftKey) =
  ResourceKey.a(registry, mcKey)
