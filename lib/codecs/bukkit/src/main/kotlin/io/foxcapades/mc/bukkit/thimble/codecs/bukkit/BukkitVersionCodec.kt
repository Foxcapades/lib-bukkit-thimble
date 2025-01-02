package io.foxcapades.mc.bukkit.thimble.codecs.bukkit

import io.foxcapades.mc.bukkit.thimble.codecs.jvm.ByteCodec
import java.io.OutputStream
import java.nio.ByteBuffer

object BukkitVersionCodec {
  fun encode(into: OutputStream, version: BukkitVersion) {
    ByteCodec.encodeBody(into, version.major.toByte())
    ByteCodec.encodeBody(into, version.minor.toByte())
    ByteCodec.encodeBody(into, version.patch.toByte())
  }

  fun decode(from: ByteBuffer): BukkitVersion =
    BukkitVersionImpl(
      ByteCodec.create(from).toUByte(),
      ByteCodec.create(from).toUByte(),
      ByteCodec.create(from).toUByte(),
    )
}
