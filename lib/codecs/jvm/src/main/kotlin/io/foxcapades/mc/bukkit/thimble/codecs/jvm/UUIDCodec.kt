package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.codecs.UnversionedCodec
import io.foxcapades.mc.bukkit.thimble.io.readI64
import io.foxcapades.mc.bukkit.thimble.io.writeI64
import io.foxcapades.mc.bukkit.thimble.types.DataType
import java.io.InputStream
import java.io.OutputStream
import java.util.UUID

object UUIDCodec : UnversionedCodec<UUID> {
  override val javaType: Class<out UUID>
    get() = UUID::class.java

  override val dataType: DataType
    get() = DataType.Sequence

  override fun create(from: InputStream): UUID {
    return UUID(from.readI64(), from.readI64())
  }

  override fun encodeBody(into: OutputStream, value: UUID) {
    into.writeI64(value.mostSignificantBits)
    into.writeI64(value.leastSignificantBits)
  }
}
