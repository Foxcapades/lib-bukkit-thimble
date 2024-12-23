package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.old.read.BooleanAccessor
import io.foxcapades.mc.bukkit.thimble.old.read.NumberAccessor
import io.foxcapades.mc.bukkit.thimble.types.jvm.BooleanArrayTypeDefinition
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class BooleanArrayThimbleTypeDefinitionTest {
  @Test
  fun deserializerFor() {
    assertArrayEquals(booleanArrayOf(true, false), BooleanArrayTypeDefinition.deserializerFor(1)!!.let {
      it.append(0, NumberAccessor("2"))
      it.append(1, BooleanAccessor(true))
      it.append(2, BooleanAccessor(false))
      it.build()
    })
  }
}
