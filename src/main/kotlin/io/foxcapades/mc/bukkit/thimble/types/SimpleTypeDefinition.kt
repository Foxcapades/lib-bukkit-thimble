package io.foxcapades.mc.bukkit.thimble.types

import io.foxcapades.mc.bukkit.thimble.parse.SimpleDeserializer

/**
 * Defines a type that can be represented as a scalar value.
 *
 * @param S Serialized data type.
 *
 * @param D Deserialized data type.
 */
interface SimpleTypeDefinition<S : Any, D : Any> : ThimbleTypeDefinition<D> {

  /**
   * Serializes the given value into a scalar value of type [S], or `null`.
   *
   * @param value Value to be serialized.
   *
   * @return The serialized form of the given value.
   */
  fun serialize(value: D): S?

  /**
   * Returns a `null` representation of this type.
   *
   * By default, returns a literal `null`.
   *
   * @return Value to use for values of this type when they are `null`.
   */
  fun serializeNull(): S? = null

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
  override fun deserializerFor(version: Byte): SimpleDeserializer<out D>?
}
