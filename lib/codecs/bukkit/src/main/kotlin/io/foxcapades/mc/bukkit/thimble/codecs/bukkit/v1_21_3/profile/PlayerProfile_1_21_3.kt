package io.foxcapades.mc.bukkit.thimble.codecs.bukkit.v1_21_3.profile

import io.foxcapades.mc.bukkit.thimble.codecs.bukkit.*
import io.foxcapades.mc.bukkit.thimble.codecs.fields.CustomEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.FieldDecoder
import io.foxcapades.mc.bukkit.thimble.codecs.fields.NullableEncoder
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.ByteCodec
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.NullCodec
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.StringCodecV1
import io.foxcapades.mc.bukkit.thimble.codecs.jvm.UUIDCodec
import org.bukkit.Bukkit
import org.bukkit.profile.PlayerProfile
import org.bukkit.profile.PlayerTextures
import org.bukkit.profile.PlayerTextures.SkinModel
import java.io.OutputStream
import java.net.URI
import java.nio.ByteBuffer

internal class PlayerProfile_1_21_3 : BukkitTypeCodec<PlayerProfile> {
  override val version      get() = v1_21_3
  override val javaType     get() = PlayerProfile::class.java
  override val bukkitTypeId get() = BukkitTypeId.PlayerProfile

  override val fieldEncoders get() = iteratorOf(
    NullableEncoder(PlayerProfile::getUniqueId, UUIDCodec),
    NullableEncoder(PlayerProfile::getName, StringCodecV1),
    CustomEncoder(PlayerProfile::getTextures, ::encodeTextures)
  )

  override val fieldDecoders get() = emptyIterator<FieldDecoder<PlayerProfile>>()

  override fun create(from: ByteBuffer): PlayerProfile {
    return Bukkit.createPlayerProfile(
      UUIDCodec.nullable(from),
      StringCodecV1.nullable(from)
    )
      // Messy, but PlayerTextures can't be created without a PlayerProfile
      // instance.  Also, there is presently no decoder type that would be
      // usable in the fieldDecoders.
      //
      // NullableComplexDecoder wouldn't work here as we would need the state
      // from both the skin and model values to call setSkin on the textures
      // instance.
      .apply { textures.also { tx ->
      val skin = StringCodecV1.nullable(from)?.let(::URI)?.toURL()
      val model = ByteCodec.nullable(from)?.toInt()?.let(SkinModel.entries::get)
      val cape = StringCodecV1.nullable(from)?.let(::URI)?.toURL()

      if (model != null)
        tx.setSkin(skin, model)
      if (cape != null)
        tx.cape = cape
    } }
  }

  private fun encodeTextures(into: OutputStream, value: PlayerTextures) {
    if (value.isEmpty) {
      NullCodec.encode(into)
      NullCodec.encode(into)
      NullCodec.encode(into)
    } else {
      StringCodecV1.nullable(into, value.skin?.toString())
      ByteCodec.encode(into, value.skinModel.ordinal.toByte())
      StringCodecV1.nullable(into, value.cape?.toString())
    }
  }
}

