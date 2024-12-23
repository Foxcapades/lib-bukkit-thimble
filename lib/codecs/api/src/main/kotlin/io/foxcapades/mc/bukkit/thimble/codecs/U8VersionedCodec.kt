package io.foxcapades.mc.bukkit.thimble.codecs

import java.io.InputStream

interface U8VersionedCodec<T : Any> : VersionedCodec<T, U8CodecVersion> {
  override val versionCodec: (InputStream) -> U8CodecVersion
    get() = U8CodecVersion.Companion::readFrom
}
