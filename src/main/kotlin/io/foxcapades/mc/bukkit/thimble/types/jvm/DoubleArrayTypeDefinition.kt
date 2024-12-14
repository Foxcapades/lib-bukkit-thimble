package io.foxcapades.mc.bukkit.thimble.types.jvm

import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.ComplexTypeDefinition
import io.foxcapades.mc.bukkit.thimble.util.B1
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter


/**
 * Basic [DoubleArray] (de)serialization provider.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since 1.0.0
 */
open class DoubleArrayTypeDefinition : ComplexTypeDefinition<DoubleArray> {
  override val actualType     get() = DoubleArray::class.java
  override val typeIdentifier get() = "[d]"
  override val currentVersion get() = B1

  override fun serialize(value: DoubleArray, writer: ValueWriter) {
    writer.writeInt(value.size)
    value.forEach { writer.writeRaw(DoubleTypeDefinition.serializeToRaw(it)) }
  }

  override fun deserializerFor(version: Byte): ComplexDeserializer<DoubleArray>? =
    when (version) {
      B1   -> object : ComplexDeserializer<DoubleArray> {
        var values: DoubleArray? = null

        override fun append(index: Int, value: ValueAccessor) {
          if (index == 0)
            values = DoubleArray(value.asInt())
          else
            values!![index-1] = value.asDouble()
        }

        override fun build() = values ?: DoubleArray(0)
      }

      else -> null
    }
}
