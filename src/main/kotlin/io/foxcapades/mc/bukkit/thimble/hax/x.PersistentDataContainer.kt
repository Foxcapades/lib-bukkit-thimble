@file:JvmName("xPersistentDataContainer")
package io.foxcapades.mc.bukkit.thimble.hax

import net.minecraft.nbt.NBTReadLimiter
import net.minecraft.nbt.NBTTagCompound

import org.bukkit.craftbukkit.v1_21_R1.persistence.CraftPersistentDataContainer
import org.bukkit.craftbukkit.v1_21_R1.persistence.CraftPersistentDataTypeRegistry

import io.foxcapades.mc.bukkit.thimble.ThimbleException
import io.foxcapades.mc.bukkit.thimble.hax.mc.toByteArray

import org.bukkit.persistence.PersistentDataContainer

import java.io.ByteArrayInputStream
import java.io.DataInputStream

fun PersistentDataContainer(nbtData: ByteArray): PersistentDataContainer =
  CraftPersistentDataContainer(CraftPersistentDataTypeRegistry()).apply {
    putAll(NBTTagCompound.b.c(DataInputStream(ByteArrayInputStream(nbtData)), NBTReadLimiter.a()))
  }


fun PersistentDataContainer.toNbtData(): ByteArray {
  if (this !is CraftPersistentDataContainer)
    throw ThimbleException("unsupported PersistentDataContainer implementation: ${this::class}")

  return toTagCompound().toByteArray()
}


fun NbtData(): ByteArray =
  NBTTagCompound().toByteArray()

