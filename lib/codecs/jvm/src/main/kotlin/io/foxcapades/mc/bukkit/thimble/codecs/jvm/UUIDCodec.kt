package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.codecs.UnversionedCodec
import io.foxcapades.mc.bukkit.thimble.DataType
import io.foxcapades.mc.bukkit.thimble.RecordType
import io.foxcapades.mc.bukkit.thimble.io.mustGetLong
import io.foxcapades.mc.bukkit.thimble.utils.writeLong
import java.io.OutputStream
import java.nio.ByteBuffer
import java.util.UUID

object UUIDCodec : UnversionedCodec<UUID> {
  override val javaType: Class<out UUID>
    get() = UUID::class.java

  override val dataType: DataType
    get() = DataType.Record(RecordType.Sequence)

  override fun create(from: ByteBuffer): UUID {
    return UUID(from.mustGetLong(), from.mustGetLong())
  }

  override fun encodeBody(into: OutputStream, value: UUID) {
    into.writeLong(value.mostSignificantBits)
    into.writeLong(value.leastSignificantBits)
  }
}
