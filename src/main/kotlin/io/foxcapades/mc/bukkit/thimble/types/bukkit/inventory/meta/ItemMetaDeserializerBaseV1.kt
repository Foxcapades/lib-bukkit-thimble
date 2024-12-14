package io.foxcapades.mc.bukkit.thimble.types.bukkit.inventory.meta

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

/**
 * Base [ComplexDeserializer] for [ItemMeta] instances serialized by
 * implementers of the [ItemMetaTypeDefinitionBase] abstract type.
 *
 * This type may be extended by `ItemMeta` subtype deserializers.
 *
 * @constructor Creates a new `ItemMeta` deserializer instance.
 *
 * @param indexOffset The offset at which base [ItemMeta] property values start
 * in the incoming [ValueAccessor].
 *
 * If subtypes place their own property values _before_ base `ItemMeta`
 * properties, then this will be the number of properties specific to the
 * subtype.
 *
 * If subtypes place their own property values _after_ base `ItemMeta`
 * properties, then this will be `0`.
 */
@Suppress("UnstableApiUsage")
abstract class ItemMetaDeserializerBaseV1<T : ItemMeta> : ComplexDeserializer<T> {
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
  protected var displayName: String? = null

  /**
   * Index `1`
   *
   * If absent, value is `i32(0)`.
   */
  protected var itemName: String? = null

  /**
   * Index `2`
   *
   * If absent, value is `i32(0)`.
   */
  protected var lore: List<String>? = null

  /**
   * Index `3`
   *
   * If present, type is `int`, else value is `null`.
   */
  protected var customModelData: Int? = null

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
  protected var enchants: Map<Enchantment, Int>? = null

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
  protected var itemFlags: Array<ItemFlag>? = null

  /**
   * Index `6`: Hide Tooltip Flag
   *
   * Represented as either int value `0` or `1`
   */
  protected var hideTooltip: Boolean = false

  /**
   * Index `7`: Unbreakable Flag
   *
   * Represented as either int value `0` or `1`
   */
  protected var unbreakable: Boolean = false

  /**
   * Index `8`: Enchantment Glint Override
   *
   * Represented as a trinary value with the options `0`, `1`, or `2`.
   *
   * * `0` = `false`
   * * `1` = `true`
   * * `2` = `null`
   */
  protected var enchantmentGlintOverride: Boolean? = null

  /**
   * Index `9`: Fire-Resistant Flag
   *
   * Represented as either int value `0` or `1`
   */
  protected var fireResistant: Boolean = false

  /**
   * Index `10`: Max Stack Size
   *
   * Value of `0` == `null`.
   */
  protected var maxStackSize: Int? = null

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
  protected var rarity: ItemRarity? = null

  /**
   * Index `12`: Food Component
   *
   * If absent, value is `i32(0)`.
   */
  protected var food: FoodComponent? = null

  /**
   * Index `13`: Tool Component
   *
   * If absent, value is `i32(0)`.
   */
  protected var tool: ToolComponent? = null

  /**
   * Index `14`: Jukebox Component
   *
   * If absent, value is `i32(0)`.
   */
  protected var jukeboxPlayable: JukeboxPlayableComponent? = null

  /**
   * Index `15`: Attribute Modifiers
   *
   * If absent, value is `i32(0)`.
   */
  protected var attributeModifiers: Multimap<Attribute, AttributeModifier> = ImmutableListMultimap.of()

  override fun append(index: Int, value: ValueAccessor) {
    if (index >= deserializers.size)
      throw ThimbleDeserializationException("invalid value index: $index")

    deserializers[index](value)
  }

  final override fun build(): T = newItemMetaInstance().also(::populateItemMeta)

  /**
   * Extension point for extenders to override and return their own `ItemMeta`
   * subtype.
   *
   * @return A new `ItemMeta` instance to be populated by [populateItemMeta].
   */
  protected abstract fun newItemMetaInstance(): T

  /**
   * Extension point for extenders to apply their own properties to the new
   * `ItemMeta` instance created by [newItemMetaInstance].
   *
   * @param itemMeta `ItemMeta` instance to populate.
   */
  protected open fun populateItemMeta(itemMeta: T) {
    itemMeta.setDisplayName(displayName)
    itemMeta.setItemName(itemName)
    itemMeta.lore = lore
    itemMeta.setCustomModelData(customModelData)
    enchants?.forEach { (e, l) -> itemMeta.addEnchant(e, l, true) }
    itemFlags?.let(itemMeta::addItemFlags)
    itemMeta.isHideTooltip = hideTooltip
    itemMeta.isUnbreakable = unbreakable
    itemMeta.setEnchantmentGlintOverride(enchantmentGlintOverride)
    itemMeta.isFireResistant = fireResistant
    itemMeta.setMaxStackSize(maxStackSize)
    itemMeta.setRarity(rarity)
    itemMeta.setFood(food)
    itemMeta.setTool(tool)
    itemMeta.jukeboxPlayable = jukeboxPlayable
    itemMeta.attributeModifiers = attributeModifiers
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

      val flags = HashSet<ItemFlag>(pos)

      for (i in 0 ..< pos) {
        flags.add(ItemFlag.entries[tmp[i]])
      }

      itemFlags = flags.toTypedArray()
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
