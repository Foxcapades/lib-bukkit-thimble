package io.foxcapades.mc.bukkit.thimble.types.bukkit

import io.foxcapades.mc.bukkit.thimble.ThimbleException
import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.parse.ThimbleDeserializationException
import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.ComplexTypeDefinition
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.DataInputStream
import java.io.DataOutputStream

import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

import org.bukkit.persistence.PersistentDataContainer

// UNSAFE!!
import net.minecraft.nbt.NBTReadLimiter
import net.minecraft.nbt.NBTTagCompound
import org.bukkit.craftbukkit.v1_21_R1.persistence.CraftPersistentDataContainer
import org.bukkit.craftbukkit.v1_21_R1.persistence.CraftPersistentDataTypeRegistry


data object PersistentDataContainerTypeDefinition : ComplexTypeDefinition<PersistentDataContainer> {
  override val typeIdentifier get() = "pdc"

  override val currentVersion get(): Byte = 1

  override val actualType get() = PersistentDataContainer::class.java

  override fun serialize(value: PersistentDataContainer, writer: ValueWriter) {
    if (value !is CraftPersistentDataContainer)
      throw ThimbleException("unsupported PersistentDataContainer implementation: ${value::class}")

    val buffer = ByteArrayOutputStream(512)

    value.toTagCompound().a(DataOutputStream(buffer))

    writer.writeBinary(buffer.toByteArray())
  }

  override fun deserializerFor(version: Byte): ComplexDeserializer<out PersistentDataContainer> =
    object : ComplexDeserializer<PersistentDataContainer> {
      private val data = CraftPersistentDataContainer(CraftPersistentDataTypeRegistry())

      override fun append(index: Int, value: ValueAccessor) {
        if (index == 0)
          data.putAll(parseValue(value.asString()))
        else
          throw ThimbleDeserializationException("invalid value index: $index")
      }

      override fun build(): PersistentDataContainer = data

      @OptIn(ExperimentalEncodingApi::class)
      private fun parseValue(value: String): NBTTagCompound =
        NBTTagCompound.b.c(DataInputStream(ByteArrayInputStream(Base64.decode(value))), NBTReadLimiter.a())
    }
}
