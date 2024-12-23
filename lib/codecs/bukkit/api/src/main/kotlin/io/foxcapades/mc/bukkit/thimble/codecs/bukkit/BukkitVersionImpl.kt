package io.foxcapades.mc.bukkit.thimble.codecs.bukkit

import io.foxcapades.mc.bukkit.thimble.io.BasicIO.writeU8
import java.io.OutputStream

internal data class BukkitVersionImpl(
  val j: UByte,
  val n: UByte,
  val t: UByte,
) : BukkitVersion {
  override val major: Int
    get() = j.toInt()

  override val minor: Int
    get() = n.toInt()

  override val patch: Int
    get() = t.toInt()

  override fun writeTo(into: OutputStream) {
    into.writeU8(j)
    into.writeU8(n)
    into.writeU8(t)
  }

  override fun toString() = "$j.$n.$t"
}
