package io.foxcapades.mc.bukkit.thimble.unsafe

import net.minecraft.core.HolderSet
import net.minecraft.core.IRegistry
import net.minecraft.resources.ResourceKey
import org.bukkit.Keyed


internal object CraftHolderUtil {
  private val parseFn = CraftHolderUtilClass
    .getDeclaredMethod("parse", Any::class.java, ResourceKey::class.java, IRegistry::class.java)

  @Suppress("UNCHECKED_CAST")
  fun <T> parse(target: Iterable<Keyed>, registryKey: ResourceKey<IRegistry<T>>, registry: IRegistry<T>): HolderSet<T> {
    return parseFn.invoke(target.map { it.key }, registryKey, registry) as HolderSet<T>
  }
}
