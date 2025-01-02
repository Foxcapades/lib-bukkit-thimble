package io.foxcapades.mc.bukkit.thimble.codecs.reflect

class ComplexGenericRef(val type: Class<*>, val generics: List<GenericRef>): GenericRef {
  fun hasUnresolved(): Boolean =
    generics.any { it is UnresolvedGenericRef }

  internal fun collapseWith(other: ComplexGenericRef): ComplexGenericRef {
    val aKeys = type.typeParameters.let { p -> Array<String>(generics.size) { p[it].name } }

    val mod = other.generics.toMutableList()

    for (ref in other.generics) {
      if (ref is UnresolvedGenericRef) {
        when (val i = aKeys.indexOf(ref.name)) {
          -1   -> throw IllegalStateException()
          else -> mod[i] = generics[i]
        }
      }
    }

    return ComplexGenericRef(other.type, mod)
  }

  override fun toString() =
    "Complex Reference { type: $type, generics: ${generics.joinToString(", ", "[", "]") { it.toString() }} }"
}
