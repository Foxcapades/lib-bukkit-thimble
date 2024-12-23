package io.foxcapades.mc.bukkit.thimble.unsafe

import java.io.ByteArrayOutputStream
import java.io.DataOutput
import java.io.DataOutputStream
import java.util.Optional

internal inline fun withDataOutput(initialSize: Int = 128, fn: (DataOutput) -> Unit): ByteArray =
  ByteArrayOutputStream(initialSize)
    .also { fn(DataOutputStream(it)) }
    .toByteArray()


@Suppress("NOTHING_TO_INLINE")
internal inline fun <T : Any> T?.optional(): Optional<T> =
  Optional.ofNullable(this)
