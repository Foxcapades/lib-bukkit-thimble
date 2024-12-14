package io.foxcapades.mc.bukkit.thimble.types.bukkit

import io.foxcapades.mc.bukkit.thimble.hax.meta.FoodEffect
import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.parse.ThimbleDeserializationException
import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.read.asType
import io.foxcapades.mc.bukkit.thimble.types.ComplexTypeDefinition
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

import org.bukkit.inventory.meta.components.FoodComponent.FoodEffect
import org.bukkit.potion.PotionEffect


@Suppress("UnstableApiUsage")
data object FoodEffectTypeDefinition : ComplexTypeDefinition<FoodEffect> {
  override val typeIdentifier: String
    get() = "b:m:FE"

  override val currentVersion: Byte
    get() = 1

  override val actualType: Class<FoodEffect>
    get() = FoodEffect::class.java

  override fun serialize(value: FoodEffect, writer: ValueWriter) {
    writer.writeComplex(value.effect)
    writer.writeFloat(value.probability)
  }

  override fun deserializerFor(version: Byte): ComplexDeserializer<out FoodEffect> =
    object : ComplexDeserializer<FoodEffect> {
      var effect: PotionEffect? = null
      var probability = 0f

      override fun append(index: Int, value: ValueAccessor) {
        when (index) {
          0    -> effect = value.asComplex().asType()
          1    -> probability = value.asFloat()
          else -> throw ThimbleDeserializationException("invalid value index: $index")
        }
      }

      override fun build(): FoodEffect = FoodEffect(effect!!, probability)
    }
}
