package io.foxcapades.mc.bukkit.thimble.types.bukkit

import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.impl.SimpleListTypeHandler
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

import org.bukkit.persistence.PersistentDataContainer


data object PersistentDataContainerListTypeHandler : SimpleListTypeHandler<PersistentDataContainer>() {
  override val typeIndicator: String
    get() = "<pdc>"

  override val elementType: Class<PersistentDataContainer>
    get() = PersistentDataContainer::class.java

  override fun writeValue(value: PersistentDataContainer, writer: ValueWriter) =
    writer.writeComplex(value)

  override fun readValue(reader: ValueAccessor) =
    reader.asComplex().asType(PersistentDataContainer::class.java)
}
