package io.foxcapades.mc.bukkit.thimble.types.jvm

import io.foxcapades.mc.bukkit.thimble.parse.SimpleDeserializer
import io.foxcapades.mc.bukkit.thimble.types.UnaryTypeHandler
import io.foxcapades.mc.bukkit.thimble.util.B1


/**
 * Basic [Byte] (de)serialization provider.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since 1.0.0
 */
data object ByteTypeHandler : UnaryTypeHandler<Byte> {
  override val actualType     get() = Byte::class.java
  override val currentVersion get() = B1
  override val typeIndicator  get() = "b"

  override fun deserializerFor(version: Byte): SimpleDeserializer<Byte>? =
    when (version) {
      B1   -> SimpleDeserializer { it.asByte() }
      else -> null
    }
}
