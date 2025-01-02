package io.foxcapades.mc.bukkit.thimble

import io.foxcapades.mc.bukkit.thimble.codecs.*
import io.foxcapades.mc.bukkit.thimble.codecs.MagicCodec
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitCodecProvider
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitCodecRegistry
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitVersion
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.*
import io.foxcapades.mc.bukkit.thimble.codecs.reflect.*
import io.foxcapades.mc.bukkit.thimble.utils.takeAs

class DefaultCodecRegistry: CodecProvider {
  private inline val DefaultJvmVersion get() = U8CodecVersion.of(1)

  var scalarCodecs:     ScalarCodecProvider     = DefaultScalarCodecProvider()
  var sequenceCodecs:   SequenceCodecProvider   = DefaultSequenceCodecProvider()
  var dictionaryCodecs: DictionaryCodecProvider = DefaultDictionaryCodecProvider()
  var bukkitCodecs:     BukkitCodecProvider     = BukkitCodecRegistry
  var customCodecs:     CustomCodecProvider?    = null

  override fun getCodec(type: ScalarType): Codec<*>? =
    scalarCodecs.getCodec(type)

  override fun requireCodec(type: ScalarType): Codec<*> =
    scalarCodecs.requireCodec(type)

  override fun getCodec(type: ScalarType, version: U8CodecVersion): Codec<*>? =
    scalarCodecs.getCodec(type, version)

  override fun requireCodec(type: ScalarType, version: U8CodecVersion): Codec<*> =
    scalarCodecs.requireCodec(type, version)

  @Suppress("UNCHECKED_CAST")
  override fun <T: Any> getCodec(type: Class<T>): Codec<T>? {
    if (type.isPrimitive)
      return scalarCodecs.getCodec(type)

    val pkg = type.packageName

    when {
      pkg.startsWith("java.")
        -> return getJvmCodec(type, pkg, DefaultJvmVersion)

      pkg.startsWith("org.bukkit.")
        -> return bukkitCodecs.getCodec(type)

      pkg.startsWith("kotlin.")
        -> return scalarCodecs.getCodec(type)
    }

    customCodecs?.getCodec(type)
      ?.let { return it }

    return when {
      MutableMap::class.java.isAssignableFrom(type)
        -> type.withResolved<MutableMap<*, *>> {
          dictionaryCodecs.getCodec(generics[0].toAnyCodec(), generics[1].toAnyCodec(), it)
        }

      Iterable::class.java.isAssignableFrom(type)
        -> type.withResolved<Iterable<*>> { sequenceCodecs.getCodec(generics[0].toAnyCodec(), it) }

      type.isArray
        -> sequenceCodecs.getCodec(getCodec(type.arrayType()) ?: MagicCodec(this), type)

      Iterator::class.java.isAssignableFrom(type)
        -> type.withResolved<Iterator<*>> { sequenceCodecs.getCodec(generics[0].toAnyCodec(), it) }

      Sequence::class.java.isAssignableFrom(type)
        -> type.withResolved<Sequence<*>> { sequenceCodecs.getCodec(generics[0].toAnyCodec(), type) }

      else -> null
    } as Codec<T>?
  }

  override fun <T : Any> requireCodec(type: Class<T>): Codec<T> =
    getCodec(type)
      ?: throw ThimbleException("could not find codec for type $type")

  @Suppress("UNCHECKED_CAST")
  override fun <T : Any> getCodec(type: Class<T>, version: Any): Codec<T>? {
    if (type.isPrimitive)
      return scalarCodecs.getCodec(type)

    val pkg = type.packageName

    when {
      pkg.startsWith("java.")
        -> return version.takeAs<U8CodecVersion>()?.let { getJvmCodec(type, pkg, it) }

      pkg.startsWith("org.bukkit.")
        -> return version.takeAs<BukkitVersion>()?.let { bukkitCodecs.getCodec(type, it) }

      pkg.startsWith("kotlin.")
        -> return version.takeAs<U8CodecVersion>()?.let { scalarCodecs.getCodec(type, it) }
    }

    customCodecs?.getCodec(type, version)
      ?.let { return it }

    return when {
      Map::class.java.isAssignableFrom(type)
        -> type.withResolved<MutableMap<*, *>> {
          val mapVersion = version.takeAs<U8CodecVersion>() ?: return null
          dictionaryCodecs.getCodec(generics[0].toAnyCodec(version), generics[1].toAnyCodec(version), it, mapVersion)
        }

      Iterable::class.java.isAssignableFrom(type)
        -> type.withResolved<Iterable<*>> {
          val itVersion = version.takeAs<U8CodecVersion>() ?: return null
          sequenceCodecs.getCodec(generics[0].toAnyCodec(version), it, itVersion)
        }

      type.isArray -> {
        val arrVersion = version.takeAs<U8CodecVersion>() ?: return null

        sequenceCodecs.getCodec(
          getCodec(type.arrayType(), version)
            ?: getCodec(type.arrayType())
            ?: MagicCodec(this),
          type,
          arrVersion,
        )
      }

      Iterator::class.java.isAssignableFrom(type)
        -> type.withResolved<Iterator<*>> {
          val itVersion = version.takeAs<U8CodecVersion>() ?: return null
          sequenceCodecs.getCodec(generics[0].toAnyCodec(version), it, itVersion)
        }

      Sequence::class.java.isAssignableFrom(type)
        -> type.withResolved<Sequence<*>> {
          val seqVersion = version.takeAs<U8CodecVersion>() ?: return null
          sequenceCodecs.getCodec(generics[0].toAnyCodec(version), it, seqVersion)
        }

      else -> null
    } as Codec<T>?
  }

