package io.foxcapades.mc.bukkit.thimble.codecs

import io.foxcapades.mc.bukkit.thimble.DataKind
import io.foxcapades.mc.bukkit.thimble.DataType
import io.foxcapades.mc.bukkit.thimble.ThimbleDeserializationException
import io.foxcapades.mc.bukkit.thimble.io.readDataType
import java.io.OutputStream
import java.nio.ByteBuffer

/**
 * "Magic" codec used for untyped collections and maps.
 *
 * Each individual value written by this codec will be preceded by a full type
 * header.
 */
class MagicCodec(private val provider: CodecProvider) : Codec<Any?> {
  override val dataType: DataType
    get() = DataType.Unknown

  override val javaType: Class<out Any?>
    get() = Any::class.java

  override fun create(from: ByteBuffer): Any? {
    val dt = from.readDataType()

    val codec = when (dt.kind) {
      DataKind.Scalar, DataKind.Record -> provider.requireCodec(dt)
      else -> throw ThimbleDeserializationException("cannot decode a value of type $dt")
    }

    return codec.decode(from)
  }

  override fun encodeBody(into: OutputStream, value: Any?) {
    if (value == null) {
      provider.requireCodec(Unit::class.java).encode(into, Unit)
    } else {
      @Suppress("UNCHECKED_CAST")
      (provider.requireCodec(value::class.java) as Codec<Any>).encode(into, value)
    }
  }
}
