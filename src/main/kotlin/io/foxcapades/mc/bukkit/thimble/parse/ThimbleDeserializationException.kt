package io.foxcapades.mc.bukkit.thimble.parse

import io.foxcapades.mc.bukkit.thimble.ThimbleException

open class ThimbleDeserializationException : ThimbleException {
  constructor() : super()
  constructor(msg: String) : super(msg)
  constructor(cause: Throwable) : super(cause)
  constructor(msg: String, cause: Throwable) : super(msg, cause)
}
