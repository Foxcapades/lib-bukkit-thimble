package io.foxcapades.mc.bukkit.thimble.read

@JvmInline
internal value class BooleanAccessor(val value: Boolean) : ValueAccessor {
  override val isBoolean: Boolean
    get() = true

  override fun asBoolean() = value

  override fun asBooleanOrNull() = value
}
