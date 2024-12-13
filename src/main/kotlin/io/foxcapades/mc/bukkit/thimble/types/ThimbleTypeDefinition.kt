package io.foxcapades.mc.bukkit.thimble.types

import io.foxcapades.mc.bukkit.thimble.parse.ThimbleDeserializer

/**
 * Defines a type that the Thimble library can (de)serialize.
 *
 * This type should not be implemented directly, instead one of the
 * sub-interfaces should be implemented.
 *
 * @param D The type of the deserialized form of the value.
 *
 * @see ComplexTypeDefinition
 * @see ListTypeDefinition
 * @see SimpleTypeDefinition
 */
sealed interface ThimbleTypeDefinition<D : Any> {
  /**
   * Unique identifier string that is used by Thimble to tag values being
   * written where necessary.
   */
  val typeIdentifier: String

  /**
   * Current version of this type definition.
   *
   * Serialized data is versioned to allow type definitions to update in a clean
   * manner and still support data that has been serialized in a different
   * format.
   */
  val currentVersion: Byte

  /**
   * Class value for the deserialized form of the defined type.
   */
  val actualType: Class<out D>

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
  fun deserializerFor(version: Byte): ThimbleDeserializer<out D>?
}

