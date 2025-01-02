package io.foxcapades.mc.bukkit.thimble.codecs.bukkit

import io.foxcapades.mc.bukkit.thimble.ThimbleException
import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.CodecRegistry_1_21_3

object BukkitCodecRegistry : BukkitCodecProvider {
  private inline val DefaultVersion get() = v1_21_3

  internal val Codec_1_21_3 by lazy { CodecRegistry_1_21_3() }

  override fun getCodec(type: BukkitTypeId): BukkitTypeCodec<*>? =
    getCodec(type, DefaultVersion)

  override fun requireCodec(type: BukkitTypeId): BukkitTypeCodec<*> =
    requireCodec(type, DefaultVersion)

  override fun getCodec(type: BukkitTypeId, version: BukkitVersion): BukkitTypeCodec<*>? {
    return when (version) {
      v1_21_3 -> Codec_1_21_3.getCodec(type)
      else    -> null
    }
  }

  override fun requireCodec(type: BukkitTypeId, version: BukkitVersion): BukkitTypeCodec<*> {
    return when (version) {
      v1_21_3 -> Codec_1_21_3.requireCodec(type)
      else    -> throw unsupportedVersionError(version)
    }
  }

  override fun <T : Any> getCodec(type: Class<T>): BukkitTypeCodec<T>? =
    getCodec(type, DefaultVersion)

  override fun <T : Any> requireCodec(type: Class<T>): BukkitTypeCodec<T> {
    TODO("Not yet implemented")
  }

