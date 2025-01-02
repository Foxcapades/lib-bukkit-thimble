package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.ThimbleException
import io.foxcapades.mc.bukkit.thimble.codecs.Codec
import io.foxcapades.mc.bukkit.thimble.codecs.SequenceCodec
import io.foxcapades.mc.bukkit.thimble.codecs.SequenceCodecProvider
import io.foxcapades.mc.bukkit.thimble.codecs.U8CodecVersion
import java.util.Queue

class DefaultSequenceCodecProvider : SequenceCodecProvider {
  @Suppress("UNCHECKED_CAST")
  override fun <T, C: Any> getCodec(codec: Codec<T>, collectionType: Class<C>): SequenceCodec<T, C>? =
    when {
      List::class.java.isAssignableFrom(collectionType)
        -> ListCodecV1(codec)
      Set::class.java.isAssignableFrom(collectionType)
        -> SetCodecV1(codec)
      collectionType.isArray
        -> ArrayCodecV1(codec)
      Queue::class.java.isAssignableFrom(collectionType)
        -> QueueCodecV1(codec)
      Collection::class.java.isAssignableFrom(collectionType)
        -> ListCodecV1(codec)
      Iterable::class.java.isAssignableFrom(collectionType)
        -> ListCodecV1(codec)
      Iterator::class.java.isAssignableFrom(collectionType)
        -> ListCodecV1(codec)
      Sequence::class.java.isAssignableFrom(collectionType)
        -> ListCodecV1(codec)
      else
        -> null
    } as SequenceCodec<T, C>?

  @Suppress("UNCHECKED_CAST")
  override fun <T, C: Any> requireCodec(codec: Codec<T>, collectionType: Class<C>): SequenceCodec<T, C> =
    when {
      List::class.java.isAssignableFrom(collectionType)
        -> ListCodecV1(codec)
      Set::class.java.isAssignableFrom(collectionType)
        -> SetCodecV1(codec)
      collectionType.isArray
        -> ArrayCodecV1(codec)
      Queue::class.java.isAssignableFrom(collectionType)
        -> QueueCodecV1(codec)
      Collection::class.java.isAssignableFrom(collectionType)
        -> ListCodecV1(codec)
      Iterable::class.java.isAssignableFrom(collectionType)
        -> ListCodecV1(codec)
      Iterator::class.java.isAssignableFrom(collectionType)
        -> ListCodecV1(codec)
      Sequence::class.java.isAssignableFrom(collectionType)
        -> ListCodecV1(codec)
      else
        -> throw collTypeErr(collectionType)
    } as SequenceCodec<T, C>

  override fun <T, C : Any> getCodec(
    codec: Codec<T>,
    collectionType: Class<C>,
    collectionVersion: U8CodecVersion
  ): SequenceCodec<T, C>? =
    when (collectionVersion) {
      V1   -> getCodec(codec, collectionType)
      else -> null
    }

  override fun <T, C : Any> requireCodec(
    codec: Codec<T>,
    collectionType: Class<C>,
    collectionVersion: U8CodecVersion
  ): SequenceCodec<T, C> =
    when (collectionVersion) {
      V1   -> getCodec(codec, collectionType)
      else -> null
    } ?: throw collTypeErr("$collectionType v$collectionVersion")

  @Suppress("NOTHING_TO_INLINE")
  private inline fun collTypeErr(itemType: Any) =
    ThimbleException("no codec found for sequence collection type $itemType")
}
