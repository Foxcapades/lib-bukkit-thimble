package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.enchantments

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.*
import io.foxcapades.mc.bukkit.thimble.codecs.fields.CustomEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldDecoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.ByteCodec
import org.bukkit.enchantments.Enchantment
import java.io.OutputStream
import java.nio.ByteBuffer

@Suppress("NOTHING_TO_INLINE")
internal class Enchantment_1_21_3 : BukkitTypeCodec<Enchantment> {
  override val version: BukkitVersion
    get() = v1_21_3

  override val javaType: Class<out Enchantment>
    get() = Enchantment::class.java

  override val bukkitTypeId get() = BukkitTypeId.Enchantment

  override val fieldEncoders: Iterator<FieldEncoder<Enchantment>>
    get() = iteratorOf(CustomEncoder("key", { it }, ::writeBody))

  override val fieldDecoders: Iterator<FieldDecoder<Enchantment>>
    get() = emptyIterator()

  override fun create(from: ByteBuffer): Enchantment {
    val id = ByteCodec.create(from).toInt()

    // Broken up into blocks of 10 ids for a worst-case lookup of 14 ops.
    return when {
      id < 10 -> deserializeBlock1(id)
      id < 20 -> deserializeBlock2(id)
      id < 30 -> deserializeBlock3(id)
      id < 40 -> deserializeBlock4(id)
      else    -> deserializeBlock5(id)
    }
  }

  private fun writeBody(into: OutputStream, value: Enchantment) {
    val c = value.key.key[0]

    ByteCodec.encodeBody(into, when {
      c < 'f' -> serializeA_E(value, c)
      c < 'l' -> serializeF_K(value, c)
      c < 'q' -> serializeL_P(value, c)
      c < 't' -> serializeQ_S(value, c)
      else    -> serializeT_W(value, c)
    })
  }

  private inline fun deserializeBlock1(id: Int) =
    when (id) {
      1    -> Enchantment.PROTECTION
      2    -> Enchantment.FIRE_PROTECTION
      3    -> Enchantment.FEATHER_FALLING
      4    -> Enchantment.BLAST_PROTECTION
      5    -> Enchantment.PROJECTILE_PROTECTION
      6    -> Enchantment.RESPIRATION
      7    -> Enchantment.AQUA_AFFINITY
      8    -> Enchantment.THORNS
      9    -> Enchantment.DEPTH_STRIDER
      0    -> throw IllegalArgumentException("unrecognized enchantment id: 0")
      else -> throw IllegalStateException()
    }

  private inline fun deserializeBlock2(id: Int) =
    when (id) {
      10   -> Enchantment.FROST_WALKER
      11   -> Enchantment.BINDING_CURSE
      12   -> Enchantment.SHARPNESS
      13   -> Enchantment.SMITE
      14   -> Enchantment.BANE_OF_ARTHROPODS
      15   -> Enchantment.KNOCKBACK
      16   -> Enchantment.FIRE_ASPECT
      17   -> Enchantment.LOOTING
      18   -> Enchantment.SWEEPING_EDGE
      19   -> Enchantment.EFFICIENCY
      else -> throw IllegalStateException()
    }

  private inline fun deserializeBlock3(id: Int) =
    when (id) {
      20   -> Enchantment.SILK_TOUCH
      21   -> Enchantment.UNBREAKING
      22   -> Enchantment.FORTUNE
      23   -> Enchantment.POWER
      24   -> Enchantment.PUNCH
      25   -> Enchantment.FLAME
      26   -> Enchantment.INFINITY
      27   -> Enchantment.LUCK_OF_THE_SEA
      28   -> Enchantment.LURE
      29   -> Enchantment.LOYALTY
      else -> throw IllegalStateException()
    }

  private inline fun deserializeBlock4(id: Int) =
    when (id) {
      30 -> Enchantment.IMPALING
      31 -> Enchantment.RIPTIDE
      32 -> Enchantment.CHANNELING
      33 -> Enchantment.MULTISHOT
      34 -> Enchantment.QUICK_CHARGE
      35 -> Enchantment.PIERCING
      36 -> Enchantment.DENSITY
      37 -> Enchantment.BREACH
      38 -> Enchantment.WIND_BURST
      39 -> Enchantment.MENDING
      else -> throw IllegalStateException()
    }

