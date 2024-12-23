package io.foxcapades.mc.bukkit.thimble.codecs.bukkit

import io.foxcapades.mc.bukkit.thimble.ThimbleDeserializationException
import io.foxcapades.mc.bukkit.thimble.codecs.VersionedCodec
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldDecoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldEncoder
import io.foxcapades.mc.bukkit.thimble.io.readU8
import io.foxcapades.mc.bukkit.thimble.io.writeU8
import io.foxcapades.mc.bukkit.thimble.types.DataType
import java.io.InputStream
import java.io.OutputStream

interface BukkitTypeCodec<T : Any> : VersionedCodec<T, BukkitVersion> {
  override val dataType: DataType
    get() = DataType.Bukkit

  override val versionCodec: (InputStream) -> BukkitVersion
    get() = BukkitVersion.Companion::readFrom

  override val headerLength: UInt
    get() = super.headerLength + 1u

  val bukkitTypeId: BukkitTypeId

  val fieldEncoders: Iterator<FieldEncoder<T>>

  val fieldDecoders: Iterator<FieldDecoder<T>>

  override fun encodeHeader(into: OutputStream) {
    super.encodeHeader(into)
    into.writeU8(bukkitTypeId.value)
  }

  override fun encodeBody(into: OutputStream, value: T) =
    fieldEncoders.forEach { it.encode(into, value) }

  override fun decodeBody(from: InputStream, into: T) =
    fieldDecoders.forEach { it.decode(from, into) }

  override fun validateAndSkipHeader(from: InputStream, offset: UInt): UInt {
    val off = super.validateAndSkipHeader(from, offset)

    if (off == super.headerLength) {
      when (val bt = from.readU8()) {
        bukkitTypeId.value -> { /* heck yes */ }
        else -> throw ThimbleDeserializationException("expected bucket type ID $bukkitTypeId, got $bt")
      }

      return 1u + super.headerLength
    }

    return off
  }

}
