package io.foxcapades.mc.bukkit.thimble.hax.mc

import net.minecraft.nbt.NBTTagCompound
import java.io.ByteArrayOutputStream
import java.io.DataOutputStream


internal fun NBTTagCompound.toByteArray(): ByteArray {
  val buffer = ByteArrayOutputStream(f() * 32 + 32)
  a(DataOutputStream(buffer))
  return buffer.toByteArray()
}
