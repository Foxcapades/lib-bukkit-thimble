package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.codecs.Codec

class SetCodecV1<T>(override val valueCodec: Codec<T>) : SequenceCodecV1<T, Set<T>>() {
  @Suppress("UNCHECKED_CAST")
  override val javaType: Class<out Set<T>>
    get() = Set::class.java as Class<out Set<T>>

  override fun newCollection(size: Int) = HashSet<T>(size)
}
