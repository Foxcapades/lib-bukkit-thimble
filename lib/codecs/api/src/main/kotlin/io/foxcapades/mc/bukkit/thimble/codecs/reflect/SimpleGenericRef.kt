package io.foxcapades.mc.bukkit.thimble.codecs.reflect

@JvmInline
value class SimpleGenericRef(val type: Class<*>): GenericRef {
  override fun toString() = "Simple Reference { type: $type }"
}
