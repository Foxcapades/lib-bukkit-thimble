package io.foxcapades.mc.bukkit.thimble.codecs

import io.foxcapades.mc.bukkit.thimble.ThimbleDeserializationException
import io.foxcapades.mc.bukkit.thimble.io.readDataType
import io.foxcapades.mc.bukkit.thimble.DataType
import java.io.OutputStream
import java.nio.ByteBuffer

interface Codec<T> {
  val dataType: DataType

  val javaType: Class<out T>

  val headerLength: Int
    get() = 1

  /**
   * Encodes given value and a header into the given [OutputStream].
   *
   * This method is a shortcut for calling [encodeHeader] followed by
   * [encodeBody].
   *
   * @param into Stream the encoded value will be written to.
   *
   * @param value Value to encode and write.
   */
  fun encode(into: OutputStream, value: T) {
    encodeHeader(into)
    encodeBody(into, value)
  }

  /**
   * Writes a header for the data type represented by this [Codec] to the given
   * [OutputStream].
   *
   * @param into Stream the encoded header will be written to.
   */
  fun encodeHeader(into: OutputStream) {
    into.write(dataType.rawValue.toInt())
  }

  /**
   * Encodes the given value WITHOUT HEADER into the given [OutputStream].
   *
   * @param into Stream the encoded value data will be written to.
   *
   * @param value Value to encode and write.
   */
  fun encodeBody(into: OutputStream, value: T)

  /**
   * Decodes a value of type [T] from the given [ByteBuffer].
   *
   * The buffer is expected to be at a position where the next read would be the
   * first byte of the header value for this [Codec].
   *
   * This method will validate that the header read from the buffer is correct
   * for the type represented by this codec before
   * [creating a new value instance][create], then
   * [decoding any extra fields into it][decodeBody].
   *
   * @param from Stream the encoded header and value will be read from.
   *
   * @return A new value of type [T] created from data in the given stream.
   */
  fun decode(from: ByteBuffer): T {
    validateAndSkipHeader(from)

    return create(from).also { decodeBody(from, it) }
  }

  fun create(from: ByteBuffer): T

  fun decodeBody(from: ByteBuffer, into: T) {}

  /**
   * *WARNING* This method mutates the given byte buffer by incrementing its
   * [position][ByteBuffer.position] value as it is read.
   */
  fun validateAndSkipHeader(from: ByteBuffer) {
    if (from.position() > headerLength)
      throw ThimbleDeserializationException("header over-read; read ${from.position()} bytes of a possible $headerLength")

    if (from.position() == 0) {
      when (val dt = from.readDataType()) {
        dataType -> { /* all good here */ }
        else     -> throw ThimbleDeserializationException("expected $dataType, got $dt")
      }
    }
  }
}

