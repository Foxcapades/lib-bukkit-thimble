package io.foxcapades.mc.bukkit.thimble.types.jvm

import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.ComplexTypeDefinition
import io.foxcapades.mc.bukkit.thimble.util.B1
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter


/**
 * Basic [ShortArray] (de)serialization provider.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since 1.0.0
 */
open class ShortArrayTypeDefinition : ComplexTypeDefinition<ShortArray> {
  override val actualType     get() = ShortArray::class.java
  override val currentVersion get() = B1
  override val typeIdentifier  get() = "[s]"

  override fun deserializerFor(version: Byte): ComplexDeserializer<ShortArray>? =
    when (version) {
      B1 -> object : ComplexDeserializer<ShortArray> {
        var values: ShortArray? = null

        override fun append(index: Int, value: ValueAccessor) {
          if (index == 0)
            values = ShortArray(value.asInt())
          else
            values!![index-1] = value.asShort()
        }

        override fun build() = values ?: ShortArray(0)
      }

      else -> null
    }

  override fun serialize(value: ShortArray, writer: ValueWriter) {
    writer.writeInt(value.size)
    value.forEach { writer.writeShort(it) }
  }
}
