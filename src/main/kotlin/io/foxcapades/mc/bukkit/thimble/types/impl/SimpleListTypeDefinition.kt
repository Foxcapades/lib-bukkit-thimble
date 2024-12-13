package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.ListTypeDefinition
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

abstract class SimpleListTypeDefinition<T : Any> : ListTypeDefinition<T> {
  override val currentVersion: Byte
    get() = 1

  override fun deserializerFor(version: Byte): ComplexDeserializer<out List<T>>? =
    SimpleListDeserializer(::readValue)

  override fun serialize(values: List<T>, writer: ValueWriter) {
    writer.writeInt(values.size)
    values.forEach { writeValue(it, writer) }
  }

  protected abstract fun writeValue(value: T, writer: ValueWriter)

  protected abstract fun readValue(reader: ValueAccessor): T
}
