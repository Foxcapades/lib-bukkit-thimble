package io.foxcapades.mc.bukkit.thimble.types

import io.foxcapades.mc.bukkit.thimble.ThimbleException
import io.foxcapades.mc.bukkit.thimble.ThimbleSerializationException
import io.foxcapades.mc.bukkit.thimble.types.bukkit.*
import io.foxcapades.mc.bukkit.thimble.types.bukkit.attribute.AttributeModifierListTypeDefinition
import io.foxcapades.mc.bukkit.thimble.types.bukkit.attribute.AttributeModifierTypeDefinition
import io.foxcapades.mc.bukkit.thimble.types.bukkit.attribute.AttributeModifiersTypeDefinition
import io.foxcapades.mc.bukkit.thimble.types.bukkit.entity.EntitySnapshotTypeDefinition
import io.foxcapades.mc.bukkit.thimble.types.bukkit.inventory.ItemStackTypeDefinition
import io.foxcapades.mc.bukkit.thimble.types.bukkit.inventory.meta.*
import io.foxcapades.mc.bukkit.thimble.types.bukkit.inventory.meta.components.*
import io.foxcapades.mc.bukkit.thimble.types.bukkit.persistence.PersistentDataContainerListTypeDefinition
import io.foxcapades.mc.bukkit.thimble.types.bukkit.persistence.PersistentDataContainerTypeDefinition
import io.foxcapades.mc.bukkit.thimble.types.bukkit.potion.PotionEffectListTypeDefinition
import io.foxcapades.mc.bukkit.thimble.types.bukkit.potion.PotionEffectTypeDefinition
import io.foxcapades.mc.bukkit.thimble.types.bukkit.profile.PlayerProfileTypeDefinition
import io.foxcapades.mc.bukkit.thimble.types.impl.*
import io.foxcapades.mc.bukkit.thimble.types.jvm.*
import java.lang.invoke.MethodType

class DefaultTypeDefinitionRegistry : TypeDefinitionRegistry {
  private val byHandle = HashMap<String, ThimbleTypeDefinition<*>>(32)
  private val byClass = HashMap<Class<*>, ThimbleTypeDefinition<*>>(19)
  private val byListClass = HashMap<Class<*>, ListTypeDefinition<*>>(12)

  override var useStrictTypeMatching: Boolean = false

  init {
    TypeDefinitionRegistry.registerJavaScalars(this)
    TypeDefinitionRegistry.registerJavaScalarArrays(this)
    TypeDefinitionRegistry.registerJavaScalarLists(this)
    TypeDefinitionRegistry.registerJavaScalarArrayLists(this)

    registerListType(UntypedListTypeDefinition())

    // BUKKIT TYPES

    // Attribute
    registerType(AttributeModifierTypeDefinition())
    registerType(AttributeModifiersTypeDefinition())
    registerListType(AttributeModifierListTypeDefinition())

    // Entity
    registerType(EntitySnapshotTypeDefinition())

    // Inventory
    registerType(ItemStackTypeDefinition())

    // Inventory Meta
    registerType(ItemMetaTypeDefinition())
    registerType(SkullMetaTypeDefinition())
    registerType(SpawnEggMetaTypeDefinition())
    registerType(SuspiciousStewMetaTypeDefinition())
    registerType(TropicalFishBucketMetaTypeDefinition())
    registerType(WritableBookMetaTypeDefinition())

    // Inventory Meta Components
    registerType(FoodComponentTypeDefinition())
    registerType(FoodEffectTypeDefinition())
    registerListType(FoodEffectListTypeDefinition())
    registerType(JukeboxPlayableComponentTypeDefinition())
    registerType(ToolComponentTypeDefinition())
    registerType(ToolRuleTypeDefinition())

    // Persistence
    registerType(PersistentDataContainerTypeDefinition())
    registerListType(PersistentDataContainerListTypeDefinition())

    // Potion
    registerType(PotionEffectTypeDefinition())
    registerListType(PotionEffectListTypeDefinition())

    // Profile
    registerType(PlayerProfileTypeDefinition())
  }

  @Throws(ThimbleException::class)
  override fun registerType(definition: ThimbleTypeDefinition<*>) {
    if (definition is ListTypeDefinition<*>)
      return registerListType(definition)

    tryPutByClass(definition.actualType, definition)
    tryPutByHandle(definition)

    if (definition.actualType.isPrimitive) {
      tryPutByClass(MethodType.methodType(definition.actualType).wrap().returnType(), definition)
    }
  }

