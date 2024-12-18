package io.foxcapades.mc.bukkit.thimble

open class ThimbleException : RuntimeException {
  constructor() : super()
  constructor(msg: String) : super(msg)
  constructor(cause: Throwable) : super(cause)
  constructor(msg: String, cause: Throwable) : super(msg, cause)
}
