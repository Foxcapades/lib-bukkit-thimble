package io.foxcapades.mc.bukkit.thimble.types.bukkit.persistence

import io.foxcapades.mc.bukkit.thimble.hax.NbtData
import io.foxcapades.mc.bukkit.thimble.hax.PersistentDataContainer
import io.foxcapades.mc.bukkit.thimble.hax.toNbtData
import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.parse.ThimbleDeserializationException
import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.ComplexTypeDefinition
import io.foxcapades.mc.bukkit.thimble.util.B1
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

import org.bukkit.persistence.PersistentDataContainer


open class PersistentDataContainerTypeDefinition : ComplexTypeDefinition<PersistentDataContainer> {
  override val actualType     get() = PersistentDataContainer::class.java
  override val typeIdentifier get() = "pdc"
  override val currentVersion get() = B1

  override fun serialize(value: PersistentDataContainer, writer: ValueWriter) {
    writer.writeBinary(value.toNbtData())
  }

  override fun deserializerFor(version: Byte): ComplexDeserializer<out PersistentDataContainer>? =
    when (version) {
      B1 -> object : ComplexDeserializer<PersistentDataContainer> {
        private var data: ByteArray = NbtData()

        @OptIn(ExperimentalEncodingApi::class)
        override fun append(index: Int, value: ValueAccessor) {
          if (index == 0)
            data = Base64.decode(value.asString())
          else
            throw ThimbleDeserializationException("invalid value index: $index")
        }

        override fun build(): PersistentDataContainer = PersistentDataContainer(data)
      }

      else -> null
    }
}
