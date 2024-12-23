package io.foxcapades.mc.bukkit.thimble

open class ThimbleDeserializationException : ThimbleException {
  constructor(msg: String) : super(msg)
  constructor(cause: Throwable) : super(cause)
  constructor(msg: String, cause: Throwable) : super(msg, cause)
}
