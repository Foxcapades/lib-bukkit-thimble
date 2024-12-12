package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

data object BooleanListTypeHandler : SimpleListTypeHandler<Boolean>() {
  override val typeIndicator: String
    get() = "<B>"

  override val elementType: Class<Boolean>
    get() = Boolean::class.java

  override fun writeValue(value: Boolean, writer: ValueWriter) =
    writer.writeBoolean(value)

  override fun readValue(reader: ValueAccessor) =
    reader.asBoolean()
}
