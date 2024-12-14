package io.foxcapades.mc.bukkit.thimble.types.bukkit.persistence

import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.impl.SimpleListTypeDefinition
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

import org.bukkit.persistence.PersistentDataContainer


open class PersistentDataContainerListTypeDefinition : SimpleListTypeDefinition<PersistentDataContainer>() {
  override val elementType    get() = PersistentDataContainer::class.java
  override val typeIdentifier get() = "<pdc>"

  override fun writeValue(value: PersistentDataContainer, writer: ValueWriter) =
    writer.writeComplex(value)

  override fun readValue(reader: ValueAccessor) =
    reader.asComplex().asType(PersistentDataContainer::class.java)
}
