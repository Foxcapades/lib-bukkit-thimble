package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.ComplexTypeHandler
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

data object DoubleArrayTypeHandler : ComplexTypeHandler<DoubleArray> {
  override val currentVersion get() = 1

  override val typeIndicator get() = "[d]"

  override val actualType get() = DoubleArray::class.java

  override fun deserializerFor(version: Int) = object : ComplexDeserializer<DoubleArray> {
    var values: DoubleArray? = null

    override fun append(index: Int, value: ValueAccessor) {
      if (index == 0)
        values = DoubleArray(value.asInt())
      else
        values!![index-1] = value.asDouble()
    }

    override fun build() = values ?: DoubleArray(0)
  }

  override fun serialize(value: DoubleArray, writer: ValueWriter) {
    writer.writeInt(value.size)
    value.forEach { writer.writeRaw(DoubleTypeHandler.serializeToRaw(it)) }
  }
}
