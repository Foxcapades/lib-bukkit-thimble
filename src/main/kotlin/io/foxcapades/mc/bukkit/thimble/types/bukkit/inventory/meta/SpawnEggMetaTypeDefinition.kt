package io.foxcapades.mc.bukkit.thimble.types.bukkit.inventory.meta

import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter
import org.bukkit.inventory.meta.SpawnEggMeta

open class SpawnEggMetaTypeDefinition : ItemMetaTypeDefinitionBase<SpawnEggMeta>() {
  override val actualType     get() = SpawnEggMeta::class.java
  override val typeIdentifier get() = "b:m:SEM"

  override fun serialize(value: SpawnEggMeta, writer: ValueWriter) {
    value.spawnedEntity
    super.serialize(value, writer)
  }

  override fun deserializerFor(version: Byte): ComplexDeserializer<out SpawnEggMeta>? {
    TODO("Not yet implemented")
  }
}