  private fun tryPutByClass(type: Class<*>, handler: ThimbleTypeDefinition<*>) {
    if (byClass[type].takeIf { it !== handler } != null)
      throw ThimbleException(
        "attempted to register multiple serialization handlers for type" +
          " $type"
      )

    byClass[type] = handler
  }

  @Throws(ThimbleException::class)
  override fun registerListType(definition: ListTypeDefinition<*>) {
    tryPutByListClass(definition.elementType, definition)
    tryPutByHandle(definition)

    if (definition.elementType.isPrimitive)
      tryPutByListClass(MethodType.methodType(definition.elementType).wrap().returnType(), definition)
  }

  override fun containsDefinitionFor(type: Class<*>): Boolean = type in byClass

  override fun containsListHandlerFor(type: Class<*>): Boolean = type in byListClass

  override fun containsDefinitionFor(alias: String): Boolean = alias in byHandle

  override fun removeHandlerFor(type: Class<*>): Boolean = byClass.remove(type) != null

  override fun removeListHandlerFor(type: Class<*>): Boolean = byListClass.remove(type) != null

  override fun removeHandlerFor(alias: String): Boolean = byHandle.remove(alias) != null

  @Suppress("UNCHECKED_CAST")
  @Throws(ThimbleException::class)
  override fun <T : Any> typeDefinitionFor(type: Class<T>): ThimbleTypeDefinition<in T>? =
    (byClass[type] ?: (if (useStrictTypeMatching) null else scanAndCache(type))) as ThimbleTypeDefinition<in T>?

  @Suppress("UNCHECKED_CAST")
  override fun <T : Any> listTypeHandlerFor(type: Class<T>): ListTypeDefinition<T>? =
    (byListClass[type] ?: (if (useStrictTypeMatching) null else scanAndCacheList(type))) as ListTypeDefinition<T>?

  override fun typeDefinitionFor(identifier: String): ThimbleTypeDefinition<*>? = byHandle[identifier]

  @Throws(ThimbleException::class)
  override fun <T : Any> requireTypeDefinitionFor(type: Class<T>): ThimbleTypeDefinition<in T> =
    typeDefinitionFor(type)
      ?: throw ThimbleSerializationException("no type handler for type $type")

  @Throws(ThimbleException::class)
  override fun <T : Any> requireListTypeHandlerFor(type: Class<T>): ListTypeDefinition<T> =
    listTypeHandlerFor(type)
      ?: throw ThimbleSerializationException("no type handler for type $type")

  @Throws(ThimbleException::class)
  override fun requireTypeDefinitionFor(identifier: String): ThimbleTypeDefinition<*> =
    typeDefinitionFor(identifier)
      ?: throw ThimbleSerializationException("no type handler for alias $identifier")

  private fun tryPutByListClass(type: Class<*>, handler: ListTypeDefinition<*>) {
    if (byListClass[type].takeIf { it !== handler } != null)
      throw ThimbleException(
        "attempted to register multiple list serialization handlers for type" +
          " $type"
      )

    byListClass[type] = handler
  }

  private fun tryPutByHandle(handler: ThimbleTypeDefinition<*>) {
    if (byHandle[handler.typeIdentifier].takeIf { it !== handler } != null)
      throw ThimbleException(
        "attempted to register multiple serialization handlers for with the" +
          " type indicator " +
          handler.typeIdentifier
      )

    byHandle[handler.typeIdentifier] = handler
  }

  private fun <T : Any> scanAndCache(type: Class<T>): ThimbleTypeDefinition<T>? {
    @Suppress("UNCHECKED_CAST")
    return scanAndCache(type, byClass) as ThimbleTypeDefinition<T>?
  }

  private fun <T : Any> scanAndCacheList(type: Class<T>): ListTypeDefinition<T>? {
    @Suppress("UNCHECKED_CAST")
    return scanAndCache(type, byListClass) as ListTypeDefinition<T>?
  }

  @Throws(ThimbleException::class)
  private fun <T : ThimbleTypeDefinition<*> >scanAndCache(type: Class<*>, byClass: MutableMap<Class<*>, T>): T? {
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
