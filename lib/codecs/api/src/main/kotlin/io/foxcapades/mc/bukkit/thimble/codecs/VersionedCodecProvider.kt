package io.foxcapades.mc.bukkit.thimble.codecs

import io.foxcapades.mc.bukkit.thimble.types.DataType
import java.io.InputStream

interface VersionedCodecProvider<T : VersionedCodec<*, V>, V : CodecVersion> : CodecProvider<T> {
  fun getCodec(forType: DataType, forVersion: V, input: InputStream): T?
}