  override fun <T : Any> requireCodec(type: Class<T>, version: Any): Codec<T> =
    getCodec(type, version)
      ?: throw ThimbleException("could not find codec for type $type, version $version")

  @Suppress("UNCHECKED_CAST")
  override fun <R: Any> getCodec(type: ComplexType<R>): Codec<R>? {
    val root = type.resolve()

    return when {
      root.generics.isEmpty()
        -> getCodec(root.type)

      MutableMap::class.java.isAssignableFrom(root.type)
        -> root.withResolved<MutableMap<*, *>> {
          dictionaryCodecs.getCodec(generics[0].toAnyCodec(), generics[1].toAnyCodec(), it)
        }

      Iterable::class.java.isAssignableFrom(root.type)
        -> root.withResolved<Iterable<*>> { sequenceCodecs.getCodec(generics[0].toAnyCodec(), it) }

      Iterator::class.java.isAssignableFrom(root.type)
        -> root.withResolved<Iterator<*>> { sequenceCodecs.getCodec(generics[0].toAnyCodec(), it) }

      root.type.isArray
        -> sequenceCodecs.getCodec(getCodec(root.type.arrayType()) ?: MagicCodec(this), root.type)

      Sequence::class.java.isAssignableFrom(root.type)
        -> root.withResolved<Sequence<*>> { sequenceCodecs.getCodec(generics[0].toAnyCodec(), it) }

      else
        -> customCodecs?.getCodec(type)
    } as Codec<R>?
  }

  override fun <T : Any> requireCodec(type: ComplexType<T>): Codec<T> =
    getCodec(type)
      ?: throw ThimbleException("could not find codec for type $type")

  @Suppress("UNCHECKED_CAST")
  private fun <T> getJvmCodec(type: Class<T & Any>, pkg: String, version: U8CodecVersion): Codec<T>? {
    return when {
      pkg.startsWith("lang.", 5) -> scalarCodecs.getCodec(type, version)
      pkg.startsWith("util.", 5) -> when {
        Iterable::class.java.isAssignableFrom(type)
        || Iterator::class.java.isAssignableFrom(type)
        || Sequence::class.java.isAssignableFrom(type)
          -> sequenceCodecs.getCodec(MagicCodec(this), type)

        Map::class.java.isAssignableFrom(type)
          -> dictionaryCodecs.getCodec(MagicCodec(this), MagicCodec(this), type as Class<out MutableMap<*, *>>)

        else -> customCodecs?.getCodec(type)
      }
      pkg.startsWith("math.", 5) -> scalarCodecs.getCodec(type)
      else -> customCodecs?.getCodec(type)
    } as Codec<T>?
  }

  @Suppress("UNCHECKED_CAST", "NOTHING_TO_INLINE")
  private inline fun <T> GenericRef.toCodec() =
    (getType()?.let(::getCodec) ?: MagicCodec(this@DefaultCodecRegistry)) as Codec<T>

  @Suppress("UNCHECKED_CAST", "NOTHING_TO_INLINE")
  private inline fun <T> GenericRef.toCodec(version: Any) =
    (getType()?.let { getCodec(it, version) ?: getCodec(it) } ?: MagicCodec(this@DefaultCodecRegistry)) as Codec<T>

  @Suppress("UNCHECKED_CAST", "NOTHING_TO_INLINE")
  private inline fun GenericRef.toAnyCodec() =
    (getType()?.let(::getCodec) ?: MagicCodec(this@DefaultCodecRegistry)) as Codec<Any>

  @Suppress("UNCHECKED_CAST", "NOTHING_TO_INLINE")
  private inline fun GenericRef.toAnyCodec(version: Any) =
    (getType()?.let { getCodec(it, version) ?: getCodec(it) } ?: MagicCodec(this@DefaultCodecRegistry)) as Codec<Any>

}
