package io.foxcapades.mc.bukkit.thimble.types.bukkit

import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.impl.SimpleListTypeHandler
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

import org.bukkit.inventory.meta.components.FoodComponent.FoodEffect

@Suppress("UnstableApiUsage")
data object FoodEffectListTypeHandler : SimpleListTypeHandler<FoodEffect>() {
  override val elementType: Class<FoodEffect>
    get() = FoodEffect::class.java

  override val typeIndicator: String
    get() = "<${FoodEffectTypeHandler.typeIndicator}>"

  override fun writeValue(value: FoodEffect, writer: ValueWriter) {
    writer.writeComplex(value)
  }

  override fun readValue(reader: ValueAccessor): FoodEffect {
    return reader.asComplex().asType(elementType)
  }
}
