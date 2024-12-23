package io.foxcapades.mc.bukkit.thimble.unsafe

import net.minecraft.core.IRegistryCustom
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.nbt.NBTTagCompound

import org.bukkit.craftbukkit.v1_21_R2.inventory.CraftItemStack

import org.bukkit.inventory.ItemStack


fun ItemStack.encode(): ByteArray =
  when (this) {
    is CraftItemStack -> this.encode()
    else              -> CraftItemStack.asCraftCopy(this).encode()
  }

internal fun CraftItemStack.encode(): ByteArray =
  (CraftItemStack.asNMSCopy(this)
    .b(IRegistryCustom.c(listOf(BuiltInRegistries.g))) as NBTTagCompound)
    .encode(512)
