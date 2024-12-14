package io.foxcapades.mc.bukkit.thimble.types.bukkit.inventory.meta

import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.util.B1
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter
import org.bukkit.inventory.meta.WritableBookMeta

class WritableBookMetaTypeDefinition : ItemMetaTypeDefinitionBase<WritableBookMeta>() {
  override val actualType     get() = WritableBookMeta::class.java
  override val typeIdentifier get() = "b:m:WBM"

  override fun serialize(value: WritableBookMeta, writer: ValueWriter) {
    if (value.hasPages())
      writer.writeStringList(value.pages)
    else
      writer.writeInt(0)

    super.serialize(value, writer)
  }

  override fun deserializerFor(version: Byte): ComplexDeserializer<out WritableBookMeta>? =
    when (version) {
      B1   -> WriteableBookMetaDeserializerV1()
      else -> null
    }
}

