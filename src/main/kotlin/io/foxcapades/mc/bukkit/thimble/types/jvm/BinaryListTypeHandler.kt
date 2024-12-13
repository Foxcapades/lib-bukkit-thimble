package io.foxcapades.mc.bukkit.thimble.types.jvm

import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.impl.SimpleListTypeHandler
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi


/**
 * Basic [List]<[ByteArray]> (de)serialization provider.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since 1.0.0
 */
data object BinaryListTypeHandler : SimpleListTypeHandler<ByteArray>() {
  override val elementType   get() = ByteArray::class.java
  override val typeIndicator get() = "<bin>"

  override fun writeValue(value: ByteArray, writer: ValueWriter) =
    writer.writeBinary(value)

  @OptIn(ExperimentalEncodingApi::class)
  override fun readValue(reader: ValueAccessor) =
    Base64.decode(reader.asString())
}
