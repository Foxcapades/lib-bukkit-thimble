package io.foxcapades.mc.bukkit.thimble.types

import io.foxcapades.mc.bukkit.thimble.ThimbleException
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

interface ListTypeHandler<T : Any> : TypeHandler<List<*>> {
  override val actualType: Class<List<*>>
    get() = List::class.java

  val elementType: Class<T>

  @Throws(ThimbleException::class)
  fun serialize(value: List<T>, writer: ValueWriter)

  @Throws(ThimbleException::class)
  fun serializeNull(writer: ValueWriter) = writer.writeNull()
}
