package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.parse.ThimbleDeserializationException
import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.ComplexTypeHandler
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter
import org.bukkit.NamespacedKey
import org.bukkit.Registry
import org.bukkit.potion.PotionEffect

data object PotionEffectTypeHandler : ComplexTypeHandler<PotionEffect> {
  override val typeIndicator: String
    get() = "b:p:PE"

  override val currentVersion: Int
    get() = 1

  override val actualType: Class<PotionEffect>
    get() = PotionEffect::class.java

  override fun serialize(value: PotionEffect, writer: ValueWriter) {
    writer.writeInt(value.amplifier)
    writer.writeInt(value.duration)
    writer.writeString(value.type.key.toString())
    writer.writeBoolean(value.isAmbient)
    writer.writeBoolean(value.hasParticles())
    writer.writeBoolean(value.hasIcon())
  }

  override fun deserializerFor(version: Int): ComplexDeserializer<out PotionEffect> =
    object : ComplexDeserializer<PotionEffect> {
      private var amplifier = 0
      private var duration = 0
      private var key = ""
      private var isAmbient = false
      private var hasParticles = false
      private var hasIcon = false

      override fun append(index: Int, value: ValueAccessor) {
        when (index) {
          0    -> amplifier = value.asInt()
          1    -> duration = value.asInt()
          2    -> key = value.asString()
          3    -> isAmbient = value.asBoolean()
          4    -> hasParticles = value.asBoolean()
          5    -> hasIcon = value.asBoolean()
          else -> throw ThimbleDeserializationException("unexpected value at index $index")
        }
      }

      override fun build() =
        PotionEffect(
          Registry.EFFECT.get(NamespacedKey.fromString(key)!!)!!,
          duration,
          amplifier,
          isAmbient,
          hasParticles,
          hasIcon
        )
    }

}
