package io.foxcapades.mc.bukkit.thimble.types.jvm

import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.impl.SimpleListTypeDefinition
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter


/**
 * Basic [List]<[LongArray]> (de)serialization provider.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since 1.0.0
 */
open class LongArrayListTypeDefinition : SimpleListTypeDefinition<LongArray>() {
  override val elementType    get() = LongArray::class.java
  override val typeIdentifier get() = "<[l]>"

  override fun writeValue(value: LongArray, writer: ValueWriter) =
    writer.writeLongArray(value)

  override fun readValue(reader: ValueAccessor) =
    reader.asComplex().asType(LongArray::class.java)
}