  @Suppress("UNCHECKED_CAST")
  override fun <T : Any> getCodec(type: Class<T>, version: BukkitVersion): BukkitTypeCodec<T>? {
    return when(type.packageName) {
      "org.bukkit" -> when (type.simpleName) {
        "Color"           -> getCodec(BukkitTypeId.Color, version)
        "DyeColor"        -> getCodec(BukkitTypeId.DyeColor, version)
        "FireworkEffect"  -> getCodec(BukkitTypeId.FireworkEffect, version)
        "Location"        -> getCodec(BukkitTypeId.Location, version)
        "Material"        -> getCodec(BukkitTypeId.Material, version)
        "MusicInstrument" -> getCodec(BukkitTypeId.MusicInstrument, version)
        "NamespacedKey"   -> getCodec(BukkitTypeId.NamespacedKey, version)
        "OfflinePlayer"   -> getCodec(BukkitTypeId.OfflinePlayer, version)
        else              -> null
      }

      "org.bukkit.attribute" -> when (type.simpleName) {
        "Attribute"         -> getCodec(BukkitTypeId.Attribute, version)
        "AttributeModifier" -> getCodec(BukkitTypeId.AttributeModifier, version)
        else                -> null
      }

      "org.bukkit.block.banner" -> when (type.simpleName) {
        "Pattern" -> getCodec(BukkitTypeId.Pattern, version)
        else      -> null
      }

      "org.bukkit.damage" -> when (type.simpleName) {
        "DamageType" -> getCodec(BukkitTypeId.DamageType, version)
        else         -> null
      }

      "org.bukkit.enchantments" -> when (type.simpleName) {
        "Enchantment" -> getCodec(BukkitTypeId.Enchantment, version)
        else          -> null
      }

      "org.bukkit.entity" -> when (type.simpleName) {
        "EntitySnapshot" -> getCodec(BukkitTypeId.EntitySnapshot, version)
        "EntityType"     -> getCodec(BukkitTypeId.EntityType, version)
        else             -> null
      }

      "org.bukkit.inventory" -> when (type.simpleName) {
        "EquipmentSlotGroup" -> getCodec(BukkitTypeId.EquipmentSlotGroup, version)
        "ItemFlag"           -> getCodec(BukkitTypeId.ItemFlag, version)
        "ItemRarity"         -> getCodec(BukkitTypeId.ItemRarity, version)
        "ItemStack"          -> getCodec(BukkitTypeId.ItemStack, version)
        else                 -> null
      }

      "org.bukkit.inventory.meta" -> when (type.simpleName) {
        "ArmorMeta"              -> getCodec(BukkitTypeId.ArmorMeta, version)
        "AxolotlBucketMeta"      -> getCodec(BukkitTypeId.AxolotlBucketMeta, version)
        "BannerMeta"             -> getCodec(BukkitTypeId.BannerMeta, version)
        "BlockDataMeta"          -> getCodec(BukkitTypeId.BlockDataMeta, version)
        "BookMeta"               -> getCodec(BukkitTypeId.BookMeta, version)
        "BundleMeta"             -> getCodec(BukkitTypeId.BundleMeta, version)
        "ColorableArmorMeta"     -> getCodec(BukkitTypeId.ColorableArmorMeta, version)
        "CompassMeta"            -> getCodec(BukkitTypeId.CompassMeta, version)
        "CrossbowMeta"           -> getCodec(BukkitTypeId.CrossbowMeta, version)
        "EnchantmentStorageMeta" -> getCodec(BukkitTypeId.EnchantmentStorageMeta, version)
        "FireworkEffectMeta"     -> getCodec(BukkitTypeId.FireworkEffectMeta, version)
        "FireworkMeta"           -> getCodec(BukkitTypeId.FireworkMeta, version)
        "ItemMeta"               -> getCodec(BukkitTypeId.ItemMeta, version)
        "KnowledgeBookMeta"      -> getCodec(BukkitTypeId.KnowledgeBookMeta, version)
        "LeatherArmorMeta"       -> getCodec(BukkitTypeId.LeatherArmorMeta, version)
        "MapMeta"                -> getCodec(BukkitTypeId.MapMeta, version)
        "MusicInstrumentMeta"    -> getCodec(BukkitTypeId.MusicInstrumentMeta, version)
        "OminousBottleMeta"      -> getCodec(BukkitTypeId.OminousBottleMeta, version)
        "PotionMeta"             -> getCodec(BukkitTypeId.PotionMeta, version)
        "ShieldMeta"             -> getCodec(BukkitTypeId.ShieldMeta, version)
        "SkullMeta"              -> getCodec(BukkitTypeId.SkullMeta, version)
        "SpawnEggMeta"           -> getCodec(BukkitTypeId.SpawnEggMeta, version)
        "SuspiciousStewMeta"     -> getCodec(BukkitTypeId.SuspiciousStewMeta, version)
        "TropicalFishBucketMeta" -> getCodec(BukkitTypeId.TropicalFishBucketMeta, version)
        "WritableBookMeta"       -> getCodec(BukkitTypeId.WritableBookMeta, version)
        else                     -> null
      }

      "org.bukkit.inventory.meta.components" -> when (type.simpleName) {
        "EquippableComponent"      -> getCodec(BukkitTypeId.EquippableComponent, version)
        "FoodComponent"            -> getCodec(BukkitTypeId.FoodComponent, version)
        "JukeboxPlayableComponent" -> getCodec(BukkitTypeId.JukeboxPlayableComponent, version)
        "ToolComponent"            -> getCodec(BukkitTypeId.ToolComponent, version)
        "ToolRule"                 -> getCodec(BukkitTypeId.ToolRule, version)
        "UseCooldownComponent"     -> getCodec(BukkitTypeId.UseCooldownComponent, version)
        else -> null
      }

      "org.bukkit.inventory.meta.trim" -> when (type.simpleName) {
        "ArmorTrim"    -> getCodec(BukkitTypeId.ArmorTrim, version)
        "TrimMaterial" -> getCodec(BukkitTypeId.TrimMaterial, version)
        "TrimPattern"  -> getCodec(BukkitTypeId.TrimPattern, version)
        else           -> null
      }

      "org.bukkit.map" -> when (type.simpleName) {
        "MapView" -> getCodec(BukkitTypeId.MapView, version)
        else      -> null
      }

      "org.bukkit.potion" -> when (type.simpleName) {
        "PotionEffect"     -> getCodec(BukkitTypeId.PotionEffect, version)
        "PotionEffectType" -> getCodec(BukkitTypeId.PotionEffectType, version)
        "PotionType"       -> getCodec(BukkitTypeId.PotionType, version)
        else               -> null
      }

      "org.bukkit.profile" -> when (type.simpleName) {
        "PlayerProfile" -> getCodec(BukkitTypeId.PlayerProfile, version)
        else -> null
      }

      else -> null

    } as BukkitTypeCodec<T>?
  }

  override fun <T : Any> requireCodec(type: Class<T>, version: BukkitVersion): BukkitTypeCodec<T> =
    getCodec(type, version)
      ?: throw ThimbleException("could not find codec for $type v$version")
}
