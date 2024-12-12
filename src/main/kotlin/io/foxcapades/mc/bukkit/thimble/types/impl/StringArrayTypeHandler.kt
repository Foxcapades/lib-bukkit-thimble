package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.ComplexTypeHandler
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

data object StringArrayTypeHandler : ComplexTypeHandler<Array<String>> {
  override val currentVersion get() = 1

  override val typeIndicator get() = "[S]"

  override val actualType get() = Array<String>::class.java

  override fun deserializerFor(version: Int) = object : ComplexDeserializer<Array<String>> {
    var values: Array<String?>? = null

    override fun append(index: Int, value: ValueAccessor) {
      if (index == 0)
        values = arrayOfNulls(value.asInt())
      else
        values!![index-1] = value.asString()
    }

    @Suppress("UNCHECKED_CAST")
    override fun build() = values as Array<String>? ?: emptyArray<String>()
  }

  override fun serialize(value: Array<String>, writer: ValueWriter) {
    writer.writeInt(value.size)
    value.forEach { writer.writeString(it) }
  }
}
