package io.foxcapades.mc.bukkit.thimble.types.jvm

import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.ComplexTypeDefinition
import io.foxcapades.mc.bukkit.thimble.util.B1
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter


/**
 * Basic [LongArray] (de)serialization provider.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since 1.0.0
 */
data object LongArrayTypeDefinition : ComplexTypeDefinition<LongArray> {
  override val actualType     get() = LongArray::class.java
  override val typeIdentifier  get() = "[l]"
  override val currentVersion get() = B1

  override fun deserializerFor(version: Byte): ComplexDeserializer<LongArray>? =
    when (version) {
      B1 -> object : ComplexDeserializer<LongArray> {
        var values: LongArray? = null

        override fun append(index: Int, value: ValueAccessor) {
          if (index == 0)
            values = LongArray(value.asInt())
          else
            values!![index-1] = value.asLong()
        }

        override fun build() = values ?: LongArray(0)
      }

      else -> null
    }

  override fun serialize(value: LongArray, writer: ValueWriter) {
    writer.writeInt(value.size)
    value.forEach { writer.writeLong(it) }
  }
}
