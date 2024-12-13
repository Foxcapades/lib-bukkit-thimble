package io.foxcapades.mc.bukkit.thimble.types.jvm

import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.ComplexTypeHandler
import io.foxcapades.mc.bukkit.thimble.util.B1
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter


/**
 * Basic [FloatArray] (de)serialization provider.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since 1.0.0
 */
data object FloatArrayTypeHandler : ComplexTypeHandler<FloatArray> {
  override val actualType     get() = FloatArray::class.java
  override val typeIndicator  get() = "[f]"
  override val currentVersion get() = B1

  override fun deserializerFor(version: Byte): ComplexDeserializer<FloatArray>? =
    when (version) {
      B1   -> object : ComplexDeserializer<FloatArray> {
        var values: FloatArray? = null

        override fun append(index: Int, value: ValueAccessor) {
          if (index == 0)
            values = FloatArray(value.asInt())
          else
            values!![index-1] = value.asFloat()
        }

        override fun build() = values ?: FloatArray(0)
      }

      else -> null
    }

  override fun serialize(value: FloatArray, writer: ValueWriter) {
    writer.writeInt(value.size)
    value.forEach { writer.writeRaw(FloatTypeHandler.serializeToRaw(it)) }
  }
}
