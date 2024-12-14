package io.foxcapades.mc.bukkit.thimble.types.jvm

import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.ComplexTypeDefinition
import io.foxcapades.mc.bukkit.thimble.util.B1
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter


/**
 * Basic [Array]<[String]> (de)serialization provider.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since 1.0.0
 */
open class StringArrayTypeDefinition : ComplexTypeDefinition<Array<String>> {
  override val actualType     get() = Array<String>::class.java
  override val typeIdentifier get() = "[S]"
  override val currentVersion get() = B1

  override fun deserializerFor(version: Byte): ComplexDeserializer<Array<String>>? =
    when (version) {
      B1 -> object : ComplexDeserializer<Array<String>> {
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

      else -> null
    }

  override fun serialize(value: Array<String>, writer: ValueWriter) {
    writer.writeInt(value.size)
    value.forEach { writer.writeString(it) }
  }
}
