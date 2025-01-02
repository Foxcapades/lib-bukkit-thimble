package io.foxcapades.mc.bukkit.thimble.codecs.reflect

@JvmInline
value class UnresolvedGenericRef(val name: String): GenericRef {
  override fun toString() = "Unresolved Reference { name: $name }"
}
