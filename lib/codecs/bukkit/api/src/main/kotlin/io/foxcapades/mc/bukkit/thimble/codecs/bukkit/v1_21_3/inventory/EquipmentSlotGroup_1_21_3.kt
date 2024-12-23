package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.*
import io.foxcapades.mc.bukkit.thimble.codecs.fields.BodyEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldDecoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.StringCodecV1
import org.bukkit.inventory.EquipmentSlotGroup
import java.io.InputStream


@Suppress("UnstableApiUsage")
internal class EquipmentSlotGroup_1_21_3 : BukkitTypeCodec<EquipmentSlotGroup> {
  override val version: BukkitVersion
    get() = v1_21_3

  override val javaType: Class<out EquipmentSlotGroup>
    get() = EquipmentSlotGroup::class.java

  override val bukkitTypeId get() = BukkitTypeId.EquipmentSlotGroup

  override val fieldEncoders: Iterator<FieldEncoder<EquipmentSlotGroup>>
    get() = iteratorOf(BodyEncoder(EquipmentSlotGroup::toString, StringCodecV1))

  override val fieldDecoders: Iterator<FieldDecoder<EquipmentSlotGroup>>
    get() = emptyIterator()

  override fun create(from: InputStream): EquipmentSlotGroup =
    StringCodecV1.create(from).let {
      EquipmentSlotGroup.getByName(it)
        ?: throw IllegalStateException("invalid EquipmentSlotGroup name: $it")
    }
}
