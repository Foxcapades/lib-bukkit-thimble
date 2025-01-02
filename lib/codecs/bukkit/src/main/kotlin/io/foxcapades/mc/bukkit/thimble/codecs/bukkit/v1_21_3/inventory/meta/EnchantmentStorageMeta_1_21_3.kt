package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.meta

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitTypeId
import io.foxcapades.mc.bukkit.thimble.codecs.fields.ConditionalEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldDecoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.NullableDecoder1
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.meta.EnchantmentStorageMeta
import java.nio.ByteBuffer

internal class EnchantmentStorageMeta_1_21_3 : ItemMetaBase_1_21_3<EnchantmentStorageMeta>() {
  override val javaType: Class<out EnchantmentStorageMeta>
    get() = EnchantmentStorageMeta::class.java

  override val bukkitTypeId get() = BukkitTypeId.EnchantmentStorageMeta

  override val fieldEncoders: Iterator<FieldEncoder<EnchantmentStorageMeta>>
    get() = (baseEncoders() + arrayOf(
      ConditionalEncoder(EnchantmentStorageMeta::hasEnchants, EnchantmentStorageMeta::getStoredEnchants, enchantCodec)
    )).iterator()

  override val fieldDecoders: Iterator<FieldDecoder<EnchantmentStorageMeta>>
    get() = (baseDecoders() + arrayOf(NullableDecoder1(::setStoredEnchants, enchantCodec))).iterator()

  override fun create(from: ByteBuffer): EnchantmentStorageMeta =
    Material.ENCHANTED_BOOK.createMeta()

  private fun setStoredEnchants(instance: EnchantmentStorageMeta, value: Map<Enchantment, Int>) =
    value.forEach { instance.addStoredEnchant(it.key, it.value, true) }
}
