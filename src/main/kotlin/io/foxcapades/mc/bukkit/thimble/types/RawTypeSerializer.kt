package io.foxcapades.mc.bukkit.thimble.types

/**
 * Defines a special type definition trait that is used with
 * [type definitions][ThimbleTypeDefinition] to indicate that the type
 * definition wants to control exactly how the value is represented, skipping
 * the [ValueWriter][io.foxcapades.mc.bukkit.thimble.write.ValueWriter]
 * entirely.
 *
 * When a type definition has this trait, Thimble will use the [serializeToRaw]
 * instead of that type definition's standard serialize method.
 *
 * @param D Deserialized form of the value that types with this trait will
 * serialize.
 */
interface RawTypeSerializer<D : Any> {
  /**
   * Serializes the given value into a raw JSON string containing a *scalar*
   * value that will be written directly to the serialized value.
   *
   * *CAUTION*: No attempt is made by the Thimble library to verify that the
   * returned value is valid JSON.
   *
   * *WARNING*: Returning a non-scalar value from this method may result in a
   * value that cannot be deserialized.
   *
   * @param value Value to serialize.
   *
   * @return Raw JSON representation of the value.
   */
  fun serializeToRaw(value: D): String
}
