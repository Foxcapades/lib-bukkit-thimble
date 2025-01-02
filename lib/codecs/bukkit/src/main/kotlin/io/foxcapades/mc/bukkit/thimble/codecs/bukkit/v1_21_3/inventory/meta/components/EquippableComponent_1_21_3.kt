package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.meta.components

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.*
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitCodecRegistry.Codec_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.fields.*
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.BooleanCodec
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.ByteCodec
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.CollectionCodecV1
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.ListCodecV1
import io.foxcapades.mc.bukkit.thimble.unsafe.EquippableComponent
import org.bukkit.Registry
import org.bukkit.Sound
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.meta.components.EquippableComponent
import java.nio.ByteBuffer

private val allowedEntitiesCodec = CollectionCodecV1(Codec_1_21_3.EntityType)

@Suppress("UnstableApiUsage")
internal class EquippableComponent_1_21_3 : BukkitTypeCodec<EquippableComponent> {
  override val version: BukkitVersion
    get() = v1_21_3

  override val javaType: Class<out EquippableComponent>
    get() = EquippableComponent::class.java

  override val bukkitTypeId get() = BukkitTypeId.EquippableComponent

  override val fieldEncoders: Iterator<FieldEncoder<EquippableComponent>>
    get() = encoders.iterator()

  override val fieldDecoders: Iterator<FieldDecoder<EquippableComponent>>
    get() = emptyIterator()

  override fun create(from: ByteBuffer): EquippableComponent =
    EquippableComponent(
      EnumByteBodyDecoder.lookup(ByteCodec.create(from), enumIndex),
      Codec_1_21_3.NamespacedKey.nullable(from)
        ?.let { Registry.SOUNDS.require(it) }
        ?: Sound.ITEM_ARMOR_EQUIP_GENERIC,
      Codec_1_21_3.NamespacedKey.nullable(from),
      Codec_1_21_3.NamespacedKey.nullable(from),
      ListCodecV1(Codec_1_21_3.EntityType).create(from),
      BooleanCodec.create(from),
      BooleanCodec.create(from),
      BooleanCodec.create(from),
    )

  private companion object {
    private val enumIndex get() = arrayOf(
      EquipmentSlot.HAND,
      EquipmentSlot.OFF_HAND,
      EquipmentSlot.FEET,
      EquipmentSlot.LEGS,
      EquipmentSlot.CHEST,
      EquipmentSlot.HEAD,
      EquipmentSlot.BODY,
    )

    val encoders by lazy { arrayOf(
      EnumByteBodyEncoder(EquippableComponent::getSlot, enumIndex),
      NullableMappingEncoder(EquippableComponent::getEquipSound, { it.key }, Codec_1_21_3.NamespacedKey),
      NullableEncoder(EquippableComponent::getModel, Codec_1_21_3.NamespacedKey),
      NullableEncoder(EquippableComponent::getCameraOverlay, Codec_1_21_3.NamespacedKey),
      NullableEncoder(EquippableComponent::getAllowedEntities, allowedEntitiesCodec),
      BodyEncoder(EquippableComponent::isDispensable, BooleanCodec),
      BodyEncoder(EquippableComponent::isSwappable, BooleanCodec),
      BodyEncoder(EquippableComponent::isDamageOnHurt, BooleanCodec),
    ) }
  }
}

