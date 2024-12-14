@file:Suppress("UnstableApiUsage")
@file:JvmName("xJukeboxPlayableComponent")
package io.foxcapades.mc.bukkit.thimble.hax.meta

import net.minecraft.core.registries.Registries
import net.minecraft.resources.MinecraftKey
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.EitherHolder
import net.minecraft.world.item.JukeboxPlayable

import org.bukkit.craftbukkit.v1_21_R1.inventory.components.CraftJukeboxComponent

import org.bukkit.inventory.meta.components.JukeboxPlayableComponent


fun JukeboxPlayableComponent(songKey: String, showInTooltip: Boolean): JukeboxPlayableComponent =
  CraftJukeboxComponent(JukeboxPlayable(
    EitherHolder(ResourceKey.a(Registries.L, MinecraftKey.a(songKey))),
    showInTooltip,
  ))
