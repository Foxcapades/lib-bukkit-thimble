package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.parse.SimpleDeserializer
import io.foxcapades.mc.bukkit.thimble.types.SimpleTypeHandler
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

data object BinaryTypeHandler : SimpleTypeHandler<String, ByteArray> {
  override val typeIndicator get() = "bin"

  override val currentVersion get() = 1

  override val actualType get() = ByteArray::class.java

  @OptIn(ExperimentalEncodingApi::class)
  override fun deserializerFor(version: Int) = SimpleDeserializer { Base64.decode(it.asString()) }

  @OptIn(ExperimentalEncodingApi::class)
  override fun serialize(value: ByteArray) = Base64.encode(value)
}
