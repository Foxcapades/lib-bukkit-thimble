package io.foxcapades.mc.bukkit.thimble.types.bukkit.potion

import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.parse.ThimbleDeserializationException
import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.ComplexTypeDefinition
import io.foxcapades.mc.bukkit.thimble.util.B1
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

import org.bukkit.NamespacedKey
import org.bukkit.Registry
import org.bukkit.potion.PotionEffect


open class PotionEffectTypeDefinition : ComplexTypeDefinition<PotionEffect> {
  override val actualType     get() = PotionEffect::class.java
  override val typeIdentifier get() = "b:p:PE"
  override val currentVersion get() = B1

  override fun serialize(value: PotionEffect, writer: ValueWriter) {
    writer.writeInt(value.amplifier)
    writer.writeInt(value.duration)
    writer.writeString(value.type.key.toString())
    writer.writeBoolean(value.isAmbient)
    writer.writeBoolean(value.hasParticles())
    writer.writeBoolean(value.hasIcon())
  }

  override fun deserializerFor(version: Byte): ComplexDeserializer<out PotionEffect>? =
    when (version) {
      B1 -> object : ComplexDeserializer<PotionEffect> {
        private var amplifier = 0
        private var duration = 0
        private var key = ""
        private var isAmbient = false
        private var hasParticles = false
        private var hasIcon = false

        override fun append(index: Int, value: ValueAccessor) {
          when (index) {
            0 -> amplifier = value.asInt()
            1 -> duration = value.asInt()
            2 -> key = value.asString()
            3 -> isAmbient = value.asBoolean()
            4 -> hasParticles = value.asBoolean()
            5 -> hasIcon = value.asBoolean()
            else -> throw ThimbleDeserializationException("invalid value index: $index")
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

      else -> null
    }
}
