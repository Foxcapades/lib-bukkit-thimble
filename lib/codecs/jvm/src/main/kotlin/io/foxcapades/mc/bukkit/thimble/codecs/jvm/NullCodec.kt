package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.codecs.UnversionedCodec
import io.foxcapades.mc.bukkit.thimble.DataType
import io.foxcapades.mc.bukkit.thimble.ScalarType
import java.io.OutputStream
import java.nio.ByteBuffer

object NullCodec : UnversionedCodec<Any?> {
  override val dataType: DataType
    get() = DataType.Scalar(ScalarType.Null)

  override val javaType: Class<Any>
    get() = Any::class.java

  fun encode(into: OutputStream) =
    encode(into, null)

  override fun encodeBody(into: OutputStream, value: Any?) {}

  override fun create(from: ByteBuffer): Any? = null
}
