package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.ComplexTypeHandler
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

data object ShortArrayTypeHandler : ComplexTypeHandler<ShortArray> {
  override val currentVersion get() = 1

  override val typeIndicator get() = "[s]"

  override val actualType get() = ShortArray::class.java

  override fun deserializerFor(version: Int) = object : ComplexDeserializer<ShortArray> {
    var values: ShortArray? = null

    override fun append(index: Int, value: ValueAccessor) {
      if (index == 0)
        values = ShortArray(value.asInt())
      else
        values!![index-1] = value.asShort()
    }

    override fun build() = values ?: ShortArray(0)
  }

  override fun serialize(value: ShortArray, writer: ValueWriter) {
    writer.writeInt(value.size)
    value.forEach { writer.writeShort(it) }
  }
}
