package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.meta.components

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.*
import io.foxcapades.mc.bukkit.thimble.codecs.fields.BodyEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldDecoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.BooleanCodec
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.FloatCodec
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.IntCodec

import io.foxcapades.mc.bukkit.thimble.unsafe.FoodComponent

import org.bukkit.inventory.meta.components.FoodComponent

import java.io.InputStream

@Suppress("UnstableApiUsage")
internal class FoodComponent_1_21_3() : BukkitTypeCodec<FoodComponent> {
  override val version: BukkitVersion
    get() = v1_21_3

  override val bukkitTypeId get() = BukkitTypeId.FoodComponent

  override val javaType: Class<out FoodComponent>
    get() = FoodComponent::class.java

  override val fieldEncoders: Iterator<FieldEncoder<FoodComponent>>
    get() = iteratorOf(
      BodyEncoder(FoodComponent::getNutrition, IntCodec),
      BodyEncoder(FoodComponent::getSaturation, FloatCodec),
      BodyEncoder(FoodComponent::canAlwaysEat, BooleanCodec),
    )

  override val fieldDecoders: Iterator<FieldDecoder<FoodComponent>>
    get() = emptyIterator()


  override fun create(from: InputStream): FoodComponent =
    FoodComponent(
      IntCodec.create(from),
      FloatCodec.create(from),
      BooleanCodec.create(from),
    )
}
