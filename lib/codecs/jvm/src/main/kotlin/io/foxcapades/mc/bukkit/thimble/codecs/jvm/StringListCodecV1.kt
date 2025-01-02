package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.codecs.Codec

object StringListCodecV1 : SequenceCodecV1<String, List<String>>() {
  @Suppress("UNCHECKED_CAST")
  override val valueCodec
    get() = StringCodecV1 as Codec<String>

  @Suppress("UNCHECKED_CAST")
  override val javaType: Class<List<String>>
    get() = List::class.java as Class<List<String>>

  override fun newCollection(size: Int) = ArrayList<String>(size)
}
