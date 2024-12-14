@file:Suppress("UnstableApiUsage")
@file:JvmName("xToolComponent")
package io.foxcapades.mc.bukkit.thimble.hax.meta

import net.minecraft.core.HolderSet
import net.minecraft.world.item.component.Tool

import org.bukkit.craftbukkit.v1_21_R1.inventory.components.CraftToolComponent
import org.bukkit.craftbukkit.v1_21_R1.inventory.components.CraftToolComponent.CraftToolRule
import org.bukkit.craftbukkit.v1_21_R1.util.CraftMagicNumbers

import org.bukkit.Material
import org.bukkit.inventory.meta.components.ToolComponent
import org.bukkit.inventory.meta.components.ToolComponent.ToolRule

import java.util.Optional

fun ToolComponent(rules: List<ToolRule>, defaultMiningSpeed: Float, damagePerBlock: Int): ToolComponent =
  CraftToolComponent(Tool(
    rules.map { (it as CraftToolRule).handle },
    defaultMiningSpeed,
    damagePerBlock
  ))

fun ToolRule(blocks: Iterable<String>, speed: Float?, correctForDrops: Boolean?): ToolRule =
  CraftToolRule(Tool.a(
    HolderSet.a(blocks.map { CraftMagicNumbers.getBlock(Material.getMaterial(it)).s() }),
    Optional.ofNullable(speed),
    Optional.ofNullable(correctForDrops),
  ))
