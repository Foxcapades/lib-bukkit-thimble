package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

data object UntypedListTypeDefinition : SimpleListTypeDefinition<Any>() {
  override val typeIdentifier: String
    get() = "<*>"

  override val elementType: Class<Any>
    get() = Any::class.java

  override fun writeValue(value: Any, writer: ValueWriter) =
    writer.writeComplex(value)

  override fun readValue(reader: ValueAccessor) =
    reader.asComplex().asType(Any::class.java)
}
