package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.read.StringAccessor
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class BinaryTypeHandlerTest {
  @Test
  fun deserializerFor() {
    assertEquals("hello", BinaryTypeHandler.deserializerFor(1).invoke(StringAccessor("aGVsbG8=")).decodeToString())
  }
}
