package io.foxcapades.mc.bukkit.thimble.unsafe

import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.equipment.Equippable

import org.bukkit.craftbukkit.v1_21_R2.CraftEquipmentSlot
import org.bukkit.craftbukkit.v1_21_R2.CraftSound
import org.bukkit.craftbukkit.v1_21_R2.inventory.components.CraftEquippableComponent

import org.bukkit.NamespacedKey
import org.bukkit.Sound
import org.bukkit.entity.EntityType
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.meta.components.EquippableComponent

import java.util.Optional

@Suppress("UnstableApiUsage")
fun EquippableComponent(
  slot: EquipmentSlot,
  sound: Sound,
  model: NamespacedKey?,
  cameraOverlay: NamespacedKey?,
  allowedEntities: Iterable<EntityType>?,
  dispensible: Boolean,
  swappable: Boolean,
  damageOnHurt: Boolean,
): EquippableComponent =
  CraftEquippableComponent(Equippable(
    CraftEquipmentSlot.getNMS(slot),
    CraftSound.bukkitToMinecraftHolder(sound),
    Optional.ofNullable(model?.toMinecraftKey()),
    Optional.ofNullable(cameraOverlay?.toMinecraftKey()),
    allowedEntities?.let { CraftHolderUtil.parse(it, Registries.z, BuiltInRegistries.f) }
      .let { Optional.ofNullable(it) },
    dispensible,
    swappable,
    damageOnHurt,
  ))
