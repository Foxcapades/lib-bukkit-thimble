package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.codecs.Codec

class ListCodecV1<T : Any>(override val valueCodec: Codec<T>) : SequenceCodecV1<T, List<T>>() {
  override fun newCollection(size: Int) = ArrayList<T>(size)
}

