package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.meta

import com.google.common.collect.ArrayListMultimap
import com.google.common.collect.Multimap
import io.foxcapades.mc.bukkit.thimble.ThimbleDeserializationException
import io.foxcapades.mc.bukkit.thimble.codecs.VersionedCodec
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.*
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitCodecRegistry.Codec_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.fields.*
import io.foxcapades.mc.bukkit.thimble.codecs.fields.BodyEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.ConditionalEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.BodyDecoder
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.*
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.Tag
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.damage.DamageType
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.meta.*
import java.io.InputStream
import java.io.OutputStream

@Suppress("UnstableApiUsage")
internal abstract class ItemMetaBase_1_21_3<T : ItemMeta> : BukkitTypeCodec<T> {
  override val version: BukkitVersion
    get() = v1_21_3

  @Suppress("UNCHECKED_CAST")
  protected open fun baseEncoders() = untypedBaseEncoders as Array<FieldEncoder<T>>

  @Suppress("UNCHECKED_CAST")
  protected open fun baseDecoders() = untypedBaseDecoders as Array<FieldDecoder<T>>

  @Suppress("UNCHECKED_CAST", "NOTHING_TO_INLINE")
  protected inline fun Material.createMeta(): T =
    Bukkit.getItemFactory().getItemMeta(this) as T

