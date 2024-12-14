package io.foxcapades.mc.bukkit.thimble.types.bukkit.entity

import io.foxcapades.mc.bukkit.thimble.hax.entity.EntitySnapshot
import io.foxcapades.mc.bukkit.thimble.hax.entity.getNbtData
import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.parse.ThimbleDeserializationException
import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.read.asType
import io.foxcapades.mc.bukkit.thimble.types.ComplexTypeDefinition
import io.foxcapades.mc.bukkit.thimble.util.B1
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter
import org.bukkit.NamespacedKey
import org.bukkit.Registry

import org.bukkit.entity.EntitySnapshot
import org.bukkit.entity.EntityType


open class EntitySnapshotTypeDefinition : ComplexTypeDefinition<EntitySnapshot> {
  override val actualType     get() = EntitySnapshot::class.java
  override val typeIdentifier get() = "b:e:ES"
  override val currentVersion get() = B1

  override fun serialize(value: EntitySnapshot, writer: ValueWriter) {
    writer.writeString(value.entityType.name)
    writer.writeBinary(value.getNbtData())
  }

  override fun deserializerFor(version: Byte): ComplexDeserializer<out EntitySnapshot>? =
    when (version) {
      B1   -> EntitySnapshotDeserializerV1()
      else -> null
    }
}

private class EntitySnapshotDeserializerV1 : ComplexDeserializer<EntitySnapshot> {
  private var entityType: EntityType? = null
  private var nbtData: ByteArray? = null

  override fun append(index: Int, value: ValueAccessor) {
    when (index) {
      0    -> entityType = Registry.ENTITY_TYPE.get(NamespacedKey.minecraft(value.asString()))
      1    -> nbtData = value.asComplex().asType()
      else -> throw ThimbleDeserializationException("invalid value index: $index")
    }
  }

  override fun build(): EntitySnapshot = EntitySnapshot(nbtData!!, entityType!!)
}
