package io.foxcapades.mc.bukkit.thimble.types.jvm

import io.foxcapades.mc.bukkit.thimble.parse.SimpleDeserializer
import io.foxcapades.mc.bukkit.thimble.types.RawTypeSerializer
import io.foxcapades.mc.bukkit.thimble.types.UnaryTypeHandler
import io.foxcapades.mc.bukkit.thimble.util.B1

import kotlin.math.floor

/**
 * Basic [Double] (de)serialization provider.
 *
 * This type also implements [RawTypeSerializer] as it serializes double values
 * directly to a raw JSON string to have better ability to prevent or limit
 * floating point value stringification issues such as bizarre rendered
 * precisions.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since 1.0.0
 */
data object DoubleTypeHandler : UnaryTypeHandler<Double>, RawTypeSerializer<Double> {
  override val actualType     get() = Double::class.java
  override val typeIndicator  get() = "d"
  override val currentVersion get() = B1

  override fun deserializerFor(version: Byte): SimpleDeserializer<Double>? =
    when (version) {
      B1   -> SimpleDeserializer { it.asDouble() }
      else -> null
    }

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
