package io.foxcapades.mc.bukkit.thimble.types.jvm

import io.foxcapades.mc.bukkit.thimble.parse.SimpleDeserializer
import io.foxcapades.mc.bukkit.thimble.types.SimpleTypeDefinition
import io.foxcapades.mc.bukkit.thimble.util.B1

import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi


/**
 * Basic [ByteArray] (de)serialization provider.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since 1.0.0
 */
open class BinaryTypeDefinition : SimpleTypeDefinition<String, ByteArray> {
  override val actualType     get() = ByteArray::class.java
  override val typeIdentifier get() = "bin"
  override val currentVersion get() = B1

  @OptIn(ExperimentalEncodingApi::class)
  override fun serialize(value: ByteArray) = Base64.encode(value)

  @OptIn(ExperimentalEncodingApi::class)
  override fun deserializerFor(version: Byte): SimpleDeserializer<ByteArray>? =
    when (version) {
      B1   -> SimpleDeserializer { Base64.decode(it.asString()) }
      else -> null
    }
}
