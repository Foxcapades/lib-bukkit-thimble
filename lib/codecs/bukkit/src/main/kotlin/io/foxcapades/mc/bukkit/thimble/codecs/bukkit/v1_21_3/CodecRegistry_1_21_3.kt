package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitTypeCodec
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitTypeId
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.VersionedBukkitCodecProvider
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.attribute.AttributeModifier_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.attribute.Attribute_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.block.banner.Pattern_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.damage.DamageType_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.enchantments.Enchantment_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.entity.EntitySnapshot_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.entity.EntityType_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.EquipmentSlotGroup_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.ItemFlag_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.ItemRarity_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.ItemStack_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.meta.*
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.meta.components.*
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.meta.trim.ArmorTrim_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.meta.trim.TrimMaterial_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.meta.trim.TrimPattern_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.map.MapView_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.potion.PotionEffectType_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.potion.PotionEffect_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.potion.PotionType_1_21_3
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.profile.PlayerProfile_1_21_3

import org.bukkit.*
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.block.banner.Pattern
import org.bukkit.damage.DamageType
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.EntitySnapshot
import org.bukkit.entity.EntityType
import org.bukkit.inventory.EquipmentSlotGroup
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemRarity
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.*
import org.bukkit.inventory.meta.components.*
import org.bukkit.inventory.meta.trim.ArmorTrim
import org.bukkit.inventory.meta.trim.TrimMaterial
import org.bukkit.inventory.meta.trim.TrimPattern
import org.bukkit.map.MapView
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.potion.PotionType
import org.bukkit.profile.PlayerProfile

@Suppress("UnstableApiUsage")
internal class CodecRegistry_1_21_3 : VersionedBukkitCodecProvider {
  // This array is NOT sorted alphabetically!!!  It was initially, but things
  // have been added at the end.
  private val registry by lazy { arrayOf(
    ::ArmorMeta_1_21_3,
    ::ArmorTrim_1_21_3,
    ::Attribute_1_21_3,
    ::AttributeModifier_1_21_3,
    ::AxolotlBucketMeta_1_21_3,
    ::BannerMeta_1_21_3,
    ::BlockDataMeta_1_21_3,
    ::BookMeta_1_21_3,
    ::BundleMeta_1_21_3,
    ::Color_1_21_3,
    ::ColorableArmorMeta_1_21_3,
    ::CompassMeta_1_21_3,
    ::CrossbowMeta_1_21_3,
    ::DamageType_1_21_3,
    ::DyeColor_1_21_3,
    ::Enchantment_1_21_3,
    ::EnchantmentStorageMeta_1_21_3,
    ::EntitySnapshot_1_21_3,
    ::EquipmentSlotGroup_1_21_3,
    ::EquippableComponent_1_21_3,
    ::FireworkEffect_1_21_3,
    ::FireworkEffectMeta_1_21_3,
    ::FireworkMeta_1_21_3,
    ::FoodComponent_1_21_3,
    ::ItemFlag_1_21_3,
    ::ItemMeta_1_21_3,
    ::ItemRarity_1_21_3,
    ::ItemStack_1_21_3,
    ::JukeboxPlayableComponent_1_21_3,
    ::KnowledgeBookMeta_1_21_3,
    ::LeatherArmorMeta_1_21_3,
    ::Location_1_21_3,
    ::MapMeta_1_21_3,
    ::MapView_1_21_3,
    ::Material_1_21_3,
    ::MusicInstrument_1_21_3,
    ::MusicInstrumentMeta_1_21_3,
    ::NamespacedKey_1_21_3,
    ::OfflinePlayer_1_21_3,
    ::OminousBottleMeta_1_21_3,
    ::Pattern_1_21_3,
    ::PlayerProfile_1_21_3,
    ::PotionEffect_1_21_3,
    ::PotionEffectType_1_21_3,
    ::PotionMeta_1_21_3,
    ::PotionType_1_21_3,
    ::ShieldMeta_1_21_3,
    ::SkullMeta_1_21_3,
    ::SpawnEggMeta_1_21_3,
    ::SuspiciousStewMeta_1_21_3,
    ::ToolComponent_1_21_3,
    ::ToolRule_1_21_3,
    ::TrimMaterial_1_21_3,
    ::TrimPattern_1_21_3,
    ::TropicalFishBucketMeta_1_21_3,
    ::UseCooldownComponent_1_21_3,
    ::EntityType_1_21_3,
    ::WritableBookMeta_1_21_3,
  ) }

