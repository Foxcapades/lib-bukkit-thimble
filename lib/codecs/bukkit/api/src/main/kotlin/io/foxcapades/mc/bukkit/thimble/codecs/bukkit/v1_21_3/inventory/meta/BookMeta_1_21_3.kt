package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.meta

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitTypeId
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.enforceNN
import io.foxcapades.mc.bukkit.thimble.codecs.fields.*
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.StringCodecV1
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.StringListCodecV1
import io.foxcapades.mc.bukkit.thimble.types.DataType
import org.bukkit.Material
import org.bukkit.inventory.meta.BookMeta
import java.io.InputStream
import java.io.OutputStream

internal class BookMeta_1_21_3 : ItemMetaBase_1_21_3<BookMeta>() {
  private val enumValues = arrayOf(
    BookMeta.Generation.ORIGINAL,
    BookMeta.Generation.COPY_OF_ORIGINAL,
    BookMeta.Generation.COPY_OF_COPY,
    BookMeta.Generation.TATTERED,
  )

  override val javaType
    get() = BookMeta::class.java

  override val bukkitTypeId
    get() = BukkitTypeId.BookMeta

  override val fieldEncoders
    get() = (baseEncoders() + arrayOf(
      ConditionalEncoder(BookMeta::hasPages, BookMeta::getPages, StringListCodecV1),
      ConditionalEncoder(BookMeta::hasTitle, BookMeta::getTitle, StringCodecV1::enforceNN),
      ConditionalEncoder(BookMeta::hasAuthor, BookMeta::getAuthor, StringCodecV1::enforceNN),
      ConditionalEncoder(BookMeta::hasGeneration, BookMeta::getGeneration, ::writeGeneration),
    )).iterator()

  override val fieldDecoders
    get() = (baseDecoders() + arrayOf(
      NullableDecoder1(BookMeta::setPages, StringListCodecV1),
      NullableDecoder1(BookMeta::setTitle, StringCodecV1),
      NullableDecoder1(BookMeta::setAuthor, StringCodecV1),
      NullableDecoder1(FullDecoder(BookMeta::setGeneration, DataType.Byte, ::readGeneration)),
    )).iterator()

  override fun create(from: InputStream): BookMeta =
    Material.BOOK.createMeta()

  private fun writeGeneration(into: OutputStream, value: BookMeta.Generation?) =
    EnumByteBodyEncoder.encodeFully(into, enumValues, value!!)

  private fun readGeneration(from: InputStream): BookMeta.Generation =
    EnumByteBodyDecoder.decodeFully(from, enumValues)
}
