package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.attribute

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.*
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitCodecRegistry.Codec_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.fields.*
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.ByteCodec
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.DoubleCodec
import org.bukkit.attribute.AttributeModifier
import java.nio.ByteBuffer

internal class AttributeModifier_1_21_3 : BukkitTypeCodec<AttributeModifier> {
  private val enumIndex = arrayOf(
    AttributeModifier.Operation.ADD_NUMBER,
    AttributeModifier.Operation.ADD_SCALAR,
    AttributeModifier.Operation.MULTIPLY_SCALAR_1,
  )

  override val version
    get() = v1_21_3

  override val javaType: Class<out AttributeModifier>
    get() = AttributeModifier::class.java

  override val bukkitTypeId get() = BukkitTypeId.AttributeModifier

  override val fieldEncoders: Iterator<FieldEncoder<AttributeModifier>>
    get() = iteratorOf(
      BodyEncoder(AttributeModifier::getKey, Codec_1_21_3.NamespacedKey),
      BodyEncoder(AttributeModifier::getAmount, DoubleCodec),
      EnumByteBodyEncoder(AttributeModifier::getOperation, enumIndex),
      @Suppress("UnstableApiUsage")
      BodyEncoder(AttributeModifier::getSlotGroup, Codec_1_21_3.EquipmentSlotGroup),
    )

  override val fieldDecoders: Iterator<FieldDecoder<AttributeModifier>>
    get() = emptyIterator()

  @Suppress("UnstableApiUsage")
  override fun create(from: ByteBuffer): AttributeModifier {
    return AttributeModifier(
      Codec_1_21_3.NamespacedKey.create(from),
      DoubleCodec.create(from),
      EnumByteBodyDecoder.lookup(ByteCodec.create(from), enumIndex),
      Codec_1_21_3.EquipmentSlotGroup.create(from),
    )
  }
}
