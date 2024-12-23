package io.foxcapades.mc.bukkit.thimble

open class ThimbleSerializationException : Exception {
  constructor(msg: String) : super(msg)
  constructor(cause: Throwable) : super(cause)
  constructor(msg: String, cause: Throwable) : super(msg, cause)
}
