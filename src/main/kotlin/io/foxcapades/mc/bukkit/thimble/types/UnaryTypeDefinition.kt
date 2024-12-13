package io.foxcapades.mc.bukkit.thimble.types

/**
 * Defines a [simple type][SimpleTypeDefinition] whose serialized and
 * deserialized forms are of the same type.
 *
 * @param T Type of both the serialized and deserialized form of the defined
 * type.
 */
interface UnaryTypeDefinition<T : Any> : SimpleTypeDefinition<T, T> {
  /**
   * Provides a default implementation of the [serialize] method that simply
   * returns the given value.
   */
  override fun serialize(value: T): T = value
}
