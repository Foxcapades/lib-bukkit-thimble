package io.foxcapades.mc.bukkit.thimble.codecs

import io.foxcapades.mc.bukkit.thimble.types.DataType
import java.io.InputStream

interface CodecProvider<T : Codec<*>> {
  fun getCodec(input: InputStream): T? {
    return when (val v = input.read()) {
      -1   -> null
      else -> getCodec(DataType.fromTag(v.toUByte()), input)
    }
  }

  fun getCodec(forType: DataType, input: InputStream): T?
}

