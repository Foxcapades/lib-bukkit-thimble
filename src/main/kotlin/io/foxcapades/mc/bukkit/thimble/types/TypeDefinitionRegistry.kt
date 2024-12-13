package io.foxcapades.mc.bukkit.thimble.types

import io.foxcapades.mc.bukkit.thimble.ThimbleException
import io.foxcapades.mc.bukkit.thimble.types.impl.*

/**
 * Defines a registry/index of [ThimbleTypeDefinition] instances that may be
 * used in (de)serialization of values.
 */
interface TypeDefinitionRegistry {
  /**
   * Whether value types should be matched exactly to a type definition
   * instance, ignoring type definitions keyed on parent types.
   *
   * For example, given the type `Foo` which implements the interface `Bar`,
   * if a type definition is registered for the interface `Bar`, values of type
   * `Foo` will not use that type definition.
   */
  var useStrictTypeMatching: Boolean

  /**
   * Registers the given type definition if no conflicting type definition has
   * already been registered.
   *
   * @param definition Type definition to register.
   *
   * @throws ThimbleException if a conflicting type definition has already been
   * registered.  Type definitions may conflict on either
   * [ThimbleTypeDefinition.typeIdentifier] or
   * [ThimbleTypeDefinition.actualType].
   */
  @Throws(ThimbleException::class)
  fun registerType(definition: ThimbleTypeDefinition<*>)

  /**
   * Returns the registered type definition that would be used to represent the
   * given class if any matching type definition is found.
   *
   * If [useStrictTypeMatching] is `false`, the returned type definition may be
   * for a supertype of the given class.
   *
   * @param T Type of the class
   *
   * @param type Class for which the type definition should be returned.
   *
   * @return Either the matching type definition, or `null` if none could be
   * found.
   */
  fun <T : Any> typeDefinitionFor(type: Class<T>): ThimbleTypeDefinition<in T>?

  /**
   * Returns the registered type definition for the given identifier, if such
   * a definition has been registered.
   *
   * @param identifier Unique identifier for the type definition to look up.
   *
   * @return Either the matching type definition, or `null` if none are
   * registered with the given identifier.
   */
  fun typeDefinitionFor(identifier: String): ThimbleTypeDefinition<*>?

  /**
   * Returns the registered type definition that would be used to represent the
   * given class if any matching type definition is found.
   *
   * If [useStrictTypeMatching] is `false`, the returned type definition may be
   * for a supertype of the given class.
   *
   * @param T Type of the class
   *
   * @param type Class for which the type definition should be returned.
   *
   * @return The matching type definition.
   *
   * @throws ThimbleException if no matching type definition could be found.
   */
  @Throws(ThimbleException::class)
  fun <T : Any> requireTypeDefinitionFor(type: Class<T>): ThimbleTypeDefinition<in T>

  /**
   * Returns the registered type definition for the given identifier, if such
   * a definition has been registered.
   *
   * @param identifier Unique identifier for the type definition to look up.
   *
   * @return The matching type definition.
   *
   * @throws ThimbleException if no type definition is registered with the given
   * identifier.
   */
  @Throws(ThimbleException::class)
  fun requireTypeDefinitionFor(identifier: String): ThimbleTypeDefinition<*>

  /**
   * Tests whether a type definition is registered that would be used when
   * (de)serializing values of the given class type.
   *
   * If [useStrictTypeMatching] is `false`, a matching type definition may be
   * for a supertype of the given class.
   *
   * *WARNING*: This method DOES NOT match [ListTypeDefinition] instances whose
   * [elementType][ListTypeDefinition.elementType] field is compatible with the
   * given type.  Use [containsListHandlerFor] to test for those type
   * definitions.
   *
   * @param type Class for which the existence of a type definition should be
   * tested.
   *
   * @return `true` if a type definition is registered that could be used for
   * values of the given class type, otherwise `false`.
   *
   * @see containsListHandlerFor
   */
  fun containsDefinitionFor(type: Class<*>): Boolean

  fun containsListHandlerFor(type: Class<*>): Boolean

  fun containsDefinitionFor(alias: String): Boolean

  fun removeHandlerFor(type: Class<*>): Boolean

  fun removeListHandlerFor(type: Class<*>): Boolean

  fun removeHandlerFor(alias: String): Boolean

  fun <T : Any> listTypeHandlerFor(type: Class<T>): ListTypeDefinition<T>?

  @Throws(ThimbleException::class)
  fun <T : Any> requireListTypeHandlerFor(type: Class<T>): ListTypeDefinition<T>
}

