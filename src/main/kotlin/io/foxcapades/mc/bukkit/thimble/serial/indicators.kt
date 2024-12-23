package io.foxcapades.mc.bukkit.thimble.serial

/**
 * {A}{B}[({C}{D...})...]
 *
 * A = Type Indicator (13)
 * B = Combined type count (uint8)
 * C = Contained value type indicator (uint8)
 * D = Contained value data (type specific)
 */
internal const val TypeCompound: Byte = 12