  companion object {
    internal inline fun <reified T: ItemMeta> getCodec(stream: InputStream, offset: UInt = 0u): BukkitTypeCodec<T> {
      (Codec_1_21_3.ItemMeta as VersionedCodec<ItemMeta, BukkitVersion>).validateAndSkipHeader(stream, offset)

      val codec = Codec_1_21_3.getCodec(stream) ?: throw ThimbleDeserializationException("no codec found")

      if (!T::class.java.isAssignableFrom(codec.javaType))
        throw ThimbleDeserializationException("unexpected codec type, wanted ${T::class.java}, got ${codec.javaType}")

      @Suppress("UNCHECKED_CAST")
      return codec as BukkitTypeCodec<T>
    }

    @Suppress("UNCHECKED_CAST")
    internal fun <T : ItemMeta> getCodec(value: T): BukkitTypeCodec<T> {
      // IMPORTANT!!! Subtypes MUST precede parent types, else incomplete meta
      // will be written!
      return when (value) {
        is ColorableArmorMeta     -> Codec_1_21_3.ColorableArmorMeta
        is LeatherArmorMeta       -> Codec_1_21_3.LeatherArmorMeta
        is ArmorMeta              -> Codec_1_21_3.ArmorMeta
        is AxolotlBucketMeta      -> Codec_1_21_3.AxolotlBucketMeta
        is ShieldMeta             -> Codec_1_21_3.ShieldMeta
        is BannerMeta             -> Codec_1_21_3.BannerMeta
        is BlockDataMeta          -> Codec_1_21_3.BlockDataMeta
        // is BlockStateMeta         -> Codec_1_21_3.BlockStateMeta
        is BookMeta               -> Codec_1_21_3.BookMeta
        is WritableBookMeta       -> Codec_1_21_3.WritableBookMeta
        is BundleMeta             -> Codec_1_21_3.BundleMeta
        is CompassMeta            -> Codec_1_21_3.CompassMeta
        is CrossbowMeta           -> Codec_1_21_3.CrossbowMeta
        is EnchantmentStorageMeta -> Codec_1_21_3.EnchantmentStorageMeta
        is FireworkEffectMeta     -> Codec_1_21_3.FireworkEffectMeta
        is FireworkMeta           -> Codec_1_21_3.FireworkMeta
        is KnowledgeBookMeta      -> Codec_1_21_3.KnowledgeBookMeta
        is MapMeta                -> Codec_1_21_3.MapMeta
        is MusicInstrumentMeta    -> Codec_1_21_3.MusicInstrumentMeta
        is OminousBottleMeta      -> Codec_1_21_3.OminousBottleMeta
        is PotionMeta             -> Codec_1_21_3.PotionMeta
        is SkullMeta              -> Codec_1_21_3.SkullMeta
        is SpawnEggMeta           -> Codec_1_21_3.SpawnEggMeta
        is SuspiciousStewMeta     -> Codec_1_21_3.SuspiciousStewMeta
        is TropicalFishBucketMeta -> Codec_1_21_3.TropicalFishBucketMeta
        else                      -> Codec_1_21_3.ItemMeta
      } as BukkitTypeCodec<T>
    }

    @JvmStatic
    protected val enchantCodec = MapCodecV1(Codec_1_21_3.Enchantment, IntCodec)

    private val itemFlagCodec = SetCodecV1(Codec_1_21_3.ItemFlag)

    private val attributeModifierCodec = MapCodecV1(Codec_1_21_3.Attribute, CollectionCodecV1(Codec_1_21_3.AttributeModifier))

    private val untypedBaseEncoders = arrayOf(
      ConditionalEncoder(ItemMeta::hasDisplayName, ItemMeta::getDisplayName, StringCodecV1),
      ConditionalEncoder(ItemMeta::hasItemName, ItemMeta::getItemName, StringCodecV1),
      ConditionalEncoder(ItemMeta::hasLore, ItemMeta::getLore, StringListCodecV1::enforceNN),
      ConditionalEncoder(ItemMeta::hasCustomModelData, ItemMeta::getCustomModelData, IntCodec),
      ConditionalEncoder(ItemMeta::hasEnchantable, ItemMeta::getEnchantable, IntCodec),
      ConditionalEncoder(ItemMeta::hasEnchants, ItemMeta::getEnchants, enchantCodec),
      ConditionalEncoder(Companion::itemFlagTest, ItemMeta::getItemFlags, itemFlagCodec),
      BodyEncoder(ItemMeta::isHideTooltip, BooleanCodec),
      ConditionalEncoder(ItemMeta::hasTooltipStyle, ItemMeta::getTooltipStyle, Codec_1_21_3.NamespacedKey::enforceNN),
      ConditionalEncoder(ItemMeta::hasItemModel, ItemMeta::getItemModel, Codec_1_21_3.NamespacedKey::enforceNN),
      BodyEncoder(ItemMeta::isUnbreakable, BooleanCodec),
      ConditionalEncoder(ItemMeta::hasEnchantmentGlintOverride, ItemMeta::getEnchantmentGlintOverride, BooleanCodec),
      BodyEncoder(ItemMeta::isGlider, BooleanCodec),
      @Suppress("DEPRECATION")
      (BodyEncoder(ItemMeta::isFireResistant, BooleanCodec)),
      ConditionalEncoder(ItemMeta::hasDamageResistant, ItemMeta::getDamageResistant, Companion::writeDamageResistant),
      ConditionalEncoder(ItemMeta::hasMaxStackSize, ItemMeta::getMaxStackSize, IntCodec),
      ConditionalEncoder(ItemMeta::hasRarity, ItemMeta::getRarity, Codec_1_21_3.ItemRarity),
      ConditionalEncoder(ItemMeta::hasUseRemainder, ItemMeta::getUseRemainder, Codec_1_21_3.ItemStack::enforceNN),
      ConditionalEncoder(ItemMeta::hasUseCooldown, ItemMeta::getUseCooldown, Codec_1_21_3.UseCooldownComponent),
      ConditionalEncoder(ItemMeta::hasFood, ItemMeta::getFood, Codec_1_21_3.FoodComponent),
      ConditionalEncoder(ItemMeta::hasTool, ItemMeta::getTool, Codec_1_21_3.ToolComponent),
      ConditionalEncoder(ItemMeta::hasEquippable, ItemMeta::getEquippable, Codec_1_21_3.EquippableComponent),
      ConditionalEncoder(
        ItemMeta::hasJukeboxPlayable,
        ItemMeta::getJukeboxPlayable,
        Codec_1_21_3.JukeboxPlayableComponent::enforceNN
      ),
      ConditionalEncoder(ItemMeta::hasAttributeModifiers, ItemMeta::getAttributeModifiers, Companion::getAttrMods),
      // CraftMetaItem implements these for all types
      repairableEncoder(),
      *damageEncoders(),
    )

    private val untypedBaseDecoders = arrayOf(
      NullableDecoder1(ItemMeta::setDisplayName, StringCodecV1),
      NullableDecoder1(ItemMeta::setItemName, StringCodecV1),
      NullableDecoder1(ItemMeta::setLore, StringListCodecV1),
      NullableDecoder1(ItemMeta::setCustomModelData, IntCodec),
      NullableDecoder1(ItemMeta::setCustomModelData, IntCodec),
      NullableDecoder1(Companion::setEnchants, enchantCodec),
      NullableDecoder1(Companion::setItemFlags, itemFlagCodec),
      BodyDecoder(ItemMeta::setHideTooltip, BooleanCodec),
      NullableDecoder1(ItemMeta::setTooltipStyle, Codec_1_21_3.NamespacedKey),
      NullableDecoder1(ItemMeta::setItemModel, Codec_1_21_3.NamespacedKey),
      BodyDecoder(ItemMeta::setUnbreakable, BooleanCodec),
      NullableDecoder1(ItemMeta::setEnchantmentGlintOverride, BooleanCodec),
      BodyDecoder(ItemMeta::setGlider, BooleanCodec),
      @Suppress("DEPRECATION")
      BodyDecoder(ItemMeta::setFireResistant, BooleanCodec),
      NullableDecoder1(Companion::setDamageResistant, Codec_1_21_3.NamespacedKey),
      NullableDecoder1(ItemMeta::setMaxStackSize, IntCodec),
      NullableDecoder1(ItemMeta::setRarity, Codec_1_21_3.ItemRarity),
      NullableDecoder1(ItemMeta::setUseRemainder, Codec_1_21_3.ItemStack),
      NullableDecoder1(ItemMeta::setUseCooldown, Codec_1_21_3.UseCooldownComponent),
      NullableDecoder1(ItemMeta::setFood, Codec_1_21_3.FoodComponent),
      NullableDecoder1(ItemMeta::setTool, Codec_1_21_3.ToolComponent),
      NullableDecoder1(ItemMeta::setEquippable, Codec_1_21_3.EquippableComponent),
      NullableDecoder1(ItemMeta::setJukeboxPlayable, Codec_1_21_3.JukeboxPlayableComponent),
      NullableDecoder1(Companion::setAttrMods, attributeModifierCodec),
      // CraftMetaItem implements these for all types
      repairableDecoder(),
      *damageDecoders(),
    )

    private fun itemFlagTest(value: ItemMeta) = value.itemFlags.isNotEmpty()

    private fun setEnchants(instance: ItemMeta, value: Map<Enchantment, Int>) =
      value.forEach { instance.addEnchant(it.key, it.value, true) }

    private fun setItemFlags(instance: ItemMeta, value: Set<ItemFlag>) =
      instance.addItemFlags(*value.toTypedArray())

    private fun writeDamageResistant(stream: OutputStream, value: Tag<DamageType>?) =
      Codec_1_21_3.NamespacedKey.encode(stream, value!!.key)

    private fun setDamageResistant(instance: ItemMeta, value: NamespacedKey) {
      instance.damageResistant = Bukkit.getTag("damage_type", value, DamageType::class.java)
        ?: throw ThimbleDeserializationException("invalid damage_type key: $value")
    }

    private fun getAttrMods(stream: OutputStream, modifiers: Multimap<Attribute, AttributeModifier>?) =
      attributeModifierCodec.encode(stream, modifiers!!.asMap())

    private fun setAttrMods(instance: ItemMeta, value: Map<Attribute, Collection<AttributeModifier>>) {
      instance.attributeModifiers = ArrayListMultimap.create<Attribute, AttributeModifier>()
        .apply { value.forEach { (k, v) -> putAll(k, v) } }
    }

    @Suppress("UNCHECKED_CAST")
    private fun damageEncoders() =
      arrayOf(
        ConditionalEncoder(Damageable::hasDamage, Damageable::getDamage, IntCodec),
        ConditionalEncoder(Damageable::hasMaxDamage, Damageable::getMaxDamage, IntCodec),
      ) as Array<FieldEncoder<out ItemMeta>>

    @Suppress("UNCHECKED_CAST")
    private fun damageDecoders() =
      arrayOf(
        NullableDecoder1(Damageable::setDamage, IntCodec),
        NullableDecoder1(Damageable::setMaxDamage, IntCodec),
      ) as Array<FieldDecoder<out ItemMeta>>

    private fun repairableEncoder() =
      ConditionalEncoder(Repairable::hasRepairCost, Repairable::getRepairCost, IntCodec)
        as FieldEncoder<out ItemMeta>

    private fun repairableDecoder() =
      NullableDecoder1(Repairable::setRepairCost, IntCodec)
        as FieldDecoder<out ItemMeta>
  }
}
