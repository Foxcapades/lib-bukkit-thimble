package io.foxcapades.mc.bukkit.thimble.codecs.bukkit

import io.foxcapades.mc.bukkit.thimble.codecs.jvm.ByteCodec
import java.io.InputStream
import java.io.OutputStream

object BukkitVersionCodec {
  fun encode(into: OutputStream, version: BukkitVersion) {
    ByteCodec.encodeBody(into, version.major.toByte())
    ByteCodec.encodeBody(into, version.minor.toByte())
    ByteCodec.encodeBody(into, version.patch.toByte())
  }

  fun decode(from: InputStream): BukkitVersion =
    BukkitVersionImpl(
      ByteCodec.create(from).toUByte(),
      ByteCodec.create(from).toUByte(),
      ByteCodec.create(from).toUByte(),
    )
}
