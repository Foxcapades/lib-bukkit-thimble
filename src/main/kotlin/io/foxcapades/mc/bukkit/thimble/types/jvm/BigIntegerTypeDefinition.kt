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
data object BigIntegerTypeDefinition : UnaryTypeDefinition<BigInteger> {
  override val currentVersion get(): Byte = 1

  override val typeIdentifier get() = "I"

  override val actualType get() = BigInteger::class.java

  override fun deserializerFor(version: Byte): SimpleDeserializer<BigInteger>? =
    when (version) {
      B1   -> SimpleDeserializer { it.asBigInteger() }
      else -> null
    }
}
