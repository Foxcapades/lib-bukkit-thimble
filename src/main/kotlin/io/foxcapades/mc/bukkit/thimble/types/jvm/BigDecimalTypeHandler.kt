package io.foxcapades.mc.bukkit.thimble.types.jvm

import io.foxcapades.mc.bukkit.thimble.parse.SimpleDeserializer
import io.foxcapades.mc.bukkit.thimble.types.UnaryTypeHandler
import io.foxcapades.mc.bukkit.thimble.util.B1
import java.math.BigDecimal

/**
 * Basic [BigDecimal] (de)serialization provider.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since 1.0.0
 */
data object BigDecimalTypeHandler : UnaryTypeHandler<BigDecimal> {
  override val actualType     get() = BigDecimal::class.java
  override val typeIndicator  get() = "D"
  override val currentVersion get() = B1

  override fun deserializerFor(version: Byte): SimpleDeserializer<BigDecimal>? =
    when (version) {
      B1   -> SimpleDeserializer { it.asBigDecimal() }
      else -> null
    }
}
