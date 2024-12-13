package io.foxcapades.mc.bukkit.thimble.types

import io.foxcapades.mc.bukkit.thimble.ThimbleException
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

/**
 * Defines a list of values of a given type.
 *
 * @param T Type of data contained by the deserialized form of the [List] type
 * this definition represents.
 */
interface ListTypeDefinition<T : Any> : ThimbleTypeDefinition<List<*>> {
  override val actualType: Class<List<*>>
    get() = List::class.java

  /**
   * Class for the type of value contained in the defined [List].
   */
  val elementType: Class<T>

  /**
   * Serializes the given list value into the given [writer].
   *
   * Implementers should call the appropriate writer method for each value that
   * should be represented in the serialized output.
   *
   * Values will be passed to the deserializer in the order they are written on
   * deserialization.
   *
   * Example (Kotlin):
   * ```kotlin
   * override fun serialize(values: List<String>, writer: ValueWriter) {
   *   for (str in values)
   *     writer.writeString(str)
   * }
   * ```
   *
   * Example (Java):
   * ```java
   * public void serialize(List<String> values, ValueWriter writer) {
   *   for (var str : values)
   *     writer.writeString(str);
   * }
   * ```
   *
   * To avoid unnecessary reallocations for large lists, consider writing the
   * size of the list as the first value.
   *
   * Example (Kotlin):
   * ```kotlin
   * override fun serialize(values: List<String>, writer: ValueWriter) {
   *   writer.writeInt(values.size)
   *   for (str in values)
   *     writer.writeString(str)
   * }
   * ```
   *
   * Then when deserializing, the value at index `0` can be deserialized as an
   * `int` and used to construct the output list instance.
   *
   * @param values List to serialize.
   *
   * @param writer Data stream writer.
   *
   * @throws ThimbleException Implementations may choose to throw exceptions for
   * value serialization errors.
   */
  @Throws(ThimbleException::class)
  fun serialize(values: List<T>, writer: ValueWriter)

  /**
   * Writes a `null` representation of this type to the given [writer].
   *
   * By default, writes a JSON literal `null` to the writer.
   *
   * @param writer Data stream writer.
   */
  fun serializeNull(writer: ValueWriter) = writer.writeNull()
}
