package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.read.NumberAccessor
import io.foxcapades.mc.bukkit.thimble.types.jvm.BigIntegerTypeDefinition
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.math.BigInteger

class BigIntegerThimbleTypeDefinitionTest {
  @Test
  fun deserializerFor() {
    assertEquals(BigInteger.TEN, BigIntegerTypeDefinition.deserializerFor(1)!!.invoke(NumberAccessor("10")))
  }
}
