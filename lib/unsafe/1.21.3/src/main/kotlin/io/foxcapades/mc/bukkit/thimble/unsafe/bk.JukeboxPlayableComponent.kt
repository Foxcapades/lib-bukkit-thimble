package io.foxcapades.mc.bukkit.thimble.unsafe

import net.minecraft.core.registries.Registries
import net.minecraft.world.item.EitherHolder
import net.minecraft.world.item.JukeboxPlayable

import org.bukkit.craftbukkit.v1_21_R2.inventory.components.CraftJukeboxComponent

import org.bukkit.NamespacedKey
import org.bukkit.inventory.meta.components.JukeboxPlayableComponent

@Suppress("UnstableApiUsage")
fun JukeboxPlayableComponent(songKey: NamespacedKey, showInTooltip: Boolean): JukeboxPlayableComponent =
  CraftJukeboxComponent(JukeboxPlayable(
    EitherHolder(ResourceKey(Registries.L, songKey.toMinecraftKey())),
    showInTooltip,
  ))


