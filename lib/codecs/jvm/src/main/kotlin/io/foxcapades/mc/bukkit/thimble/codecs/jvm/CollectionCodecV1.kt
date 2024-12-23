package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.codecs.Codec

class CollectionCodecV1<T : Any>(
  override val valueCodec: Codec<T>,
  private val constructor: (Int) -> Collection<T>,
) : SequenceCodecV1<T, Collection<T>>() {
  constructor(valueCodec: Codec<T>) : this(valueCodec, ::ArrayList)
  override fun newCollection(size: Int) = constructor(size)
}
