package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor

class SimpleListDeserializer<T : Any>(private val accessor: (ValueAccessor) -> T) : ComplexDeserializer<List<T>> {
  private var out: Array<Any?>? = null

  override fun append(index: Int, value: ValueAccessor) {
    if (index == 0)
      out = Array(value.asInt()) { null }
    else
      out!![index-1] = accessor(value)
  }

  @Suppress("UNCHECKED_CAST")
  override fun build(): List<T> = (out?.asList() as List<T>?) ?: emptyList()
}
