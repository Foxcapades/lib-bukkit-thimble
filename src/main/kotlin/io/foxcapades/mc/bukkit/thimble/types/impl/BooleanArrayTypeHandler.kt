package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.ComplexTypeHandler
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

data object BooleanArrayTypeHandler : ComplexTypeHandler<BooleanArray> {
  override val currentVersion get() = 1

  override val typeIndicator get() = "[B]"

  override val actualType get() = BooleanArray::class.java

  override fun deserializerFor(version: Int) = object : ComplexDeserializer<BooleanArray> {
    var values: BooleanArray? = null

    override fun append(index: Int, value: ValueAccessor) {
      if (index == 0)
        values = BooleanArray(value.asInt())
      else
        values!![index-1] = value.asBoolean()
    }

    override fun build() = values ?: BooleanArray(0)
  }

  override fun serialize(value: BooleanArray, writer: ValueWriter) {
    writer.writeInt(value.size)
    value.forEach { writer.writeBoolean(it) }
  }
}
