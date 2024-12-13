package io.foxcapades.mc.bukkit.thimble.types.jvm

import io.foxcapades.mc.bukkit.thimble.parse.SimpleDeserializer
import io.foxcapades.mc.bukkit.thimble.types.UnaryTypeDefinition
import io.foxcapades.mc.bukkit.thimble.util.B1


/**
 * Basic [Short] (de)serialization provider.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since 1.0.0
 */
data object ShortTypeDefinition : UnaryTypeDefinition<Short> {
  override val actualType     get() = Short::class.java
  override val currentVersion get() = B1
  override val typeIdentifier  get() = "s"

  override fun deserializerFor(version: Byte): SimpleDeserializer<Short>? =
    when (version) {
      B1   -> SimpleDeserializer { it.asShort() }
      else -> null
    }
}
