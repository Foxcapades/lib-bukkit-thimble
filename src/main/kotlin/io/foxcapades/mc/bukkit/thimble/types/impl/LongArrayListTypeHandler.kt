package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

data object LongArrayListTypeHandler : SimpleListTypeHandler<LongArray>() {
  override val typeIndicator: String
    get() = "<[l]>"

  override val elementType: Class<LongArray>
    get() = LongArray::class.java

  override fun writeValue(value: LongArray, writer: ValueWriter) =
    writer.writeLongArray(value)

  override fun readValue(reader: ValueAccessor) =
    reader.asComplex().asType(LongArray::class.java)
}
