package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.codecs.UnversionedCodec
import io.foxcapades.mc.bukkit.thimble.types.DataType
import java.io.InputStream
import java.io.OutputStream

object NullCodec : UnversionedCodec<Any?> {
  override val dataType get() = DataType.Null
  override val javaType get() = Any::class.java

  fun encode(into: OutputStream) = encode(into, null)

  override fun encodeBody(into: OutputStream, value: Any?) {}
  override fun create(from: InputStream): Any? = null
}