  override val version
    get() = v1_21_3

  val ArmorMeta: BukkitTypeCodec<ArmorMeta> get() = get(BukkitTypeId.ArmorMeta)

  val ArmorTrim: BukkitTypeCodec<ArmorTrim> get() = get(BukkitTypeId.ArmorTrim)

  val Attribute: BukkitTypeCodec<Attribute> get() = get(BukkitTypeId.Attribute)

  val AttributeModifier: BukkitTypeCodec<AttributeModifier> get() = get(BukkitTypeId.AttributeModifier)

  val AxolotlBucketMeta: BukkitTypeCodec<AxolotlBucketMeta> get() = get(BukkitTypeId.AxolotlBucketMeta)

  val BannerMeta: BukkitTypeCodec<BannerMeta> get() = get(BukkitTypeId.BannerMeta)

  val BlockDataMeta: BukkitTypeCodec<BlockDataMeta> get() = get(BukkitTypeId.BlockDataMeta)

  val BookMeta: BukkitTypeCodec<BookMeta> get() = get(BukkitTypeId.BookMeta)

  val BundleMeta: BukkitTypeCodec<BundleMeta> get() = get(BukkitTypeId.BundleMeta)

  val Color: BukkitTypeCodec<Color> get() = get(BukkitTypeId.Color)

  val ColorableArmorMeta: BukkitTypeCodec<ColorableArmorMeta> get() = get(BukkitTypeId.ColorableArmorMeta)

  val CompassMeta: BukkitTypeCodec<CompassMeta> get() = get(BukkitTypeId.CompassMeta)

  val CrossbowMeta: BukkitTypeCodec<CrossbowMeta> get() = get(BukkitTypeId.CrossbowMeta)

  val DamageType: BukkitTypeCodec<DamageType> get() = get(BukkitTypeId.DamageType)

  val DyeColor: BukkitTypeCodec<DyeColor> get() = get(BukkitTypeId.DyeColor)

  val Enchantment: BukkitTypeCodec<Enchantment> get() = get(BukkitTypeId.Enchantment)

  val EnchantmentStorageMeta: BukkitTypeCodec<EnchantmentStorageMeta> get() = get(BukkitTypeId.EnchantmentStorageMeta)

  val EntitySnapshot: BukkitTypeCodec<EntitySnapshot> get() = get(BukkitTypeId.EntitySnapshot)

  val EntityType: BukkitTypeCodec<EntityType> get() = get(BukkitTypeId.EntityType)

  val EquipmentSlotGroup: BukkitTypeCodec<EquipmentSlotGroup> get() = get(BukkitTypeId.EquipmentSlotGroup)

  val EquippableComponent: BukkitTypeCodec<EquippableComponent> get() = get(BukkitTypeId.EquippableComponent)

  val FireworkEffect: BukkitTypeCodec<FireworkEffect> get() = get(BukkitTypeId.FireworkEffect)

  val FireworkEffectMeta: BukkitTypeCodec<FireworkEffectMeta> get() = get(BukkitTypeId.FireworkEffectMeta)

  val FireworkMeta: BukkitTypeCodec<FireworkMeta> get() = get(BukkitTypeId.FireworkMeta)

  val FoodComponent: BukkitTypeCodec<FoodComponent> get() = get(BukkitTypeId.FoodComponent)

  val ItemFlag: BukkitTypeCodec<ItemFlag> get() = get(BukkitTypeId.ItemFlag)

  val ItemMeta: BukkitTypeCodec<ItemMeta> get() = get(BukkitTypeId.ItemMeta)

  val ItemRarity: BukkitTypeCodec<ItemRarity> get() = get(BukkitTypeId.ItemRarity)

  val ItemStack: BukkitTypeCodec<ItemStack> get() = get(BukkitTypeId.ItemStack)

  val JukeboxPlayableComponent: BukkitTypeCodec<JukeboxPlayableComponent> get() = get(BukkitTypeId.JukeboxPlayableComponent)

