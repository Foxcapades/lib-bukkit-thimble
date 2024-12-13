package io.foxcapades.mc.bukkit.thimble.types

import io.foxcapades.mc.bukkit.thimble.ThimbleException
import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

interface ComplexTypeHandler<T : Any> : TypeHandler<T> {
  @Throws(ThimbleException::class)
  fun serialize(value: T, writer: ValueWriter)

  @Throws(ThimbleException::class)
  fun serializeNull(writer: ValueWriter) = writer.writeNull()

  override fun deserializerFor(version: Byte): ComplexDeserializer<out T>?
}
