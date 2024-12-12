package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.parse.SimpleDeserializer
import io.foxcapades.mc.bukkit.thimble.types.UnaryTypeHandler
import java.math.BigDecimal

data object BigDecimalTypeHandler : UnaryTypeHandler<BigDecimal> {
  override val currentVersion get() = 1

  override val actualType get() = BigDecimal::class.java

  override val typeIndicator get() = "D"

  override fun deserializerFor(version: Int) = SimpleDeserializer { it.asBigDecimal() }
}
