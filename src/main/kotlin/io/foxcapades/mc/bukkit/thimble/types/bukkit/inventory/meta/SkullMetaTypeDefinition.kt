package io.foxcapades.mc.bukkit.thimble.types.bukkit.inventory.meta

import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.read.asType
import io.foxcapades.mc.bukkit.thimble.util.B1
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.meta.SkullMeta
import org.bukkit.profile.PlayerProfile

open class SkullMetaTypeDefinition : ItemMetaTypeDefinitionBase<SkullMeta>() {
  override val actualType     get() = SkullMeta::class.java
  override val typeIdentifier get() = "b:m:SM"

  override fun serialize(value: SkullMeta, writer: ValueWriter) {
    writer.writeComplexOrNull(value.ownerProfile, PlayerProfile::class.java)
    writer.writeKeyOrNull(value.noteBlockSound)

    super.serialize(value, writer)
  }

  override fun deserializerFor(version: Byte): ComplexDeserializer<out SkullMeta>? =
    when (version) {
      B1   -> SkullMetaDeserializerV1()
      else -> null
    }
}

private class SkullMetaDeserializerV1 : ItemMetaDeserializerBaseV1<SkullMeta>() {
  private var owningPlayer: PlayerProfile? = null
  private var noteBlockSound: NamespacedKey? = null

  override fun append(index: Int, value: ValueAccessor) {
    when (index) {
      0    -> owningPlayer = value.asComplexOrNull()?.asType()
      1    -> noteBlockSound = value.asStringOrNull()?.let(NamespacedKey::fromString)
      else -> super.append(index-2, value)
    }
  }

  override fun populateItemMeta(itemMeta: SkullMeta) {
    itemMeta.ownerProfile = owningPlayer
    itemMeta.noteBlockSound = noteBlockSound
    super.populateItemMeta(itemMeta)
  }

  override fun newItemMetaInstance(): SkullMeta =
    Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD) as SkullMeta
}
