package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.*
import io.foxcapades.mc.bukkit.thimble.codecs.fields.BodyEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldDecoder
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.LongCodec
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.UUIDCodec
import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import java.io.InputStream
import java.util.UUID

internal class OfflinePlayer_1_21_3 : BukkitTypeCodec<OfflinePlayer> {
  override val version       get() = v1_21_3
  override val javaType      get() = OfflinePlayer::class.java
  override val bukkitTypeId  get() = BukkitTypeId.OfflinePlayer
  override val fieldEncoders get() = iteratorOf(BodyEncoder(OfflinePlayer::getUniqueId, UUIDCodec))
  override val fieldDecoders get() = emptyIterator<FieldDecoder<OfflinePlayer>>()

  override fun create(from: InputStream): OfflinePlayer =
    Bukkit.getOfflinePlayer(UUID(LongCodec.create(from), LongCodec.create(from)))
}
