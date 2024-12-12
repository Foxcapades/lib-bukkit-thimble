package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

data object IntArrayListTypeHandler : SimpleListTypeHandler<IntArray>() {
  override val typeIndicator: String
    get() = "<[i]>"

  override val elementType: Class<IntArray>
    get() = IntArray::class.java

  override fun writeValue(value: IntArray, writer: ValueWriter) =
    writer.writeIntArray(value)

  override fun readValue(reader: ValueAccessor) =
    reader.asComplex().asType(IntArray::class.java)
}
