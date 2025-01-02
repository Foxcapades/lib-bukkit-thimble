package io.foxcapades.mc.bukkit.thimble.codecs

import io.foxcapades.mc.bukkit.thimble.ScalarType
import io.foxcapades.mc.bukkit.thimble.codecs.reflect.ComplexType

interface CodecProvider {
  fun getCodec(type: ScalarType): Codec<*>?
  fun requireCodec(type: ScalarType): Codec<*>

  fun getCodec(type: ScalarType, version: U8CodecVersion): Codec<*>?
  fun requireCodec(type: ScalarType, version: U8CodecVersion): Codec<*>

  fun <T: Any> getCodec(type: Class<T>): Codec<T>?
  fun <T: Any> requireCodec(type: Class<T>): Codec<T>

  fun <T: Any> getCodec(type: Class<T>, version: Any): Codec<T>?
  fun <T: Any> requireCodec(type: Class<T>, version: Any): Codec<T>

  fun <T: Any> getCodec(type: ComplexType<T>): Codec<T>?
  fun <T: Any> requireCodec(type: ComplexType<T>): Codec<T>
}
