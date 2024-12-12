package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.ComplexTypeHandler
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

data object LongArrayTypeHandler : ComplexTypeHandler<LongArray> {
  override val currentVersion get() = 1

  override val typeIndicator get() = "[l]"

  override val actualType get() = LongArray::class.java

  override fun deserializerFor(version: Int) = object : ComplexDeserializer<LongArray> {
    var values: LongArray? = null

    override fun append(index: Int, value: ValueAccessor) {
      if (index == 0)
        values = LongArray(value.asInt())
      else
        values!![index-1] = value.asLong()
    }

    override fun build() = values ?: LongArray(0)
  }

  override fun serialize(value: LongArray, writer: ValueWriter) {
    writer.writeInt(value.size)
    value.forEach { writer.writeLong(it) }
  }
}
