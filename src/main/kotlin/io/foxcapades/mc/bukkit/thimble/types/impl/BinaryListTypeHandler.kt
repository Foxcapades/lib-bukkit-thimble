package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

data object BinaryListTypeHandler : SimpleListTypeHandler<ByteArray>() {
  override val typeIndicator: String
    get() = "<bin>"

  override val elementType: Class<ByteArray>
    get() = ByteArray::class.java

  override fun writeValue(value: ByteArray, writer: ValueWriter) =
    writer.writeBinary(value)

  @OptIn(ExperimentalEncodingApi::class)
  override fun readValue(reader: ValueAccessor) =
    Base64.decode(reader.asString())
}
