package io.foxcapades.mc.bukkit.thimble.types.jvm

import io.foxcapades.mc.bukkit.thimble.parse.SimpleDeserializer
import io.foxcapades.mc.bukkit.thimble.types.UnaryTypeHandler
import io.foxcapades.mc.bukkit.thimble.util.B1


/**
 * Basic [String] (de)serialization provider.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since 1.0.0
 */
data object StringTypeHandler : UnaryTypeHandler<String> {
  override val currentVersion get(): Byte = 1
  override val typeIndicator get() = "S"
  override val actualType get() = String::class.java

  override fun deserializerFor(version: Byte): SimpleDeserializer<String>? =
    when (version) {
      B1   -> SimpleDeserializer { it.asString() }
      else -> null
    }
}
