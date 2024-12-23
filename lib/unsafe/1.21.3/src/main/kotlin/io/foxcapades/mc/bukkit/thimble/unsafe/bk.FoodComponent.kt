package io.foxcapades.mc.bukkit.thimble.unsafe

import net.minecraft.world.food.FoodInfo

import org.bukkit.craftbukkit.v1_21_R2.inventory.components.CraftFoodComponent

import org.bukkit.inventory.meta.components.FoodComponent

@Suppress("UnstableApiUsage")
fun FoodComponent(nutrition: Int, saturation: Float, canAlwaysEat: Boolean): FoodComponent =
  CraftFoodComponent(FoodInfo(nutrition, saturation, canAlwaysEat))
