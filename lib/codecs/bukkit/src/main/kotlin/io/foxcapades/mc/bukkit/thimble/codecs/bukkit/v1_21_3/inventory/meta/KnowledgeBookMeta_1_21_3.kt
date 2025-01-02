package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.meta

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitCodecRegistry.Codec_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitTypeId
import io.foxcapades.mc.bukkit.thimble.codecs.fields.ConditionalEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldDecoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.NullableDecoder1
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.ListCodecV1
import org.bukkit.Material
import org.bukkit.inventory.meta.KnowledgeBookMeta
import java.nio.ByteBuffer

internal class KnowledgeBookMeta_1_21_3: ItemMetaBase_1_21_3<KnowledgeBookMeta>() {
  override val javaType     get() = KnowledgeBookMeta::class.java
  override val bukkitTypeId get() = BukkitTypeId.KnowledgeBookMeta

  override val fieldEncoders: Iterator<FieldEncoder<KnowledgeBookMeta>>
    get() = (baseEncoders() + arrayOf(
      ConditionalEncoder(
        KnowledgeBookMeta::hasRecipes,
        KnowledgeBookMeta::getRecipes,
        ListCodecV1(Codec_1_21_3.NamespacedKey)
      ),
    )).iterator()

  override val fieldDecoders: Iterator<FieldDecoder<KnowledgeBookMeta>>
    get() = (baseDecoders() + arrayOf(
      NullableDecoder1(KnowledgeBookMeta::setRecipes, ListCodecV1(Codec_1_21_3.NamespacedKey)),
    )).iterator()

  override fun create(from: ByteBuffer): KnowledgeBookMeta =
    Material.KNOWLEDGE_BOOK.createMeta()
}
