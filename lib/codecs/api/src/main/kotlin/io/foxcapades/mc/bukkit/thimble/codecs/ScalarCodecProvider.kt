package io.foxcapades.mc.bukkit.thimble.codecs

import io.foxcapades.mc.bukkit.thimble.ScalarType
import io.foxcapades.mc.bukkit.thimble.ThimbleDeserializationException
import io.foxcapades.mc.bukkit.thimble.codecs.reflect.ComplexType

interface ScalarCodecProvider {
  fun getCodec(forType: ScalarType): Codec<*>

  fun getCodec(forType: ScalarType, version: U8CodecVersion): Codec<*>?

  fun requireCodec(forType: ScalarType, version: U8CodecVersion): Codec<*> =
    getCodec(forType)
      ?: throw ThimbleDeserializationException("no codec available for type $forType v$version")

  fun <T> getCodec(forType: Class<T>): Codec<T>?

  fun <T> requireCodec(forType: Class<T>): Codec<T> =
    getCodec(forType)
      ?: throw ThimbleDeserializationException("no codec available for type $forType")

  fun <T> getCodec(forType: Class<T>, version: U8CodecVersion): Codec<T>?

  fun <T> requireCodec(forType: Class<T>, version: U8CodecVersion): Codec<T> =
    getCodec(forType)
      ?: throw ThimbleDeserializationException("no codec available for type $forType v$version")

  fun <T: Any> getCodec(forType: ComplexType<T>): Codec<T>?

  fun <T: Any> requireCodec(forType: ComplexType<T>): Codec<T> =
    getCodec(forType)
      ?: throw ThimbleDeserializationException("no codec available for type $forType")

  fun <T: Any> getCodec(forType: ComplexType<T>, version: U8CodecVersion): Codec<T>?

  fun <T: Any> requireCodec(forType: ComplexType<T>, version: U8CodecVersion): Codec<T> =
    getCodec(forType)
      ?: throw ThimbleDeserializationException("no codec available for type $forType v$version")
}

