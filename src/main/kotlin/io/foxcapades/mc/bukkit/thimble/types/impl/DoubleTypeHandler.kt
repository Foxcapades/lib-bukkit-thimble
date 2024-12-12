package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.parse.SimpleDeserializer
import io.foxcapades.mc.bukkit.thimble.types.SpecialTypeHandler
import io.foxcapades.mc.bukkit.thimble.types.UnaryTypeHandler
import java.text.DecimalFormat
import kotlin.math.floor

data object DoubleTypeHandler : UnaryTypeHandler<Double>, SpecialTypeHandler<Double> {
  private val format = DecimalFormat("#.###############")

  override val currentVersion get() = 1

  override val typeIndicator get() = "d"

  override val actualType get() = Double::class.java

  override fun deserializerFor(version: Int) = SimpleDeserializer { it.asDouble() }

  override fun serializeToRaw(value: Double): String {
    var fv = floor(value)

    if (fv == value)
      return fv.toString()

    val sb = StringBuilder(16)

    var v = value

    if (v < 0) {
      sb.append('-')
      v = -v
    }

    if (v > 1) {
      sb.append(fv.toInt())
      v -= fv
    } else {
      sb.append('0')
    }

    sb.append('.')

    var zCount = 0
    var digits = 0
    while (v > 0 && digits < 16) {
      v *= 10
      fv = floor(v)

      if (fv == 0.0) {
        if (zCount >= 5) {
          break
        } else {
          zCount++
        }
      } else {
        zCount = 0
      }

      sb.append(fv.toInt())
      v -= fv
      digits++
    }

    return sb.substring(0, sb.length - zCount)
  }
}
