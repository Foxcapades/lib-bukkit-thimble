package io.foxcapades.mc.bukkit.thimble.types

import io.foxcapades.mc.bukkit.thimble.ThimbleException
import io.foxcapades.mc.bukkit.thimble.ThimbleSerializationException
import io.foxcapades.mc.bukkit.thimble.types.impl.*
import java.lang.invoke.MethodType

object DefaultTypeHandlerRegistry : TypeHandlerRegistry {
  private val byHandle = HashMap<String, TypeHandler<*>>(32)
  private val byClass = HashMap<Class<*>, TypeHandler<*>>(19)
  private val byListClass = HashMap<Class<*>, ListTypeHandler<*>>(12)

  override var useStrictTypeMatching: Boolean = false

  init {
    registerTypeHandler(BigDecimalTypeHandler)
    registerTypeHandler(BigIntegerTypeHandler)
    registerTypeHandler(BinaryTypeHandler)
    registerTypeHandler(BooleanTypeHandler)
    registerTypeHandler(ByteTypeHandler)
    registerTypeHandler(CharTypeHandler)
    registerTypeHandler(DoubleTypeHandler)
    registerTypeHandler(FloatTypeHandler)
    registerTypeHandler(IntegerTypeHandler)
    registerTypeHandler(LongTypeHandler)
    registerTypeHandler(ShortTypeHandler)
    registerTypeHandler(StringTypeHandler)

    registerTypeHandler(BooleanArrayTypeHandler)
    registerTypeHandler(DoubleArrayTypeHandler)
    registerTypeHandler(FloatArrayTypeHandler)
    registerTypeHandler(IntegerArrayTypeHandler)
    registerTypeHandler(LongArrayTypeHandler)
    registerTypeHandler(ShortArrayTypeHandler)
    registerTypeHandler(StringArrayTypeHandler)

    registerTypeHandler(PersistentDataContainerTypeHandler)

    registerListTypeHandler(ByteListTypeHandler)
    registerListTypeHandler(ShortListTypeHandler)
    registerListTypeHandler(IntegerListTypeHandler)
    registerListTypeHandler(LongListTypeHandler)
    registerListTypeHandler(FloatListTypeHandler)
    registerListTypeHandler(DoubleListTypeHandler)
    registerListTypeHandler(BooleanListTypeHandler)
    registerListTypeHandler(StringListTypeHandler)

    registerListTypeHandler(BinaryListTypeHandler)
    registerListTypeHandler(IntArrayListTypeHandler)
    registerListTypeHandler(LongArrayListTypeHandler)

    registerListTypeHandler(PersistentDataContainerListTypeHandler)

    registerListTypeHandler(UntypedListTypeHandler)
  }

  @Throws(ThimbleException::class)
  override fun registerTypeHandler(handler: TypeHandler<*>) {
    if (handler is ListTypeHandler<*>)
      return registerListTypeHandler(handler)

    tryPutByClass(handler.actualType, handler)
    tryPutByHandle(handler)

    if (handler.actualType.isPrimitive) {
      tryPutByClass(MethodType.methodType(handler.actualType).wrap().returnType(), handler)
    }
  }

  private fun tryPutByClass(type: Class<*>, handler: TypeHandler<*>) {
    if (byClass[type].takeIf { it !== handler } != null)
      throw ThimbleException(
        "attempted to register multiple serialization handlers for type" +
          " $type"
      )

    byClass[type] = handler
  }

  @Throws(ThimbleException::class)
  private fun registerListTypeHandler(handler: ListTypeHandler<*>) {
    tryPutByListClass(handler.elementType, handler)
    tryPutByHandle(handler)

    if (handler.elementType.isPrimitive) {
      tryPutByListClass(MethodType.methodType(handler.elementType).wrap().returnType(), handler)
    }
  }

  private fun tryPutByListClass(type: Class<*>, handler: ListTypeHandler<*>) {
    if (byListClass[type].takeIf { it !== handler } != null)
      throw ThimbleException(
        "attempted to register multiple list serialization handlers for type" +
          " $type"
      )

    byListClass[type] = handler
  }

  private fun tryPutByHandle(handler: TypeHandler<*>) {
    if (byHandle[handler.typeIndicator].takeIf { it !== handler } != null)
      throw ThimbleException(
        "attempted to register multiple serialization handlers for with the" +
          " type indicator " +
          handler.typeIndicator
      )

    byHandle[handler.typeIndicator] = handler
  }

