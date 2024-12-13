package io.foxcapades.mc.bukkit.thimble.types.jvm

import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.impl.SimpleListTypeDefinition
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter


/**
 * Basic [List]<[Int]> (de)serialization provider.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since 1.0.0
 */
data object IntegerListTypeDefinition : SimpleListTypeDefinition<Int>() {
  override val elementType   get() = Int::class.java
  override val typeIdentifier get() = "<i>"

  override fun writeValue(value: Int, writer: ValueWriter) =
    writer.writeInt(value)

  override fun readValue(reader: ValueAccessor) =
    reader.asInt()
}
