package io.foxcapades.mc.bukkit.thimble.serial

@JvmInline
value class ComplexTypeIdentifier(private val id: ByteArray) {
  constructor(id: String) : this(id.toByteArray(Charsets.UTF_8))

  init {
    if (id.size > 255)
      throw IllegalArgumentException("type identifiers cannot be longer than 255 bytes in length")
  }

  override fun toString(): String = String(id, Charsets.UTF_8)
}
