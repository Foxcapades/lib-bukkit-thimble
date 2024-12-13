package io.foxcapades.mc.bukkit.thimble.types.jvm

import io.foxcapades.mc.bukkit.thimble.parse.SimpleDeserializer
import io.foxcapades.mc.bukkit.thimble.types.UnaryTypeHandler
import io.foxcapades.mc.bukkit.thimble.util.B1


/**
 * Basic [Int] (de)serialization provider.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since 1.0.0
 */
data object IntegerTypeHandler : UnaryTypeHandler<Int> {
  override val actualType     get() = Int::class.java
  override val typeIndicator  get() = "i"
  override val currentVersion get() = B1

  override fun deserializerFor(version: Byte): SimpleDeserializer<Int>? =
    when (version) {
      B1   -> SimpleDeserializer { it.asInt() }
      else -> null
    }
}
