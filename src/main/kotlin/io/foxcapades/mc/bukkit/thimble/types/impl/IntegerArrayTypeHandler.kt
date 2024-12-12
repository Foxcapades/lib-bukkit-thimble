package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.ComplexTypeHandler
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

data object IntegerArrayTypeHandler : ComplexTypeHandler<IntArray> {
  override val currentVersion get() = 1

  override val typeIndicator get() = "[i]"

  override val actualType get() = IntArray::class.java

  override fun deserializerFor(version: Int) = object : ComplexDeserializer<IntArray> {
    var values: IntArray? = null

    override fun append(index: Int, value: ValueAccessor) {
      if (index == 0)
        values = IntArray(value.asInt())
      else
        values!![index-1] = value.asInt()
    }

    override fun build() = values ?: IntArray(0)
  }

  override fun serialize(value: IntArray, writer: ValueWriter) {
    writer.writeInt(value.size)
    value.forEach { writer.writeInt(it) }
  }
}
