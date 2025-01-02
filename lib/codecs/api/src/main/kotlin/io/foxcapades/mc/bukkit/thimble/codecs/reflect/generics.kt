@file:JvmName("ComplexTypes")
package io.foxcapades.mc.bukkit.thimble.codecs.reflect

import io.foxcapades.mc.bukkit.thimble.ThimbleException


fun GenericRef.getType(): Class<*>? =
  when (this) {
    is ComplexGenericRef
      -> this.type
    is SimpleGenericRef
      -> this.type
    is UnresolvedGenericRef
      -> null
  }


////

fun Class<*>.resolveRef(): ComplexGenericRef =
  resolveTypeName(toGenericString()).toComplex()


inline fun <reified T: Any> Class<*>.resolveTo(): ComplexGenericRef =
  resolveTo(T::class.java)

@Suppress("UNCHECKED_CAST")
inline fun <reified T: Any> Class<*>.withResolved(fn: ComplexGenericRef.(Class<out T>) -> Any?): Any? =
  resolveTo(T::class.java).fn(this as Class<out T>)

fun Class<*>.resolveTo(type: Class<*>): ComplexGenericRef =
  when {
    type.isInterface
      -> resolveToInterface(type, resolveTypeName(toGenericString()).toComplex())
    type.isPrimitive || type.isEnum
      -> resolveSimple(type, resolveTypeName(toGenericString()))
    else
      -> resolveToSuperclass(type, resolveTypeName(toGenericString()).toComplex())
  }


////


fun ComplexType<*>.resolve(): ComplexGenericRef =
  getRoot().generics[0].toComplex()


fun ComplexType<*>.resolveTo(type: Class<*>): ComplexGenericRef {
  return if (type.isInterface)
    resolveToInterface(type, getRoot().generics[0].toComplex())
  else if (type.isPrimitive || type.isEnum)
    resolveSimple(type, getRoot().generics[0])
  else
    resolveToSuperclass(type, getRoot().generics[0].toComplex())
}


////


inline fun <reified T: Any> ComplexGenericRef.resolveTo(): ComplexGenericRef =
  resolveTo(T::class.java)

@Suppress("UNCHECKED_CAST")
inline fun <reified T: Any> ComplexGenericRef.withResolved(fn: ComplexGenericRef.(Class<out T>) -> Any?): Any? =
  resolveTo(T::class.java).fn(this.type as Class<out T>)

fun ComplexGenericRef.resolveTo(type: Class<*>): ComplexGenericRef {
  return if (type.isInterface)
    resolveToInterface(type, this)
  else if (type.isPrimitive || type.isEnum)
    resolveSimple(type, this)
  else
    resolveToSuperclass(type, this)
}


////


// get the ComplexType ref with any generics applied from subclasses?
private fun ComplexType<*>.getRoot(): ComplexGenericRef {
  var res = resolveTypeName(this::class.java.genericSuperclass.typeName).toComplex()

  while (res.type != ComplexType::class.java) {
    res = res.collapseWith(resolveTypeName(res.type.genericSuperclass.typeName).toComplex())

    if (res.type == Any::class.java) {
      throw IllegalStateException("somehow missed complex type")
    }
  }

  return res
}


private fun resolveToInterface(type: Class<*>, resolvedRoot: ComplexGenericRef): ComplexGenericRef {
  var softHit: ComplexGenericRef? = null

  val queue = ArrayDeque<GenericRef>(12)
  queue.add(resolvedRoot)

  while (queue.isNotEmpty()) {
    val curComplex = when (val current = queue.first()) {
      is ComplexGenericRef -> current
      is SimpleGenericRef  -> current.toComplex()
      else                 -> continue
    }

    if (!type.isAssignableFrom(curComplex.type))
      continue

    if (curComplex.type == type) {

      if (!curComplex.hasUnresolved())
        return curComplex
      else
        softHit = curComplex
    }

    if (!curComplex.atClassTreeRoot())
      queue.add(curComplex.collapseWith(
        resolveTypeName(curComplex.type.genericSuperclass.typeName)
          .toComplex()))

    curComplex.type.genericInterfaces.forEach { queue.add(resolveTypeName(it.typeName)) }
  }

  if (softHit != null) {
    val count = softHit.generics.count { it is UnresolvedGenericRef }

    throw IllegalArgumentException("type ${resolvedRoot.type} could not be"
    + " fully resolved as a value of type $type, $count generic parameters"
    + " were not filled.")
  }

  throw IllegalArgumentException("type ${resolvedRoot.type} could not be"
  + " resolved as a value of type $type")
}

private fun resolveToSuperclass(type: Class<*>, root: ComplexGenericRef): ComplexGenericRef {
  var current = root

  while (type.isAssignableFrom(current.type)) {
    if (current.type == type) {
      if (current.hasUnresolved()) {
        val count = current.generics.count { it is UnresolvedGenericRef }

        throw IllegalArgumentException("type ${current.type} could not be"
        + " fully resolved as a value of type $type, $count generic"
        + " parameters were not filled.")
      }

      return current
    }

    if (current.atClassTreeRoot())
      break;

    current = current.collapseWith(resolveTypeName(current.type.genericSuperclass.typeName).toComplex())
  }

  throw IllegalArgumentException("type ${root.type} could not be"
  + " resolved as a value of type $type")
}


private fun resolveSimple(type: Class<*>, root: GenericRef): ComplexGenericRef {
  if (root is SimpleGenericRef && type.isAssignableFrom(root.type))
    return root.toComplex()

  throw ThimbleException("cannot resolve type $root as $type")
}


////


private fun GenericRef.toComplex(): ComplexGenericRef {
  return when (this) {
    is ComplexGenericRef -> this
    is SimpleGenericRef -> ComplexGenericRef(type, emptyList())
    else -> throw IllegalArgumentException("genericRef must not be unresolved")
  }
}

@Suppress("NOTHING_TO_INLINE")
private inline fun ComplexGenericRef.atClassTreeRoot() =
  type.superclass == Any::class.java || type.superclass == null

////

val typePattern = Regex("([\\w$.]+<.+?>|[\\w$.]+)(?:,|$)")

private fun resolveTypeName(generic: String) =
  when (val pos = generic.indexOf('<')) {
    -1   -> tryResolveSimple(generic)
    else -> resolveComplex(generic, pos)
  }

private fun resolveComplex(generics: String, bracePos: Int): GenericRef {
  return ComplexGenericRef(
    Class.forName(generics.substring(0, bracePos)),
    try {
      typePattern.findAll(generics.substring(bracePos+1, generics.lastIndexOf('>')))
        .map { resolveTypeName(it.groupValues[1]) }
        .toList()
    } catch (e: NullPointerException) {
      e.printStackTrace()
      emptyList()
    }
  )
}

private fun tryResolveSimple(name: String): GenericRef =
  try {
    SimpleGenericRef(Class.forName(name))
  } catch (e: ClassNotFoundException) {
    UnresolvedGenericRef(name)
  }
