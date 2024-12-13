package io.foxcapades.mc.bukkit.thimble.types.bukkit

import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.impl.SimpleListTypeDefinition
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

import org.bukkit.persistence.PersistentDataContainer


data object PersistentDataContainerListTypeDefinition : SimpleListTypeDefinition<PersistentDataContainer>() {
  override val typeIdentifier: String
    get() = "<pdc>"

  override val elementType: Class<PersistentDataContainer>
    get() = PersistentDataContainer::class.java

  override fun writeValue(value: PersistentDataContainer, writer: ValueWriter) =
    writer.writeComplex(value)

  override fun readValue(reader: ValueAccessor) =
    reader.asComplex().asType(PersistentDataContainer::class.java)
}
