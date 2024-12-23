package io.foxcapades.mc.bukkit.thimble.unsafe

import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.item.component.Tool

import org.bukkit.craftbukkit.v1_21_R2.inventory.components.CraftToolComponent
import org.bukkit.craftbukkit.v1_21_R2.inventory.components.CraftToolComponent.CraftToolRule
import org.bukkit.craftbukkit.v1_21_R2.util.CraftMagicNumbers

import org.bukkit.Material
import org.bukkit.inventory.meta.components.ToolComponent
import org.bukkit.inventory.meta.components.ToolComponent.ToolRule

@Suppress("UnstableApiUsage")
fun ToolComponent(rules: List<ToolRule>, defaultMiningSpeed: Float, damagePerBlock: Int): ToolComponent =
  CraftToolComponent(Tool(rules.map { (it as CraftToolRule).handle }, defaultMiningSpeed, damagePerBlock))


@Suppress("UnstableApiUsage")
fun ToolRule(blocks: Iterable<Material>, speed: Float?, correctForDrops: Boolean?): ToolRule =
  CraftToolRule(Tool.a(
    HolderSet(blocks.map { BuiltInRegistries.e.f(CraftMagicNumbers.getBlock(it)) }),
    speed.optional(),
    correctForDrops.optional(),
  ))
