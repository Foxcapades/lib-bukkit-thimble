package io.foxcapades.mc.bukkit.thimble.types.bukkit

import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.read.asType
import io.foxcapades.mc.bukkit.thimble.types.impl.SimpleListTypeDefinition
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

import org.bukkit.attribute.AttributeModifier

data object AttributeModifierListTypeDefinition : SimpleListTypeDefinition<AttributeModifier>() {
  override val elementType    get() = AttributeModifier::class.java
  override val typeIdentifier get() = "<${AttributeModifierTypeDefinition.typeIdentifier}>"

  override fun writeValue(value: AttributeModifier, writer: ValueWriter) =
    writer.writeComplex(value)

  override fun readValue(reader: ValueAccessor): AttributeModifier =
    reader.asComplex().asType()
}
