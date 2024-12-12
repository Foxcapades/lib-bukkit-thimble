package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.ComplexTypeHandler
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

data object FloatArrayTypeHandler : ComplexTypeHandler<FloatArray> {
  override val currentVersion get() = 1

  override val typeIndicator get() = "[f]"

  override val actualType get() = FloatArray::class.java

  override fun deserializerFor(version: Int) = object : ComplexDeserializer<FloatArray> {
    var values: FloatArray? = null

    override fun append(index: Int, value: ValueAccessor) {
      if (index == 0)
        values = FloatArray(value.asInt())
      else
        values!![index-1] = value.asFloat()
    }

    override fun build() = values ?: FloatArray(0)
  }

  override fun serialize(value: FloatArray, writer: ValueWriter) {
    writer.writeInt(value.size)
    value.forEach { writer.writeRaw(FloatTypeHandler.serializeToRaw(it)) }
  }
}
