package io.foxcapades.mc.bukkit.thimble.types.bukkit.profile

import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.parse.ThimbleDeserializationException
import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.ComplexTypeDefinition
import io.foxcapades.mc.bukkit.thimble.util.B1
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter
import org.bukkit.Bukkit
import org.bukkit.profile.PlayerProfile
import org.bukkit.profile.PlayerTextures.SkinModel
import java.net.URI
import java.net.URL
import java.util.UUID

open class PlayerProfileTypeDefinition : ComplexTypeDefinition<PlayerProfile> {
  override val actualType     get() = PlayerProfile::class.java
  override val typeIdentifier get() = "b:p:PP"
  override val currentVersion get() = B1

  override fun serialize(value: PlayerProfile, writer: ValueWriter) {
    writer.writeStringOrNull(value.uniqueId?.toString())
    writer.writeStringOrNull(value.name)

    value.textures.also {
      writer.writeStringOrNull(it.skin?.toString())
      writer.writeStringOrNull(it.cape?.toString())
      writer.writeInt(it.skinModel.ordinal)
    }
  }

  override fun deserializerFor(version: Byte): ComplexDeserializer<out PlayerProfile>? =
    when (version) {
      B1   -> PlayerProfileDeserializerV1()
      else -> null
    }
}

private class PlayerProfileDeserializerV1 : ComplexDeserializer<PlayerProfile> {
  private var uniqueId: UUID? = null
  private var name: String? = null
  private var skin: URL? = null
  private var cape: URL? = null
  private var model = SkinModel.CLASSIC

  override fun append(index: Int, value: ValueAccessor) {
    when (index) {
      0    -> uniqueId = value.asStringOrNull()?.let(UUID::fromString)
      1    -> name = value.asStringOrNull()
      2    -> skin = value.asStringOrNull()?.let(URI::create)?.let(URI::toURL)
      3    -> cape = value.asStringOrNull()?.let(URI::create)?.let(URI::toURL)
      4    -> model = SkinModel.entries[value.asInt()]
      else -> throw ThimbleDeserializationException("invalid value index: $index")
    }
  }

  override fun build(): PlayerProfile {
    return if (uniqueId == null) {
      Bukkit.createPlayerProfile(name!!)
    } else if (name == null) {
      Bukkit.createPlayerProfile(uniqueId!!)
    } else {
      Bukkit.createPlayerProfile(uniqueId!!, name!!)
    }.apply {
      textures.also {
        it.setSkin(skin, model)
        it.cape = cape
      }
    }
  }
}
