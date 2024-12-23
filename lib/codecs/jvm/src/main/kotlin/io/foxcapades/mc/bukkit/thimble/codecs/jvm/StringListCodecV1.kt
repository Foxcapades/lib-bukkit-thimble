package io.foxcapades.mc.bukkit.thimble.codecs.jvm

object StringListCodecV1 : SequenceCodecV1<String, List<String>>() {
  override val valueCodec get() = StringCodecV1

  @Suppress("UNCHECKED_CAST")
  override val javaType: Class<List<String>>
    get() = List::class.java as Class<List<String>>

  override fun newCollection(size: Int) = ArrayList<String>(size)
}
