package io.foxcapades.mc.bukkit.thimble.read

@JvmInline
internal value class NullAccessor(private val value: Byte = 0) : ValueAccessor {
  override val isNull: Boolean
    get() = true

  override fun <T> asNull(): T? = null
}
