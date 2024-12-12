package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.parse.SimpleDeserializer
import io.foxcapades.mc.bukkit.thimble.types.UnaryTypeHandler
import java.math.BigInteger

data object BigIntegerTypeHandler : UnaryTypeHandler<BigInteger> {
  override val currentVersion get() = 1

  override val typeIndicator get() = "I"

  override val actualType get() = BigInteger::class.java

  override fun deserializerFor(version: Int) = SimpleDeserializer { it.asBigInteger() }
}
