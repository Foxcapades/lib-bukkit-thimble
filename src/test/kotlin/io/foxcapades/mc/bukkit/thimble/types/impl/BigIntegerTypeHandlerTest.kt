package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.read.NumberAccessor
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.math.BigInteger

class BigIntegerTypeHandlerTest {
  @Test
  fun deserializerFor() {
    assertEquals(BigInteger.TEN, BigIntegerTypeHandler.deserializerFor(1).invoke(NumberAccessor("10")))
  }
}
