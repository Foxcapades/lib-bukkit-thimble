package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.meta

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitTypeId
import io.foxcapades.mc.bukkit.thimble.codecs.fields.BodyDecoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.BodyEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.StringListCodecV1
import org.bukkit.Material
import org.bukkit.inventory.meta.WritableBookMeta
import java.io.InputStream

internal class WritableBookMeta_1_21_3 : ItemMetaBase_1_21_3<WritableBookMeta>() {
  override val javaType     get() = WritableBookMeta::class.java
  override val bukkitTypeId get() = BukkitTypeId.WritableBookMeta

  override val fieldEncoders
    get() = (baseEncoders() + arrayOf(BodyEncoder(WritableBookMeta::getPages, StringListCodecV1)))
      .iterator()

  override val fieldDecoders
    get() = (baseDecoders() + arrayOf(BodyDecoder(WritableBookMeta::setPages, StringListCodecV1)))
      .iterator()

  override fun create(from: InputStream): WritableBookMeta =
    Material.WRITABLE_BOOK.createMeta()
}
