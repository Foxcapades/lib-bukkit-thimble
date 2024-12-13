package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.parse.ThimbleDeserializationException
import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.read.asType
import io.foxcapades.mc.bukkit.thimble.types.ComplexTypeHandler
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter
import net.minecraft.world.food.FoodInfo
import org.bukkit.craftbukkit.v1_21_R1.inventory.components.CraftFoodComponent.CraftFoodEffect
import org.bukkit.craftbukkit.v1_21_R1.potion.CraftPotionUtil
import org.bukkit.inventory.meta.components.FoodComponent.FoodEffect
import org.bukkit.potion.PotionEffect

@Suppress("UnstableApiUsage")
data object FoodEffectTypeHandler : ComplexTypeHandler<FoodEffect> {
  override val typeIndicator: String
    get() = "b:m:FE"

  override val currentVersion: Int
    get() = 1

  override val actualType: Class<FoodEffect>
    get() = FoodEffect::class.java

  override fun serialize(value: FoodEffect, writer: ValueWriter) {
    writer.writeComplex(value.effect)
    writer.writeFloat(value.probability)
  }

  override fun deserializerFor(version: Int): ComplexDeserializer<out FoodEffect> =
    object : ComplexDeserializer<FoodEffect> {
      var effect: PotionEffect? = null
      var probability = 0f

      override fun append(index: Int, value: ValueAccessor) {
        when (index) {
          0    -> effect = value.asComplex().asType()
          1    -> probability = value.asFloat()
          else -> throw ThimbleDeserializationException("unexpected value at index $index")
        }
      }

      override fun build(): FoodEffect =
        CraftFoodEffect(FoodInfo.b(CraftPotionUtil.fromBukkit(effect), probability))
    }
}
