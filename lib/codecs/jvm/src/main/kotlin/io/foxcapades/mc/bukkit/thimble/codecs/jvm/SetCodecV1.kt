package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.codecs.Codec

class SetCodecV1<T : Any>(override val valueCodec: Codec<T>) : SequenceCodecV1<T, Set<T>>() {
  override fun newCollection(size: Int) = HashSet<T>(size)
}
