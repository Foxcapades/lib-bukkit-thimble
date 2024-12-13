package io.foxcapades.mc.bukkit.thimble.types.jvm

import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.ComplexTypeHandler
import io.foxcapades.mc.bukkit.thimble.util.B1
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter


/**
 * Basic [BooleanArray] (de)serialization provider.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since 1.0.0
 */
data object BooleanArrayTypeHandler : ComplexTypeHandler<BooleanArray> {
  override val actualType     get() = BooleanArray::class.java
  override val typeIndicator  get() = "[B]"
  override val currentVersion get() = B1

  override fun serialize(value: BooleanArray, writer: ValueWriter) {
    writer.writeInt(value.size)
    value.forEach { writer.writeBoolean(it) }
  }

  override fun deserializerFor(version: Byte): ComplexDeserializer<BooleanArray>? =
    when (version) {
      B1   -> object : ComplexDeserializer<BooleanArray> {
        var values: BooleanArray? = null

        override fun append(index: Int, value: ValueAccessor) {
          if (index == 0)
            values = BooleanArray(value.asInt())
          else
            values!![index - 1] = value.asBoolean()
        }

        override fun build() = values ?: BooleanArray(0)
      }

      else -> null
    }
}
