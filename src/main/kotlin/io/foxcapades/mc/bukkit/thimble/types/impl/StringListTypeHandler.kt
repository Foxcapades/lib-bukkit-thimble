package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

data object StringListTypeHandler : SimpleListTypeHandler<String>() {
  override val typeIndicator: String
    get() = "<S>"

  override val elementType: Class<String>
    get() = String::class.java

  override fun writeValue(value: String, writer: ValueWriter) =
    writer.writeString(value)

  override fun readValue(reader: ValueAccessor) =
    reader.asString()
}
