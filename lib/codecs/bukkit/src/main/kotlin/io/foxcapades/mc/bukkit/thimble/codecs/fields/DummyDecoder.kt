package io.foxcapades.mc.bukkit.thimble.codecs.fields

import io.foxcapades.mc.bukkit.thimble.codecs.UnversionedCodec
import io.foxcapades.mc.bukkit.thimble.DataType
import java.io.InputStream
import java.io.OutputStream
import java.nio.ByteBuffer

internal class DummyDecoder<V>(
  override val dataType: DataType,
  val decoder: (InputStream) -> V,
): UnversionedCodec<V> {
  override val javaType: Class<out V>
    get() = throw UnsupportedOperationException()

  override fun create(from: ByteBuffer): V {
    throw UnsupportedOperationException()
  }

  override fun decode(from: InputStream, offset: UInt): V {
    return decoder(from)
  }

  override fun encodeBody(into: OutputStream, value: V) {
    throw UnsupportedOperationException()
  }
}
