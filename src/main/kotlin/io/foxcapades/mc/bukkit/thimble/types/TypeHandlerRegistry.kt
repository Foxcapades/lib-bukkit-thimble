package io.foxcapades.mc.bukkit.thimble.types

import io.foxcapades.mc.bukkit.thimble.ThimbleException
import io.foxcapades.mc.bukkit.thimble.types.impl.*

interface TypeHandlerRegistry {
  var useStrictTypeMatching: Boolean

  @Throws(ThimbleException::class)
  fun registerTypeHandler(handler: TypeHandler<*>)

  fun <T : Any> typeHandlerFor(type: Class<T>): TypeHandler<T>?

  fun typeHandlerFor(alias: String): TypeHandler<*>?

  @Throws(ThimbleException::class)
  fun <T : Any> requireTypeHandlerFor(type: Class<T>): TypeHandler<T>

  @Throws(ThimbleException::class)
  fun requireTypeHandlerFor(alias: String): TypeHandler<*>

  fun containsHandlerFor(type: Class<*>): Boolean

  fun containsListHandlerFor(type: Class<*>): Boolean

  fun containsHandlerFor(alias: String): Boolean

  fun removeHandlerFor(type: Class<*>): Boolean

  fun removeListHandlerFor(type: Class<*>): Boolean

  fun removeHandlerFor(alias: String): Boolean

  fun <T : Any> listTypeHandlerFor(type: Class<T>): ListTypeHandler<T>?

  @Throws(ThimbleException::class)
  fun <T : Any> requireListTypeHandlerFor(type: Class<T>): ListTypeHandler<T>
}

