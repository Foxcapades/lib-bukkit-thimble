package io.foxcapades.mc.bukkit.thimble.types.jvm

import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.ComplexTypeDefinition
import io.foxcapades.mc.bukkit.thimble.util.B1
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

open class IntegerArrayTypeDefinition : ComplexTypeDefinition<IntArray> {
  override val actualType     get() = IntArray::class.java
  override val typeIdentifier get() = "[i]"
  override val currentVersion get() = B1

  override fun deserializerFor(version: Byte): ComplexDeserializer<IntArray>? =
    when (version) {
      B1 -> object : ComplexDeserializer<IntArray> {
        var values: IntArray? = null

        override fun append(index: Int, value: ValueAccessor) {
          if (index == 0)
            values = IntArray(value.asInt())
          else
            values!![index - 1] = value.asInt()
        }

        override fun build() = values ?: IntArray(0)
      }

      else -> null
    }

  override fun serialize(value: IntArray, writer: ValueWriter) {
    writer.writeInt(value.size)
    value.forEach { writer.writeInt(it) }
  }
}
