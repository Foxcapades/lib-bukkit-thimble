package io.foxcapades.mc.bukkit.thimble.codecs.jvm

import io.foxcapades.mc.bukkit.thimble.ThimbleException
import io.foxcapades.mc.bukkit.thimble.codecs.Codec
import io.foxcapades.mc.bukkit.thimble.codecs.DictionaryCodec
import io.foxcapades.mc.bukkit.thimble.codecs.DictionaryCodecProvider
import io.foxcapades.mc.bukkit.thimble.codecs.U8CodecVersion

class DefaultDictionaryCodecProvider : DictionaryCodecProvider {
  @Suppress("UNCHECKED_CAST")
  override fun <K, V, M: MutableMap<K, V>> getCodec(
    keyCodec:   Codec<K>,
    valueCodec: Codec<V>,
    mapType:    Class<out MutableMap<*, *>>,
  ): DictionaryCodec<K, V, M>? =
    (try {
      mapType.getConstructor(Int::class.java)
        .also { it.isAccessible = true }
        .let { con -> MapCodecV1(keyCodec, valueCodec) { con.newInstance(it) as MutableMap<K, V> } }
    } catch (ignored: Throwable) { null }
      ?: try {
        mapType.getConstructor()
          .also { it.isAccessible = true }
          .let { con -> MapCodecV1(keyCodec, valueCodec) { con.newInstance() as MutableMap<K, V> } }
      } catch (ignored: Throwable) { null })
    as DictionaryCodec<K, V, M>?

  override fun <K, V, M: MutableMap<K, V>> requireCodec(
    keyCodec:   Codec<K>,
    valueCodec: Codec<V>,
    mapType:    Class<out MutableMap<*, *>>,
  ): DictionaryCodec<K, V, M> =
    getCodec(keyCodec, valueCodec, mapType)
      ?: throw ThimbleException("no usable constructor found for type $mapType")

  override fun <K, V, M: MutableMap<K, V>> getCodec(
    keyCodec:   Codec<K>,
    valueCodec: Codec<V>,
    mapType:    Class<out MutableMap<*, *>>,
    mapVersion: U8CodecVersion,
  ): DictionaryCodec<K, V, M>? =
    if (mapVersion == V1)
      getCodec(keyCodec, valueCodec, mapType)
    else
      null

  override fun <K, V, M: MutableMap<K, V>> requireCodec(
    keyCodec:   Codec<K>,
    valueCodec: Codec<V>,
    mapType:    Class<out MutableMap<*, *>>,
    mapVersion: U8CodecVersion,
  ): DictionaryCodec<K, V, M> =
    getCodec(keyCodec, valueCodec, mapType, mapVersion)
      ?: throw ThimbleException("no usable constructor found for type $mapType v$mapVersion")
}
