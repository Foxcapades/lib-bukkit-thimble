package io.foxcapades.mc.bukkit.thimble.types

fun interface TypeHandlerConstructor {
  operator fun invoke(registry: TypeHandlerRegistry): TypeHandler<*>
}
