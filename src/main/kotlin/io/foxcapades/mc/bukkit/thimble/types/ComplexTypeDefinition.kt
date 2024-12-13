package io.foxcapades.mc.bukkit.thimble.types

import io.foxcapades.mc.bukkit.thimble.ThimbleException
import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

/**
 * Type definition for a type that cannot or should not be represented as a
 * scalar value.
 *
 * @param T Type of the value being defined.
 */
interface ComplexTypeDefinition<T : Any> : ThimbleTypeDefinition<T> {
  /**
   * Serializes the given [value] into the given [writer].
   *
   * Implementers should call the appropriate writer method for each value that
   * should be represented in the serialized output.
   *
   * The values will be passed to the deserializer in the order they are written
   * to the given [writer].
   *
   * Example (Kotlin):
   * ```kotlin
   * override fun serialize(value: MyType, writer: ValueWriter) {
   *   writer.writeInt(value.id)
   *   writer.writeString(value.name)
   * }
   * ```
   *
   * Example (Java):
   * ```java
   * public void serialize(MyType values, ValueWriter writer) {
   *   writer.writeInt(value.getId());
   *   writer.writeString(value.getName());
   * }
   * ```
   *
   * For POJO types field names are typically not written as the ordering of the
   * values can be used to determine what field a value is for.
   *
   * For [Map] types, writing the keys may be required:
   * ```kotlin
   * for (entry in value.entries) {
   *   writer.writeString(entry.key)
   *   writer.writeComplex(entry.value)
   * }
   * ```
   *
   * @param value Value to serialize.
   *
   * @param writer Data stream writer.
   *
   * @throws ThimbleException Implementations may choose to throw exceptions for
   * value serialization errors.
   */
  @Throws(ThimbleException::class)
  fun serialize(value: T, writer: ValueWriter)

  /**
   * Writes a `null` representation of this type to the given [writer].
   *
   * By default, writes a JSON literal `null` to the writer.
   *
   * @param writer Data stream writer.
   */
  fun serializeNull(writer: ValueWriter) = writer.writeNull()

  /**
   * Returns a value deserializer instance for serialized values of the given
   * [version].
   *
   * If no deserializer is available for a given version, implementers should
   * return `null`.
   *
   * @param version Version of the data to be deserialized.
   *
   * @return Either a new deserializer instance if the given version is
   * supported, or `null` of the version is not supported.
   */
  override fun deserializerFor(version: Byte): ComplexDeserializer<out T>?
}
