package io.foxcapades.mc.bukkit.thimble.unsafe

import net.minecraft.nbt.NBTReadLimiter
import net.minecraft.nbt.NBTTagCompound
import java.io.ByteArrayInputStream
import java.io.DataInputStream
import java.io.DataOutput


internal fun NBTTagCompound(data: ByteArray): NBTTagCompound =
  NBTTagCompound.b.c(DataInputStream(ByteArrayInputStream(data)), NBTReadLimiter.a())

@Suppress("NOTHING_TO_INLINE")
internal inline fun NBTTagCompound.writeTo(output: DataOutput) = a(output)


