package io.foxcapades.mc.bukkit.thimble.types.jvm

import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.impl.SimpleListTypeDefinition
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter


/**
 * Basic [List]<[Double]> (de)serialization provider.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since 1.0.0
 */
data object DoubleListTypeDefinition : SimpleListTypeDefinition<Double>() {
  override val elementType   get() = Double::class.java
  override val typeIdentifier get() = "<d>"

  override fun writeValue(value: Double, writer: ValueWriter) =
    writer.writeRaw(DoubleTypeDefinition.serializeToRaw(value))

  override fun readValue(reader: ValueAccessor) =
    reader.asDouble()
}
