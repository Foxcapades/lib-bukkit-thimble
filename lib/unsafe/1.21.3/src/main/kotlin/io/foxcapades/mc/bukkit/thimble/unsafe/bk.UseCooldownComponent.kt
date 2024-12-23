package io.foxcapades.mc.bukkit.thimble.unsafe

import net.minecraft.world.item.component.UseCooldown

import org.bukkit.craftbukkit.v1_21_R2.inventory.components.CraftUseCooldownComponent

import org.bukkit.NamespacedKey
import org.bukkit.inventory.meta.components.UseCooldownComponent

@Suppress("UnstableApiUsage")
fun UseCooldownComponent(seconds: Float, cooldownGroup: NamespacedKey?): UseCooldownComponent =
  CraftUseCooldownComponent(UseCooldown(seconds, cooldownGroup?.toMinecraftKey().optional()))

