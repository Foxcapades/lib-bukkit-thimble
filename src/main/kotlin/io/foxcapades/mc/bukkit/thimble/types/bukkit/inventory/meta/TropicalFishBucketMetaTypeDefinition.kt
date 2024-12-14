package io.foxcapades.mc.bukkit.thimble.types.bukkit.inventory.meta

import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.util.B1
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter
import org.bukkit.Bukkit
import org.bukkit.Color
import org.bukkit.DyeColor
import org.bukkit.Material
import org.bukkit.entity.TropicalFish
import org.bukkit.inventory.meta.TropicalFishBucketMeta

class TropicalFishBucketMetaTypeDefinition : ItemMetaTypeDefinitionBase<TropicalFishBucketMeta>() {
  override val actualType     get() = TropicalFishBucketMeta::class.java
  override val typeIdentifier get() = "b:m:TFB"

  override fun serialize(value: TropicalFishBucketMeta, writer: ValueWriter) {
    if (value.hasVariant()) {
      writer.writeInt(value.patternColor.color.asARGB())
      writer.writeInt(value.bodyColor.color.asARGB())
      writer.writeInt(value.pattern.ordinal)
    } else {
      writer.writeNull()
      writer.writeNull()
      writer.writeNull()
    }
    super.serialize(value, writer)
  }

  override fun deserializerFor(version: Byte): ComplexDeserializer<out TropicalFishBucketMeta>? =
    when (version) {
      B1   -> TropicalFishBucketMetaDeserializerV1()
      else -> null
    }
}

private class TropicalFishBucketMetaDeserializerV1 : ItemMetaDeserializerBaseV1<TropicalFishBucketMeta>() {
  private var patternColor: DyeColor? = null
  private var bodyColor: DyeColor? = null

  /**
   * Index `0`
   *
   * As of 1.21.1 the options are:
   * ```
   * 0  = KOB,
   * 1  = SUNSTREAK,
   * 2  = SNOOPER,
   * 3  = DASHER,
   * 4  = BRINELY,
   * 5  = SPOTTY,
   * 6  = FLOPPER,
   * 7  = STRIPEY,
   * 8  = GLITTER,
   * 9  = BLOCKFISH,
   * 10 = BETTY,
   * 11 = CLAYFISH;
   * ```
   */
  private var pattern: TropicalFish.Pattern? = null

  override fun append(index: Int, value: ValueAccessor) {
    when (index) {
      0    -> if (value.isNumber) patternColor = DyeColor.getByColor(Color.fromARGB(value.asInt()))
      1    -> if (value.isNumber) bodyColor = DyeColor.getByColor(Color.fromARGB(value.asInt()))
      2    -> if (value.isNumber) pattern = TropicalFish.Pattern.entries[value.asInt()]
      else -> super.append(index-3, value)
    }
  }

  override fun newItemMetaInstance(): TropicalFishBucketMeta =
    Bukkit.getItemFactory().getItemMeta(Material.TROPICAL_FISH_BUCKET) as TropicalFishBucketMeta

  override fun populateItemMeta(itemMeta: TropicalFishBucketMeta) {
    patternColor?.also(itemMeta::setPatternColor)
    bodyColor?.also(itemMeta::setBodyColor)
    pattern?.also(itemMeta::setPattern)
    super.populateItemMeta(itemMeta)
  }
}
