package io.foxcapades.mc.bukkit.thimble.types.bukkit

import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.impl.SimpleListTypeDefinition
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

import org.bukkit.inventory.meta.components.FoodComponent.FoodEffect

@Suppress("UnstableApiUsage")
data object FoodEffectListTypeDefinition : SimpleListTypeDefinition<FoodEffect>() {
  override val elementType: Class<FoodEffect>
    get() = FoodEffect::class.java

  override val typeIdentifier: String
    get() = "<${FoodEffectTypeDefinition.typeIdentifier}>"

  override fun writeValue(value: FoodEffect, writer: ValueWriter) {
    writer.writeComplex(value)
  }

  override fun readValue(reader: ValueAccessor): FoodEffect {
    return reader.asComplex().asType(elementType)
  }
}
