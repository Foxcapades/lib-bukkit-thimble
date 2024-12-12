package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

data object IntegerListTypeHandler : SimpleListTypeHandler<Int>() {
  override val typeIndicator: String
    get() = "<i>"

  override val elementType: Class<Int>
    get() = Int::class.java

  override fun writeValue(value: Int, writer: ValueWriter) =
    writer.writeInt(value)

  override fun readValue(reader: ValueAccessor) =
    reader.asInt()
}
