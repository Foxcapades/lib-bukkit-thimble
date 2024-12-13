package io.foxcapades.mc.bukkit.thimble.types.bukkit

import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.parse.ThimbleDeserializationException
import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.read.asType

import com.google.common.collect.ImmutableListMultimap
import com.google.common.collect.Multimap

import org.bukkit.NamespacedKey
import org.bukkit.Registry
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemRarity
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.inventory.meta.components.FoodComponent
import org.bukkit.inventory.meta.components.JukeboxPlayableComponent
import org.bukkit.inventory.meta.components.ToolComponent

@Suppress("UnstableApiUsage")
open class ItemMetaDeserializerBaseV1 @JvmOverloads constructor(private val indexOffset: Int = 0)
  : ComplexDeserializer<ItemMeta>
{
  private val deserializers = arrayOf<(ValueAccessor) -> Unit>(
    ::parseDisplayName,              // 0
    ::parseItemName,                 // 1
    ::parseLore,                     // 2
    ::parseCustomModelData,          // 3
    ::parseEnchants,                 // 4
    ::parseItemFlags,                // 5
    ::parseHideTooltip,              // 6
    ::parseIsUnbreakable,            // 7
    ::parseEnchantmentGlintOverride, // 8
    ::parseIsFireResistant,          // 9
    ::parseMaxStackSize,             // 10
    ::parseRarity,                   // 11
    ::parseFood,                     // 12
    ::parseTool,                     // 13
    ::parseJukeboxPlayable,          // 14
    ::parseAttributeModifiers,       // 15
  )

  /**
   * Index `0`
   *
   * If absent, value is `i32(0)`.
   */
  var displayName: String? = null

  /**
   * Index `1`
   *
   * If absent, value is `i32(0)`.
   */
  var itemName: String? = null

  /**
   * Index `2`
   *
   * If absent, value is `i32(0)`.
   */
  var lore: List<String>? = null

  /**
   * Index `3`
   *
   * If present, type is `int`, else value is `null`.
   */
  var customModelData: Int? = null

  /**
   * Index `4`: Enchantments
   *
   * If absent, value is `i32(0)`.
   *
   * If present, value is string with the following format:
   * ```
   * <key>|<level>;<key>|<level>
   * ```
   *
   * For example:
   * ```
   * minecraft:protection|1;minecraft:respiration|2
   * ```
   */
  var enchants: Map<Enchantment, Int>? = null

  /**
   * Index `5`: Item Flags
   *
   * If absent, value is `i32(0)`.
   *
   * If present, value is an octal encoded int32 value representing all the
   * applied item flags.
   *
   * As of MC v1.21.1, the enum values are:
   * ```
   * 0 = HIDE_ENCHANTS
   * 1 = HIDE_ATTRIBUTES
   * 2 = HIDE_UNBREAKABLE
   * 3 = HIDE_DESTROYS
   * 4 = HIDE_PLACED_ON
   * 5 = HIDE_ADDITIONAL_TOOLTIP
   * 6 = HIDE_DYE
   * 7 = HIDE_ARMOR_TRIM
   * ```
   *
   * To decode, shift off the first `3` bits until value is `0`.
   */
  var itemFlags: Set<ItemFlag>? = null

  /**
   * Index `6`: Hide Tooltip Flag
   *
   * Represented as either int value `0` or `1`
   */
  var hideTooltip: Boolean = false

  /**
   * Index `7`: Unbreakable Flag
   *
   * Represented as either int value `0` or `1`
   */
  var unbreakable: Boolean = false

  /**
   * Index `8`: Enchantment Glint Override
   *
   * Represented as a trinary value with the options `0`, `1`, or `2`.
   *
   * * `0` = `false`
   * * `1` = `true`
   * * `2` = `null`
   */
  var enchantmentGlintOverride: Boolean? = null

  /**
   * Index `9`: Fire-Resistant Flag
   *
   * Represented as either int value `0` or `1`
   */
  var fireResistant: Boolean = false

  /**
   * Index `10`: Max Stack Size
   *
   * Value of `0` == `null`.
   */
  var maxStackSize: Int? = null

  /**
   * Index `11`: Item Rarity
   *
   * If absent, value is `i32(-1)`.
   *
   * If present, value is the index of the enum value.
   *
   * As of MC v1.21.1, the enum values are:
   * ```
   * 0 = COMMON
   * 1 = UNCOMMON
   * 2 = RARE
   * 3 = EPIC
   * ```
   */
  var rarity: ItemRarity? = null

  /**
   * Index `12`: Food Component
   *
   * If absent, value is `i32(0)`.
   */
  var food: FoodComponent? = null

  /**
   * Index `13`: Tool Component
   *
   * If absent, value is `i32(0)`.
   */
  var tool: ToolComponent? = null

  /**
   * Index `14`: Jukebox Component
   *
   * If absent, value is `i32(0)`.
   */
  var jukeboxPlayable: JukeboxPlayableComponent? = null

  /**
   * Index `15`: Attribute Modifiers
   *
   * If absent, value is `i32(0)`.
   */
  var attributeModifiers: Multimap<Attribute, AttributeModifier> = ImmutableListMultimap.of()

  override fun append(index: Int, value: ValueAccessor) {
    val idx = index - indexOffset

    if (idx >= deserializers.size)
      throw ThimbleDeserializationException("invalid value index: $index")

    deserializers[idx](value)
  }

  override fun build(): ItemMeta {
    TODO("Not yet implemented")
  }

  protected fun parseDisplayName(value: ValueAccessor) {
    if (value.isString)
      displayName = value.asString()
  }

  protected fun parseItemName(value: ValueAccessor) {
    if (value.isString)
      itemName = value.asString()
  }

  protected fun parseLore(value: ValueAccessor) {
    if (value.isComplex)
      lore = value.asComplex().asType()
  }

  protected fun parseCustomModelData(value: ValueAccessor) {
    if (value.isNumber)
      customModelData = value.asInt()
  }

  protected fun parseEnchants(value: ValueAccessor) {
    if (value.isString)
      enchants = value.asString().splitToSequence(';')
        .map {
          val i = it.indexOf('|')
          Registry.ENCHANTMENT.get(NamespacedKey.fromString(it.substring(0, i))!!)!! to it.substring(i+1).toInt()
        }
        .toMap()
  }

  protected fun parseItemFlags(value: ValueAccessor) {
    var oct = value.asInt()

    if (oct > 0) {
      val tmp = IntArray(8)
      var pos = 0

      while (oct > 0) {
        tmp[pos++] = oct and 7
        oct = oct ushr 3
      }

      itemFlags = HashSet(pos)

      for (i in 0 ..< pos) {
        (itemFlags as MutableSet).add(ItemFlag.entries[tmp[i]])
      }
    }
  }

  protected fun parseHideTooltip(value: ValueAccessor) {
    hideTooltip = value.asBoolean()
  }

  protected fun parseIsUnbreakable(value: ValueAccessor) {
    unbreakable = value.asBoolean()
  }

  protected fun parseEnchantmentGlintOverride(value: ValueAccessor) {
    when (value.asInt()) {
      0 -> enchantmentGlintOverride = false
      1 -> enchantmentGlintOverride = true
    }
  }

  protected fun parseIsFireResistant(value: ValueAccessor) {
    fireResistant = value.asInt() == 1
  }

  protected fun parseMaxStackSize(value: ValueAccessor) {
    when (val v = value.asInt()) {
      0    -> {}
      else -> maxStackSize = v
    }
  }

  protected fun parseRarity(value: ValueAccessor) {
    when (val v = value.asInt()) {
      -1   -> {}
      else -> rarity = ItemRarity.entries[v]
    }
  }

  protected fun parseFood(value: ValueAccessor) {
    if (value.isComplex) {
      food = value.asComplex().asType()
    }
  }

  protected fun parseTool(value: ValueAccessor) {
    if (value.isComplex) {
      tool = value.asComplex().asType()
    }
  }

  protected fun parseJukeboxPlayable(value: ValueAccessor) {
    if (value.isComplex)
      jukeboxPlayable = value.asComplex().asType()
  }

  protected fun parseAttributeModifiers(value: ValueAccessor) {
    if (value.isComplex)
      attributeModifiers = value.asComplex().asType()
  }
}