  override fun containsHandlerFor(type: Class<*>): Boolean = type in byClass

  override fun containsListHandlerFor(type: Class<*>) = type in byListClass

  override fun containsHandlerFor(alias: String): Boolean = alias in byHandle

  override fun removeHandlerFor(type: Class<*>): Boolean = byClass.remove(type) != null

  override fun removeListHandlerFor(type: Class<*>): Boolean = byListClass.remove(type) != null

  override fun removeHandlerFor(alias: String): Boolean = byHandle.remove(alias) != null

  @Suppress("UNCHECKED_CAST")
  @Throws(ThimbleException::class)
  override fun <T : Any> typeHandlerFor(type: Class<T>): TypeHandler<T>? =
    (byClass[type] ?: (if (useStrictTypeMatching) null else scanAndCache(type))) as TypeHandler<T>?

  @Suppress("UNCHECKED_CAST")
  override fun <T : Any> listTypeHandlerFor(type: Class<T>): ListTypeHandler<T>? =
    (byListClass[type] ?: (if (useStrictTypeMatching) null else scanAndCacheList(type))) as ListTypeHandler<T>?

  override fun typeHandlerFor(alias: String): TypeHandler<*>? = byHandle[alias]

  @Throws(ThimbleException::class)
  override fun <T : Any> requireTypeHandlerFor(type: Class<T>): TypeHandler<T> =
    typeHandlerFor(type)
      ?: throw ThimbleSerializationException("no type handler for type $type")

  @Throws(ThimbleException::class)
  override fun <T : Any> requireListTypeHandlerFor(type: Class<T>): ListTypeHandler<T> =
    listTypeHandlerFor(type)
      ?: throw ThimbleSerializationException("no type handler for type $type")

  @Throws(ThimbleException::class)
  override fun requireTypeHandlerFor(alias: String): TypeHandler<*> =
    typeHandlerFor(alias)
      ?: throw ThimbleSerializationException("no type handler for alias $alias")

  private fun <T : Any> scanAndCache(type: Class<T>): TypeHandler<T>? {
    @Suppress("UNCHECKED_CAST")
    return scanAndCache(type, byClass) as TypeHandler<T>?
  }

  private fun <T : Any> scanAndCacheList(type: Class<T>): ListTypeHandler<T>? {
    @Suppress("UNCHECKED_CAST")
    return scanAndCache(type, byListClass) as ListTypeHandler<T>?
  }

  @Throws(ThimbleException::class)
  private fun <T : TypeHandler<*> >scanAndCache(type: Class<*>, byClass: MutableMap<Class<*>, T>): T? {
    val hits = HashSet<Class<*>>(4)
    for (key in byClass.keys) {
      if (key.isAssignableFrom(type))
        hits.add(key)
    }

    val hit: Class<*>? = when (hits.size) {
      1    -> hits.first()
      0    -> null
      else -> findClosestAncestor(type, hits)
    }

    return hit?.let{ byClass[it] }
      ?.also { byClass[type] = it }
  }

  @Throws(ThimbleException::class)
  private fun findClosestAncestor(target: Class<*>, hits: Set<Class<*>>): Class<*> {
    var ancestors = setOf(target)

    var depth = 1
    while (true) {
      ancestors = ancestors.parents()

      val intersect = hits.intersect(ancestors)

      when (intersect.size) {
        // If no matches were found, try to go to the next generation.
        0 -> {
          if (++depth > 10)
            throw ThimbleException("no TypeHandler found for type ${target.simpleName}")

          continue
        }

        // If we had exactly 1 hit, then use that match.
        1 -> return intersect.first()

        // Special case, skip "Object" in favor of something specific
        2 -> intersect.first { it != Any::class.java }

        else -> throw ThimbleException(
          "type handler ambiguity error: no" +
            " TypeHandler registered specifically for type $target, but" +
            " multiple matches found for ancestor types at depth $depth."
        )
      }
    }
  }

  private fun Set<Class<*>>.parents(): Set<Class<*>> =
    HashSet<Class<*>>(size * 2).also { out ->
      forEach { parent ->
        out.add((parent.superclass ?: Any::class.java) as Class<*>)
        out.addAll(parent.interfaces as Array<Class<*>>)
      }
    }
}
