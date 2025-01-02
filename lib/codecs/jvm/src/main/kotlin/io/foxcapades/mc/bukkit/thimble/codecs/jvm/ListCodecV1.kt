package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.codecs.Codec

class ListCodecV1<T>(override val valueCodec: Codec<T>) : SequenceCodecV1<T, List<T>>() {
  @Suppress("UNCHECKED_CAST")
  override val javaType
    get() = List::class.java as Class<out List<T>>

  override fun newCollection(size: Int) = ArrayList<T>(size)
}

