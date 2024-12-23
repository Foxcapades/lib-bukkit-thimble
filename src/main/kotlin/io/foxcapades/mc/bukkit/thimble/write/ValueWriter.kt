package io.foxcapades.mc.bukkit.thimble.write

import java.io.OutputStream

internal typealias ValueWriter <T> = (value: T, stream: OutputStream) -> Unit
