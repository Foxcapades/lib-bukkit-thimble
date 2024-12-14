@file:Suppress("UnstableApiUsage")
package io.foxcapades.mc.bukkit.thimble.types.bukkit.inventory.meta.components

import io.foxcapades.mc.bukkit.thimble.hax.meta.JukeboxPlayableComponent
import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.parse.ThimbleDeserializationException
import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.ComplexTypeDefinition
import io.foxcapades.mc.bukkit.thimble.util.B1
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

import org.bukkit.inventory.meta.components.JukeboxPlayableComponent


open class JukeboxPlayableComponentTypeDefinition : ComplexTypeDefinition<JukeboxPlayableComponent> {
  override val actualType     get() = JukeboxPlayableComponent::class.java
  override val typeIdentifier get() = "b:m:JPC"
  override val currentVersion get() = B1

  override fun serialize(value: JukeboxPlayableComponent, writer: ValueWriter) {
    writer.writeString(value.songKey.key)
    writer.writeBoolean(value.isShowInTooltip)
  }

  override fun deserializerFor(version: Byte): ComplexDeserializer<out JukeboxPlayableComponent>? =
    when (version) {
      B1   -> JukeboxPlayableComponentDeserializerV1()
      else -> null
    }
}

private class JukeboxPlayableComponentDeserializerV1 : ComplexDeserializer<JukeboxPlayableComponent> {
  /**
   * Index `0`
   *
   * [org.bukkit.JukeboxSong] key WITH NO NAMESPACE!!!.
   */
  private var songKey = ""

  /**
   * Index `1`
   */
  private var showInTooltip = false

  override fun append(index: Int, value: ValueAccessor) {
    when (index) {
      0    -> songKey = value.asString()
      1    -> showInTooltip = value.asBoolean()
      else -> throw ThimbleDeserializationException("invalid value index: $index")
    }
  }

  override fun build(): JukeboxPlayableComponent = JukeboxPlayableComponent(songKey, showInTooltip)
}
