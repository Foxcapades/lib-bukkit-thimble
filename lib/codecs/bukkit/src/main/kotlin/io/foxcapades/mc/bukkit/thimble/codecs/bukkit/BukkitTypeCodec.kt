package io.foxcapades.mc.bukkit.thimble.codecs.bukkit

import io.foxcapades.mc.bukkit.thimble.ThimbleDeserializationException
import io.foxcapades.mc.bukkit.thimble.codecs.VersionedCodec
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldDecoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.ByteCodec
import io.foxcapades.mc.bukkit.thimble.DataType
import io.foxcapades.mc.bukkit.thimble.RecordType
import java.io.OutputStream
import java.nio.ByteBuffer

interface BukkitTypeCodec<T : Any> : VersionedCodec<T, BukkitVersion> {
  override val dataType: DataType
    get() = DataType.Record(RecordType.Bukkit)

  override val headerLength: Int
    get() = super.headerLength + 1

  override val versionCodec: (ByteBuffer) -> BukkitVersion
    get() = BukkitVersionCodec::decode

  val bukkitTypeId: BukkitTypeId

  val fieldEncoders: Iterator<FieldEncoder<T>>

  val fieldDecoders: Iterator<FieldDecoder<T>>

  override fun encodeHeader(into: OutputStream) {
    super.encodeHeader(into)
    ByteCodec.encodeBody(into, bukkitTypeId.value.toByte())
  }

  override fun encodeBody(into: OutputStream, value: T) =
    fieldEncoders.forEach { it.encode(into, value) }

  override fun decodeBody(from: ByteBuffer, into: T) =
    fieldDecoders.forEach { it.decode(from, into) }

  override fun validateAndSkipHeader(from: ByteBuffer) {
    super.validateAndSkipHeader(from)

    if (from.position() == super.headerLength) {
      when (val bt = ByteCodec.create(from).toUByte()) {
        bukkitTypeId.value -> { /* heck yes */ }
        else -> throw ThimbleDeserializationException("expected bucket type ID $bukkitTypeId, got $bt")
      }
    }
  }
}
