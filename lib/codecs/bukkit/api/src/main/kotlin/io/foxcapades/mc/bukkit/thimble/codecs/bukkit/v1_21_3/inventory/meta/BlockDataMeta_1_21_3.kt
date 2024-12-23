package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.meta

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitTypeId
import io.foxcapades.mc.bukkit.thimble.codecs.fields.BodyEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.StringCodecV1
import io.foxcapades.mc.bukkit.thimble.unsafe.createMeta
import org.bukkit.Bukkit
import org.bukkit.inventory.meta.BlockDataMeta
import java.io.InputStream

internal class BlockDataMeta_1_21_3 : ItemMetaBase_1_21_3<BlockDataMeta>() {
  override val javaType      get() = BlockDataMeta::class.java
  override val bukkitTypeId  get() = BukkitTypeId.BlockDataMeta

  override val fieldEncoders
    get() = (
      // for this one we put the block meta first so we can use it to construct
      // the correct instance type on deserialization.
      arrayOf<FieldEncoder<BlockDataMeta>>(BodyEncoder(BlockDataMeta::getAsString, StringCodecV1)) +
      baseEncoders()
    ).iterator()
  override val fieldDecoders get() = baseDecoders().iterator()

  override fun create(from: InputStream): BlockDataMeta =
    Bukkit.createBlockData(StringCodecV1.create(from)).createMeta()
}
