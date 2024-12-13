package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.read.NumberAccessor
import io.foxcapades.mc.bukkit.thimble.types.jvm.BigDecimalTypeDefinition
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.math.BigDecimal

class BigDecimalThimbleTypeDefinitionTest {
  @Test
  fun deserializerFor() {
    assertEquals(BigDecimal("1.1"), BigDecimalTypeDefinition.deserializerFor(1)!!.invoke(NumberAccessor("1.1")))
  }
}
