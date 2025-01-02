package io.foxcapades.mc.bukkit.thimble.codecs.bukkit

import io.foxcapades.mc.bukkit.thimble.ThimbleDeserializationException
import io.foxcapades.mc.bukkit.thimble.ThimbleException
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.ByteCodec
import java.io.OutputStream
import java.nio.ByteBuffer

@JvmInline
value class BukkitTypeId private constructor(internal val value: UByte) {
  companion object {
    // region v1.21.3-r1 -- 1-58
    val ArmorMeta = BukkitTypeId(1u)
    val ArmorTrim = BukkitTypeId(2u)
    val Attribute = BukkitTypeId(3u)
    val AttributeModifier = BukkitTypeId(4u)
    val AxolotlBucketMeta = BukkitTypeId(5u)
    val BannerMeta = BukkitTypeId(6u)
    val BlockDataMeta = BukkitTypeId(7u)
    val BookMeta = BukkitTypeId(8u)
    val BundleMeta = BukkitTypeId(9u)
    val Color = BukkitTypeId(10u)
    val ColorableArmorMeta = BukkitTypeId(11u)
    val CompassMeta = BukkitTypeId(12u)
    val CrossbowMeta = BukkitTypeId(13u)
    val DamageType = BukkitTypeId(14u)
    val DyeColor = BukkitTypeId(15u)
    val Enchantment = BukkitTypeId(16u)
    val EnchantmentStorageMeta = BukkitTypeId(17u)
    val EntitySnapshot = BukkitTypeId(18u)
    val EquipmentSlotGroup = BukkitTypeId(19u)
    val EquippableComponent = BukkitTypeId(20u)
    val FireworkEffect = BukkitTypeId(21u)
    val FireworkEffectMeta = BukkitTypeId(22u)
    val FireworkMeta = BukkitTypeId(23u)
    val FoodComponent = BukkitTypeId(24u)
    val ItemFlag = BukkitTypeId(25u)
    val ItemMeta = BukkitTypeId(26u)
    val ItemRarity = BukkitTypeId(27u)
    val ItemStack = BukkitTypeId(28u)
    val JukeboxPlayableComponent = BukkitTypeId(29u)
    val KnowledgeBookMeta = BukkitTypeId(30u)
    val LeatherArmorMeta = BukkitTypeId(31u)
    val Location = BukkitTypeId(32u)
    val MapMeta = BukkitTypeId(33u)
    val MapView = BukkitTypeId(34u)
    val Material = BukkitTypeId(35u)
    val MusicInstrument = BukkitTypeId(36u)
    val MusicInstrumentMeta = BukkitTypeId(37u)
    val NamespacedKey = BukkitTypeId(38u)
    val OfflinePlayer = BukkitTypeId(39u)
    val OminousBottleMeta = BukkitTypeId(40u)
    val Pattern = BukkitTypeId(41u)
    val PlayerProfile = BukkitTypeId(42u)
    val PotionEffect = BukkitTypeId(43u)
    val PotionEffectType = BukkitTypeId(44u)
    val PotionMeta = BukkitTypeId(45u)
    val PotionType = BukkitTypeId(46u)
    val ShieldMeta = BukkitTypeId(47u)
    val SkullMeta = BukkitTypeId(48u)
    val SpawnEggMeta = BukkitTypeId(49u)
    val SuspiciousStewMeta = BukkitTypeId(50u)
    val ToolComponent = BukkitTypeId(51u)
    val ToolRule = BukkitTypeId(52u)
    val TrimMaterial = BukkitTypeId(53u)
    val TrimPattern = BukkitTypeId(54u)
    val TropicalFishBucketMeta = BukkitTypeId(55u)
    val UseCooldownComponent = BukkitTypeId(56u)
    val EntityType = BukkitTypeId(57u)
    val WritableBookMeta = BukkitTypeId(58u)
    // endregion v1.21.3-r1 -- 1-58


    // internals!!!
    internal fun idsFor(version: BukkitVersion): Sequence<BukkitTypeId> =
      when (version) {
        v1_21_3 -> sequence {
          for (i in 1..58)
            yield(BukkitTypeId(i.toUByte()))
        }

        else -> throw ThimbleException("unsupported bukkit version: $version")
      }

    fun decode(from: ByteBuffer): BukkitTypeId =
      when (val tmp = ByteCodec.create(from)) {
        in 1..58 -> BukkitTypeId(tmp.toUByte())
        else         -> throw ThimbleDeserializationException("unrecognized bukkit type id: $tmp")
      }
  }

  fun encode(into: OutputStream) {
    ByteCodec.encodeBody(into, value.toByte())
  }
}
