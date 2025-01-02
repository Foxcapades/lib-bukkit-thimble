package io.foxcapades.mc.bukkit.thimble.codecs

import io.foxcapades.mc.bukkit.thimble.ThimbleException
import io.foxcapades.mc.bukkit.thimble.codecs.reflect.ComplexType

interface CustomCodecProvider {
  fun <T> getCodec(type: Class<T & Any>): Codec<T>?

  fun <T> requireCodec(type: Class<T & Any>): Codec<T> =
    getCodec(type) ?: throw ThimbleException("no custom codec found for type $type")

  fun <T> getCodec(type: Class<T & Any>, vararg versions: Any): Codec<T>?

  fun <T> requireCodec(type: Class<T & Any>, vararg versions: Any): Codec<T> =
    getCodec(type)
      ?: throw ThimbleException("no custom codec found for type $type,"
        + " version ${versions.joinToString(", ", "[", "]")}")

  fun <T> getCodec(type: ComplexType<T & Any>): Codec<T>?

  fun <T> requireCodec(type: ComplexType<T & Any>): Codec<T> =
    getCodec(type) ?: throw ThimbleException("no custom codec found for type $type")

  fun <T> getCodec(type: ComplexType<T & Any>, vararg versions: Any): Codec<T>?

  fun <T> requireCodec(type: ComplexType<T & Any>, vararg versions: Any): Codec<T> =
    getCodec(type)
      ?: throw ThimbleException("no custom codec found for type $type,"
        + " version ${versions.joinToString(", ", "[", "]")}")
}
