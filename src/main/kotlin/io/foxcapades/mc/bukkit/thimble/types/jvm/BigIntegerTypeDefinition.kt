package io.foxcapades.mc.bukkit.thimble.types.jvm

import io.foxcapades.mc.bukkit.thimble.parse.SimpleDeserializer
import io.foxcapades.mc.bukkit.thimble.types.UnaryTypeDefinition
import io.foxcapades.mc.bukkit.thimble.util.B1
import java.math.BigInteger

/**
 * Basic [BigInteger] (de)serialization provider.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since 1.0.0
 */
open class BigIntegerTypeDefinition : UnaryTypeDefinition<BigInteger> {
  override val actualType     get() = BigInteger::class.java
  override val typeIdentifier get() = "I"
  override val currentVersion get() = B1

  override fun deserializerFor(version: Byte): SimpleDeserializer<BigInteger>? =
    when (version) {
      B1   -> SimpleDeserializer { it.asBigInteger() }
      else -> null
    }
}
