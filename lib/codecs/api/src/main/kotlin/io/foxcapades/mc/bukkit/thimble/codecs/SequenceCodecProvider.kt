package io.foxcapades.mc.bukkit.thimble.codecs

interface SequenceCodecProvider {
  fun <T, C : Any> getCodec(
    codec: Codec<T>,
    collectionType: Class<C>,
  ): SequenceCodec<T, C>?

  fun <T, C : Any> requireCodec(
    codec: Codec<T>,
    collectionType: Class<C>,
  ): SequenceCodec<T, C>

  fun <T, C : Any> getCodec(
    codec: Codec<T>,
    collectionType: Class<C>,
    collectionVersion: U8CodecVersion,
  ): SequenceCodec<T, C>?

  fun <T, C : Any> requireCodec(
    codec: Codec<T>,
    collectionType: Class<C>,
    collectionVersion: U8CodecVersion,
  ): SequenceCodec<T, C>
}
