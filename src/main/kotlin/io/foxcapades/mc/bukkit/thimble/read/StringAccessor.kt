package io.foxcapades.mc.bukkit.thimble.read

@JvmInline
internal value class StringAccessor(val value: String) : ValueAccessor {
  override val isString: Boolean
    get() = true

  override fun asString() = value

  override fun asStringOrNull() = value
}
