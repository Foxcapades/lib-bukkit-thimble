package io.foxcapades.mc.bukkit.thimble.unsafe

import net.minecraft.resources.MinecraftKey
import org.bukkit.NamespacedKey

internal fun NamespacedKey.toMinecraftKey(): MinecraftKey =
  MinecraftKey(namespace, key)

@Suppress("UnstableApiUsage")
internal fun MinecraftKey.toNamespacedKey(): NamespacedKey =
  NamespacedKey(b(), a())

internal fun MinecraftKey(namespace: String, key: String): MinecraftKey =
  MinecraftKey.a(namespace, key)