  val KnowledgeBookMeta: BukkitTypeCodec<KnowledgeBookMeta> get() = get(BukkitTypeId.KnowledgeBookMeta)

  val LeatherArmorMeta: BukkitTypeCodec<LeatherArmorMeta> get() = get(BukkitTypeId.LeatherArmorMeta)

  val Location: BukkitTypeCodec<Location> get() = get(BukkitTypeId.Location)

  val MapMeta: BukkitTypeCodec<MapMeta> get() = get(BukkitTypeId.MapMeta)

  val MapView: BukkitTypeCodec<MapView> get() = get(BukkitTypeId.MapView)

  val Material: BukkitTypeCodec<Material> get() = get(BukkitTypeId.Material)

  val MusicInstrument: BukkitTypeCodec<MusicInstrument> get() = get(BukkitTypeId.MusicInstrument)

  val MusicInstrumentMeta: BukkitTypeCodec<MusicInstrumentMeta> get() = get(BukkitTypeId.MusicInstrumentMeta)

  val NamespacedKey: BukkitTypeCodec<NamespacedKey> get() = get(BukkitTypeId.NamespacedKey)

  val OfflinePlayer: BukkitTypeCodec<OfflinePlayer> get() = get(BukkitTypeId.OfflinePlayer)

  val OminousBottleMeta: BukkitTypeCodec<OminousBottleMeta> get() = get(BukkitTypeId.OminousBottleMeta)

  val Pattern: BukkitTypeCodec<Pattern> get() = get(BukkitTypeId.Pattern)

  val PlayerProfile: BukkitTypeCodec<PlayerProfile> get() = get(BukkitTypeId.PlayerProfile)

  val PotionEffect: BukkitTypeCodec<PotionEffect> get() = get(BukkitTypeId.PotionEffect)

  val PotionEffectType: BukkitTypeCodec<PotionEffectType> get() = get(BukkitTypeId.PotionEffectType)

  val PotionMeta: BukkitTypeCodec<PotionMeta> get() = get(BukkitTypeId.PotionMeta)

  val PotionType: BukkitTypeCodec<PotionType> get() = get(BukkitTypeId.PotionType)

  val ShieldMeta: BukkitTypeCodec<ShieldMeta> get() = get(BukkitTypeId.ShieldMeta)

  val SkullMeta: BukkitTypeCodec<SkullMeta> get() = get(BukkitTypeId.SkullMeta)

  val SpawnEggMeta: BukkitTypeCodec<SpawnEggMeta> get() = get(BukkitTypeId.SpawnEggMeta)

  val SuspiciousStewMeta: BukkitTypeCodec<SuspiciousStewMeta> get() = get(BukkitTypeId.SuspiciousStewMeta)

  val ToolComponent: BukkitTypeCodec<ToolComponent> get() = get(BukkitTypeId.ToolComponent)

  val ToolRule: BukkitTypeCodec<ToolComponent.ToolRule> get() = get(BukkitTypeId.ToolRule)

  val TrimMaterial: BukkitTypeCodec<TrimMaterial> get() = get(BukkitTypeId.TrimMaterial)

  val TrimPattern: BukkitTypeCodec<TrimPattern> get() = get(BukkitTypeId.TrimPattern)

  val TropicalFishBucketMeta: BukkitTypeCodec<TropicalFishBucketMeta> get() = get(BukkitTypeId.TropicalFishBucketMeta)

  @Suppress("UnstableApiUsage")
  val UseCooldownComponent: BukkitTypeCodec<UseCooldownComponent> get() = get(BukkitTypeId.UseCooldownComponent)

  val WritableBookMeta: BukkitTypeCodec<WritableBookMeta> get() = get(BukkitTypeId.WritableBookMeta)

  override fun getCodec(forType: BukkitTypeId): BukkitTypeCodec<*>? =
    try { get<Any>(forType) }
    catch(e: Throwable) { null }

  @Suppress("UNCHECKED_CAST")
  private fun <T: Any> get(id: BukkitTypeId): BukkitTypeCodec<T> =
    registry[id.value.toInt()-1]() as BukkitTypeCodec<T>
}
