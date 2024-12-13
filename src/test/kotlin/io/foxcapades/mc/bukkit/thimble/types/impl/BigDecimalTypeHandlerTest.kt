package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.read.NumberAccessor
import io.foxcapades.mc.bukkit.thimble.types.jvm.BigDecimalTypeHandler
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.math.BigDecimal

class BigDecimalTypeHandlerTest {
  @Test
  fun deserializerFor() {
    assertEquals(BigDecimal("1.1"), BigDecimalTypeHandler.deserializerFor(1).invoke(NumberAccessor("1.1")))
  }
}
