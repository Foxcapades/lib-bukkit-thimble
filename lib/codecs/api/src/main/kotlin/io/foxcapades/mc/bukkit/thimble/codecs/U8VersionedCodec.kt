package io.foxcapades.mc.bukkit.thimble.codecs

import java.nio.ByteBuffer

interface U8VersionedCodec<T> : VersionedCodec<T, U8CodecVersion> {
  override val versionCodec: (ByteBuffer) -> U8CodecVersion
    get() = U8CodecVersion.Companion::readFrom
}
