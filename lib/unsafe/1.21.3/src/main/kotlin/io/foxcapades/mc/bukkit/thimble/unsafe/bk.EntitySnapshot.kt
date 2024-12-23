package io.foxcapades.mc.bukkit.thimble.unsafe

import org.bukkit.craftbukkit.v1_21_R2.entity.CraftEntitySnapshot
import org.bukkit.entity.EntitySnapshot
import org.bukkit.entity.EntityType
import java.io.ByteArrayOutputStream
import java.io.DataOutputStream

fun EntitySnapshot.getNBT(): ByteArray =
  ByteArrayOutputStream(128)
    .also { (this as CraftEntitySnapshot).data.writeTo(DataOutputStream(it)) }
    .toByteArray()

fun EntitySnapshot(type: EntityType, nbtData: ByteArray): EntitySnapshot =
  CraftEntitySnapshot.create(NBTTagCompound(nbtData), type)
