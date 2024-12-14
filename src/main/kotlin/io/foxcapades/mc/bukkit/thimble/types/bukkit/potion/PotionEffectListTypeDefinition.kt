package io.foxcapades.mc.bukkit.thimble.types.bukkit.potion

import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.read.asType
import io.foxcapades.mc.bukkit.thimble.types.impl.SimpleListTypeDefinition
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

import org.bukkit.potion.PotionEffect

open class PotionEffectListTypeDefinition : SimpleListTypeDefinition<PotionEffect>() {
  override val elementType    get() = PotionEffect::class.java
  override val typeIdentifier get() = "<b:p:PE>"

  override fun writeValue(value: PotionEffect, writer: ValueWriter) =
    writer.writeComplex(value)

  override fun readValue(reader: ValueAccessor): PotionEffect =
    reader.asComplex().asType()
}
