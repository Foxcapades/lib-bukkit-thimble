@file:JvmName("xPersistentDataContainer")
package io.foxcapades.mc.bukkit.thimble.hax

import net.minecraft.nbt.NBTReadLimiter
import net.minecraft.nbt.NBTTagCompound

import org.bukkit.craftbukkit.v1_21_R1.persistence.CraftPersistentDataContainer
import org.bukkit.craftbukkit.v1_21_R1.persistence.CraftPersistentDataTypeRegistry

import io.foxcapades.mc.bukkit.thimble.ThimbleException

import org.bukkit.persistence.PersistentDataContainer

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.DataInputStream
import java.io.DataOutputStream

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


private fun NBTTagCompound.toByteArray(): ByteArray {
  val buffer = ByteArrayOutputStream(f() * 32 + 32)
  a(DataOutputStream(buffer))
  return buffer.toByteArray()
}
