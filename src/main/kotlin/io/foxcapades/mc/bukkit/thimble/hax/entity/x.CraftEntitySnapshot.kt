package io.foxcapades.mc.bukkit.thimble.hax.entity

import io.foxcapades.mc.bukkit.thimble.hax.mc.toByteArray
import net.minecraft.nbt.NBTReadLimiter
import net.minecraft.nbt.NBTTagCompound
import org.bukkit.craftbukkit.v1_21_R1.entity.CraftEntitySnapshot
import org.bukkit.entity.EntitySnapshot
import org.bukkit.entity.EntityType
import java.io.DataInputStream

fun EntitySnapshot.getNbtData(): ByteArray =
  (this as CraftEntitySnapshot).data.toByteArray()

fun EntitySnapshot(nbtData: ByteArray): EntitySnapshot =
  CraftEntitySnapshot.create(NBTTagCompound.b.c(DataInputStream(nbtData.inputStream()), NBTReadLimiter.a()))

fun EntitySnapshot(nbtData: ByteArray, type: EntityType): EntitySnapshot =
  CraftEntitySnapshot.create(NBTTagCompound.b.c(DataInputStream(nbtData.inputStream()), NBTReadLimiter.a()), type)
