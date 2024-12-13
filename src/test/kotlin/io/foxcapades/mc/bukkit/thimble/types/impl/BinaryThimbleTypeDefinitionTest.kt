package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.read.StringAccessor
import io.foxcapades.mc.bukkit.thimble.types.jvm.BinaryTypeDefinition
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class BinaryThimbleTypeDefinitionTest {
  @Test
  fun deserializerFor() {
    assertEquals("hello", BinaryTypeDefinition.deserializerFor(1)!!.invoke(StringAccessor("aGVsbG8=")).decodeToString())
  }
}
