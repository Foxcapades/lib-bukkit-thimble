@file:Suppress("UnstableApiUsage")
@file:JvmName("xFoodComponent")
package io.foxcapades.mc.bukkit.thimble.hax.meta

import net.minecraft.world.food.FoodInfo

import org.bukkit.craftbukkit.v1_21_R1.inventory.CraftItemStack
import org.bukkit.craftbukkit.v1_21_R1.inventory.components.CraftFoodComponent
import org.bukkit.craftbukkit.v1_21_R1.inventory.components.CraftFoodComponent.CraftFoodEffect
import org.bukkit.craftbukkit.v1_21_R1.potion.CraftPotionUtil

import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.components.FoodComponent
import org.bukkit.inventory.meta.components.FoodComponent.FoodEffect
import org.bukkit.potion.PotionEffect

import java.util.Optional


fun FoodComponent(nutrition: Int, saturation: Float, canAlwaysEat: Boolean, eatSeconds: Float, convertsTo: ItemStack?, effects: List<FoodEffect>?): FoodComponent =
  CraftFoodComponent(
    FoodInfo(
    nutrition,
    saturation,
    canAlwaysEat,
    eatSeconds,
    Optional.ofNullable(convertsTo?.let { CraftItemStack.asNMSCopy(it) }),
    effects?.map { (it as CraftFoodEffect).handle }
      ?: emptyList()
  ))

fun FoodEffect(effect: PotionEffect, probability: Float): FoodEffect =
  CraftFoodEffect(FoodInfo.b(CraftPotionUtil.fromBukkit(effect), probability))
