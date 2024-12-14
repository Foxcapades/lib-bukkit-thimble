package io.foxcapades.mc.bukkit.thimble.types.jvm

import io.foxcapades.mc.bukkit.thimble.parse.SimpleDeserializer
import io.foxcapades.mc.bukkit.thimble.types.UnaryTypeDefinition
import io.foxcapades.mc.bukkit.thimble.util.B1


/**
 * Basic [Int] (de)serialization provider.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since 1.0.0
 */
open class IntegerTypeDefinition : UnaryTypeDefinition<Int> {
  override val actualType     get() = Int::class.java
  override val typeIdentifier get() = "i"
  override val currentVersion get() = B1

  override fun deserializerFor(version: Byte): SimpleDeserializer<Int>? =
    when (version) {
      B1   -> SimpleDeserializer { it.asInt() }
      else -> null
    }
}