  private inline fun deserializeBlock5(id: Int) =
    when (id) {
      40   -> Enchantment.VANISHING_CURSE
      41   -> Enchantment.SOUL_SPEED
      42   -> Enchantment.SWIFT_SNEAK
      else -> throw IllegalArgumentException("unrecognized enchantment id: $id")
    }

  private inline fun serializeA_E(enchantment: Enchantment, f: Char): Byte {
    return when (f) {
      'a' -> 7  // aqua affinity
      'c' -> 32 // channeling
      'e' -> 19 // efficiency
      'b' -> when (enchantment.key.key[1]) {
        'a'  -> 14 // bane of arthropods
        'i'  -> 11 // binding curse
        'l'  -> 4  // blast protection
        'r'  -> 37 // breach
        else -> throw IllegalStateException(enchantment.key.key)
      }
      'd' -> when (enchantment.key.key[2]) {
        'n'  -> 36 // density
        'p'  -> 9  // depth strider
        else -> throw IllegalStateException(enchantment.key.key)
      }
      else -> throw IllegalStateException(enchantment.key.key)
    }
  }

  private inline fun serializeF_K(enchantment: Enchantment, f: Char): Byte {
    return when (f) {
      'k' -> 15 // knockback
      'f' -> when (enchantment.key.key.length) {
        5  -> 25 // flame
        7  -> 22 // fortune
        11 -> 16 // fire aspect
        12 -> 10 // frost walker
        else -> when (enchantment.key.key[1]) {
          'e'  -> 3 // feather falling
          'i'  -> 2 // fire protection
          else -> throw IllegalStateException(enchantment.key.key)
        }
      }
      'i' -> when (enchantment.key.key[1]) {
        'm'  -> 30 // impaling
        'n'  -> 26 // infinity
        else -> throw IllegalStateException(enchantment.key.key)
      }
      else -> throw IllegalStateException(enchantment.key.key)
    }
  }

  private inline fun serializeL_P(enchantment: Enchantment, f: Char): Byte {
    return when (f) {
      'l' -> when (enchantment.key.key[2]) {
        'o'  -> 17 // looting
        'c'  -> 27 // luck of the sea
        'r'  -> 28 // lure
        'y'  -> 29 // loyalty
        else -> throw IllegalStateException(enchantment.key.key)
      }
      'm' -> when (enchantment.key.key[1]) {
        'e'  -> 39 // mending
        'u'  -> 33 // multishot
        else -> throw IllegalStateException(enchantment.key.key)
      }
      'p' -> when (enchantment.key.key[3]) {
        't'  -> 1  // protection
        'j'  -> 5  // projectile protection
        'e'  -> 23 // power
        'c'  -> 24 // punch
        'r'  -> 35 // piercing
        else -> throw IllegalStateException(enchantment.key.key)
      }
      else -> throw IllegalStateException(enchantment.key.key)
    }
  }

  private inline fun serializeQ_S(enchantment: Enchantment, f: Char): Byte {
    return when(f) {
      'q' -> 34 // quick charge
      'r' -> when (enchantment.key.key[1]) {
        'e'  -> 6  // respiration
        'i'  -> 31 // riptide
        else -> throw IllegalStateException(enchantment.key.key)
      }
      's' -> when (enchantment.key.key[3]) {
        'e'  -> 18 // sweeping edge
        'f'  -> 42 // swift walk
        'k'  -> 20 // silk touch
        'l'  -> 41 // soul speed
        'r'  -> 12 // sharpness
        't'  -> 13 // smite
        else -> throw IllegalStateException(enchantment.key.key)
      }
      else -> throw IllegalStateException(enchantment.key.key)
    }
  }

  private inline fun serializeT_W(enchantment: Enchantment, f: Char): Byte {
    return when (f) {
      't'  -> 8  // thorns
      'u'  -> 21 // unbreaking
      'v'  -> 40 // vanishing curse
      'w'  -> 38 // wind burst
      else -> throw IllegalStateException(enchantment.key.key)
    }
  }
}
