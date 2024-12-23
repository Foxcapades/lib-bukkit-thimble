package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.inventory.meta

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.BukkitTypeId
import io.foxcapades.mc.bukkit.thimble.unsafe.BannerMeta
import org.bukkit.inventory.meta.BannerMeta
import java.io.InputStream


internal class BannerMeta_1_21_3 : BannerMetaBase_1_21_3<BannerMeta>() {
  override val javaType      get() = BannerMeta::class.java
  override val bukkitTypeId  get() = BukkitTypeId.BannerMeta
  override val fieldEncoders get() = baseEncoders().iterator()
  override val fieldDecoders get() = baseDecoders().iterator()

  override fun create(from: InputStream) = BannerMeta()
}
