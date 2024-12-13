package io.foxcapades.mc.bukkit.thimble.types.jvm

import io.foxcapades.mc.bukkit.thimble.parse.SimpleDeserializer
import io.foxcapades.mc.bukkit.thimble.types.UnaryTypeDefinition
import io.foxcapades.mc.bukkit.thimble.util.B1


/**
 * Basic [Long] (de)serialization provider.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since 1.0.0
 */
data object LongTypeDefinition : UnaryTypeDefinition<Long> {
  override val actualType     get() = Long::class.java
  override val typeIdentifier  get() = "l"
  override val currentVersion get() = B1

  override fun deserializerFor(version: Byte): SimpleDeserializer<Long>? =
    when (version) {
      B1   -> SimpleDeserializer { it.asLong() }
      else -> null
    }
}
