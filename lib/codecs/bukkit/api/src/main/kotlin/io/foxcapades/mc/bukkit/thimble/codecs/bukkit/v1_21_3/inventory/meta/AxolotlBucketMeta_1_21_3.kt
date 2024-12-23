package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.meta

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitTypeId
import io.foxcapades.mc.bukkit.thimble.codecs.fields.*
import io.foxcapades.mc.bukkit.thimble.types.DataType
import org.bukkit.Material
import org.bukkit.entity.Axolotl
import org.bukkit.inventory.meta.AxolotlBucketMeta
import java.io.InputStream
import java.io.OutputStream

internal class AxolotlBucketMeta_1_21_3 : ItemMetaBase_1_21_3<AxolotlBucketMeta>() {
  private val enumIndex get() = arrayOf(
    Axolotl.Variant.LUCY,
    Axolotl.Variant.WILD,
    Axolotl.Variant.GOLD,
    Axolotl.Variant.CYAN,
    Axolotl.Variant.BLUE,
  )

  override val bukkitTypeId  get() = BukkitTypeId.AxolotlBucketMeta
  override val javaType      get() = AxolotlBucketMeta::class.java

  override val fieldEncoders get() = (baseEncoders() + arrayOf(
    ConditionalEncoder(AxolotlBucketMeta::hasVariant, AxolotlBucketMeta::getVariant, ::writeVariant),
  )).iterator()

  override val fieldDecoders get() = (baseDecoders() + arrayOf(
    NullableDecoder1(FullDecoder(AxolotlBucketMeta::setVariant, DataType.Byte, ::readVariant))
  )).iterator()

  override fun create(from: InputStream): AxolotlBucketMeta =
    Material.TROPICAL_FISH_BUCKET.createMeta()

  private fun writeVariant(into: OutputStream, variant: Axolotl.Variant) =
    EnumByteBodyEncoder.encodeFully(into, enumIndex, variant)

  private fun readVariant(from: InputStream): Axolotl.Variant =
    EnumByteBodyDecoder.decodeFully(from, enumIndex, 1u)
}
