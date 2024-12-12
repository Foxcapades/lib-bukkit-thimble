package io.foxcapades.mc.bukkit.thimble

class InvalidThimbleValueException : ThimbleSerializationException {
  constructor() : super("invalid slim serial value")
  constructor(msg: String) : super("invalid slim serial value: $msg")
  constructor(cause: Throwable) : super("invalid slim serial value", cause)
  constructor(msg: String, cause: Throwable) : super("invalid slim serial value: $msg", cause)
}
