package io.foxcapades.mc.bukkit.thimble.reflect

internal fun <T> Class<T>.crawl(lookingFor: Set<Class<*>>): Set<Class<in T>> =
  HashSet<Class<in T>>(4).also {
    @Suppress("UNCHECKED_CAST")
    crawl(lookingFor, it as MutableSet<Class<*>>)
  }

private fun Class<*>.crawl(lookingFor: Set<Class<*>>, into: MutableSet<Class<*>>) {
  if (lookingFor.size == into.size)
    return

  when (this) {
    Any::class.java -> {}
    in lookingFor   -> into.add(this)
    else            -> {
      interfaces.forEach { it.crawl(lookingFor, into) }
      superclass?.crawl(lookingFor, into)
    }
  }
}
