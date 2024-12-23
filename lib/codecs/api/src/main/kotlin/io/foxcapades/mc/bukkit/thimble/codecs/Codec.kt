package io.foxcapades.mc.bukkit.thimble.codecs

import io.foxcapades.mc.bukkit.thimble.ThimbleDeserializationException
import io.foxcapades.mc.bukkit.thimble.io.readDataType
import io.foxcapades.mc.bukkit.thimble.types.DataType
import java.io.InputStream
import java.io.OutputStream

sealed interface Codec<T> {
  val dataType: DataType

  val javaType: Class<out T>

  val headerLength: UInt
    get() = 1u

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
    into.write(dataType.identifier.toInt())
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
   * Decodes a value of type [T] from the given [InputStream].
   *
   * The stream is expected to be at a position where the next read would be the
   * first byte of the header value for this [Codec].
   *
   * This method will validate that the header read from the stream is correct
   * for the type represented by this codec before
   * [creating a new value instance][create], then
   * [decoding any extra fields into it][decodeBody].
   *
   * @param from Stream the encoded header and value will be read from.
   *
   * @return A new value of type [T] created from data in the given stream.
   */
  fun decode(from: InputStream): T = decode(from, 0u)

  /**
   * Decodes a value of type [T] from the given [InputStream].
   *
   * This method allows for decoding from a given offset, intended for use in
   * cases where the header has already been partially read.
   *
   * This method will validate that any remaining data for the header read from
   * the stream is correct for the type represented by this codec before
   * [creating a new value instance][create], then
   * [decoding any extra fields into it][decodeBody].
   *
   * @param from Stream the encoded header and value will be read from.
   *
   * @param offset Number of bytes already read from the value header.
   *
   * @return A new value of type [T] created from data in the given stream.
   */
  fun decode(from: InputStream, offset: UInt): T {
    validateAndSkipHeader(from, offset)

    return create(from).also { decodeBody(from, it) }
  }

  fun create(from: InputStream): T

  fun decodeBody(from: InputStream, into: T) {}

  fun validateAndSkipHeader(from: InputStream, offset: UInt): UInt {
    if (offset > headerLength)
      throw ThimbleDeserializationException("header over-read; read $offset bytes of a possible $headerLength")

    if (offset == 0u) {
      when (val dt = from.readDataType()) {
        dataType -> { /* all good here */ }
        else     -> throw ThimbleDeserializationException("expected $dataType, got $dt")
      }

      return 1u
    }

    return offset
  }
}

