package io.foxcapades.mc.bukkit.thimble.types.jvm

import io.foxcapades.mc.bukkit.thimble.parse.SimpleDeserializer
import io.foxcapades.mc.bukkit.thimble.types.UnaryTypeHandler
import io.foxcapades.mc.bukkit.thimble.util.B1


/**
 * Basic [Boolean] (de)serialization provider.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since 1.0.0
 */
data object BooleanTypeHandler : UnaryTypeHandler<Boolean> {
  override val actualType     get() = Boolean::class.java
  override val currentVersion get() = B1
  override val typeIndicator  get() = "B"

  override fun deserializerFor(version: Byte): SimpleDeserializer<Boolean>? =
    when (version) {
      B1   -> SimpleDeserializer { it.asBoolean() }
      else -> null
    }
}
