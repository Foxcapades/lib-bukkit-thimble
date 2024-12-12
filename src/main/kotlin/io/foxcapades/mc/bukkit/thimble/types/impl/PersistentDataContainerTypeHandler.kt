package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.InvalidThimbleValueException
import io.foxcapades.mc.bukkit.thimble.ThimbleException
import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.ComplexTypeHandler
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter
import net.minecraft.nbt.NBTReadLimiter
import net.minecraft.nbt.NBTTagCompound
import org.bukkit.craftbukkit.v1_21_R1.persistence.CraftPersistentDataContainer
import org.bukkit.craftbukkit.v1_21_R1.persistence.CraftPersistentDataTypeRegistry
import org.bukkit.persistence.PersistentDataContainer
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.DataInputStream
import java.io.DataOutputStream
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

data object PersistentDataContainerTypeHandler : ComplexTypeHandler<PersistentDataContainer> {
  override val typeIndicator get() = "pdc"

  override val currentVersion get() = 1

  override val actualType get() = PersistentDataContainer::class.java

  override fun serialize(value: PersistentDataContainer, writer: ValueWriter) {
    if (value !is CraftPersistentDataContainer)
      throw ThimbleException("unsupported PersistentDataContainer implementation: ${value::class}")

    val buffer = ByteArrayOutputStream(512)

    value.toTagCompound().a(DataOutputStream(buffer))

    writer.writeBinary(buffer.toByteArray())
  }

  override fun deserializerFor(version: Int): ComplexDeserializer<out PersistentDataContainer> =
    object : ComplexDeserializer<PersistentDataContainer> {
      private val data = CraftPersistentDataContainer(CraftPersistentDataTypeRegistry())

      override fun append(index: Int, value: ValueAccessor) {
        if (index == 0)
          data.putAll(parseValue(value.asString()))
        else
          throw InvalidThimbleValueException("more than one value appended for a PersistentDataContainer")
      }

      override fun build(): PersistentDataContainer = data

      @OptIn(ExperimentalEncodingApi::class)
      private fun parseValue(value: String): NBTTagCompound =
        NBTTagCompound.b.c(DataInputStream(ByteArrayInputStream(Base64.decode(value))), NBTReadLimiter.a())
    }
}
