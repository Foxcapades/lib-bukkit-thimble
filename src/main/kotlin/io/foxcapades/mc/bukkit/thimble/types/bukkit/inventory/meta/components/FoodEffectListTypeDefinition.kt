package io.foxcapades.mc.bukkit.thimble.types.bukkit.inventory.meta.components

import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.impl.SimpleListTypeDefinition
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

import org.bukkit.inventory.meta.components.FoodComponent.FoodEffect


@Suppress("UnstableApiUsage")
open class FoodEffectListTypeDefinition : SimpleListTypeDefinition<FoodEffect>() {
  override val elementType    get() = FoodEffect::class.java
  override val typeIdentifier get() = "<${FoodEffectKey}>"

  override fun writeValue(value: FoodEffect, writer: ValueWriter) {
    writer.writeComplex(value)
  }

  override fun readValue(reader: ValueAccessor): FoodEffect {
    return reader.asComplex().asType(elementType)
  }
}
