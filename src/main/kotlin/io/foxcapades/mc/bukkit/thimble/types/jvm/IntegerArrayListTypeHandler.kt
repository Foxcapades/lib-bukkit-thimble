package io.foxcapades.mc.bukkit.thimble.types.jvm

import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.impl.SimpleListTypeHandler
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter


/**
 * Basic [List]<[IntArray]> (de)serialization provider.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since 1.0.0
 */
data object IntegerArrayListTypeHandler : SimpleListTypeHandler<IntArray>() {
  override val elementType   get() = IntArray::class.java
  override val typeIndicator get() = "<[i]>"

  override fun writeValue(value: IntArray, writer: ValueWriter) =
    writer.writeIntArray(value)

  override fun readValue(reader: ValueAccessor) =
    reader.asComplex().asType(IntArray::class.java)
}
