package io.foxcapades.mc.bukkit.thimble.codecs

interface DictionaryCodecProvider {
  fun <K, V, M: MutableMap<K, V>> getCodec(
    keyCodec:   Codec<K>,
    valueCodec: Codec<V>,
    mapType:    Class<out MutableMap<*, *>>,
  ): DictionaryCodec<K, V, M>?

  fun <K, V, M: MutableMap<K, V>> requireCodec(
    keyCodec:   Codec<K>,
    valueCodec: Codec<V>,
    mapType:    Class<out MutableMap<*, *>>,
  ): DictionaryCodec<K, V, M>

  fun <K, V, M: MutableMap<K, V>> getCodec(
    keyCodec:   Codec<K>,
    valueCodec: Codec<V>,
    mapType:    Class<out MutableMap<*, *>>,
    mapVersion: U8CodecVersion,
  ): DictionaryCodec<K, V, M>?

  fun <K, V, M: MutableMap<K, V>> requireCodec(
    keyCodec:   Codec<K>,
    valueCodec: Codec<V>,
    mapType:    Class<out MutableMap<*, *>>,
    mapVersion: U8CodecVersion,
  ): DictionaryCodec<K, V, M>
}
