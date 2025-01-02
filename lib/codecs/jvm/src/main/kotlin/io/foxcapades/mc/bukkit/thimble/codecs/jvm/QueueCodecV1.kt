package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.codecs.Codec
import java.util.LinkedList
import java.util.Queue

class QueueCodecV1<T>(override val valueCodec: Codec<T>) : SequenceCodecV1<T, Queue<T>>() {
  @Suppress("UNCHECKED_CAST")
  override val javaType: Class<out Queue<T>>
    get() = Queue::class.java as Class<Queue<T>>

  override fun newCollection(size: Int): Queue<T> =
    LinkedList()
}
