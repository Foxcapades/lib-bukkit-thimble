package io.foxcapades.mc.bukkit.thimble.codecs.fields

import io.foxcapades.mc.bukkit.thimble.codecs.Codec
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.NullCodec
import io.foxcapades.mc.bukkit.thimble.io.badNullableTypeError
import io.foxcapades.mc.bukkit.thimble.io.readDataType
import io.foxcapades.mc.bukkit.thimble.DataType
import java.io.InputStream
import kotlin.reflect.KFunction2

@Deprecated("NO! just have complex decoder wrapped with nullable")
internal data class NullableComplexDecoder<T : Any, V : Any>(
  override val name: String,
  private val setter: (T, V) -> Unit,
  private val codec: Codec<V>,
) : FieldDecoder<T> {
  override val dataType get() = codec.dataType

  constructor(setter: KFunction2<T, V, Unit>, codec: Codec<V>)
    : this(setter.name, setter, codec)

  override fun decode(from: InputStream, into: T, offset: UInt) {
    when (val type = from.readDataType()) {
      DataType.Null -> NullCodec.decode(from, 1u + offset)

      codec.dataType -> {
        codec.validateAndSkipHeader(from, 1u + offset)
        setter(into, codec.create(from))
      }

      else -> throw badNullableTypeError(codec.dataType, type)
    }
  }
}

