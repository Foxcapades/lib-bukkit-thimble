package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.ListTypeHandler
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

abstract class SimpleListTypeHandler<T : Any> : ListTypeHandler<T> {
  override val currentVersion: Int
    get() = 1

  override fun deserializerFor(version: Int): ComplexDeserializer<out List<T>>? =
    SimpleListDeserializer(::readValue)

  override fun serialize(value: List<T>, writer: ValueWriter) {
    writer.writeInt(value.size)
    value.forEach { writeValue(it, writer) }
  }

  protected abstract fun writeValue(value: T, writer: ValueWriter)

  protected abstract fun readValue(reader: ValueAccessor): T
}
