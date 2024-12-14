package io.foxcapades.mc.bukkit.thimble.types.jvm

import io.foxcapades.mc.bukkit.thimble.parse.SimpleDeserializer
import io.foxcapades.mc.bukkit.thimble.types.SimpleTypeDefinition
import io.foxcapades.mc.bukkit.thimble.util.B1


/**
 * Basic [Char] (de)serialization provider.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since 1.0.0
 */
open class CharTypeDefinition : SimpleTypeDefinition<String, Char> {
  override val actualType     get() = Char::class.java
  override val typeIdentifier get() = "c"
  override val currentVersion get() = B1

  override fun serialize(value: Char) = value.toString()

  override fun deserializerFor(version: Byte): SimpleDeserializer<Char>? =
    when (version) {
      B1   -> SimpleDeserializer { it.asString()[0] }
      else -> null
    }
}
