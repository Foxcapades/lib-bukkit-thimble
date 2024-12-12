package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.read.NumberAccessor
import io.foxcapades.mc.bukkit.thimble.read.StringAccessor
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class BinaryListTypeHandlerTest {
  @Test
  fun deserializerFor() {
    assertEquals(listOf("hello", "goodbye"), BinaryListTypeHandler.deserializerFor(1)!!.let {
      it.append(0, NumberAccessor("2"))
      it.append(1, StringAccessor("aGVsbG8="))
      it.append(2, StringAccessor("Z29vZGJ5ZQ=="))
      it.build().map(::String)
    })
  }
}
