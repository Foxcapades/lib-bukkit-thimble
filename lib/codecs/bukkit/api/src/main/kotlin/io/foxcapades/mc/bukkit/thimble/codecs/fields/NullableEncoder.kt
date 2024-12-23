package io.foxcapades.mc.bukkit.thimble.codecs.fields

import io.foxcapades.mc.bukkit.thimble.codecs.Codec
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.NullCodec
import java.io.OutputStream
import kotlin.reflect.KFunction1

internal data class NullableEncoder<T, V>(
  override val name: String,
  private val getter: (T) -> V?,
  private val codec: (OutputStream, V) -> Unit,
) : FieldEncoder<T> {
  constructor(getter: KFunction1<T, V?>, codec: Codec<V>) : this(getter.name, getter, codec::encode)
  constructor(getter: KFunction1<T, V?>, codec: (OutputStream, V) -> Unit) : this(getter.name, getter, codec)

  override fun encode(stream: OutputStream, instance: T) {
    when (val v = getter(instance)) {
      null -> NullCodec.encode(stream)
      else -> codec(stream, v)
    }
  }
}
