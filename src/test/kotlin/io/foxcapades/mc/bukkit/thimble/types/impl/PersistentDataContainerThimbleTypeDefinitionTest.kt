package io.foxcapades.mc.bukkit.thimble.types.impl

import io.foxcapades.mc.bukkit.thimble.read.StringAccessor
import io.foxcapades.mc.bukkit.thimble.types.bukkit.persistence.PersistentDataContainerTypeDefinition
import org.bukkit.NamespacedKey
import org.bukkit.persistence.PersistentDataType
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class PersistentDataContainerThimbleTypeDefinitionTest {
  @Test
  fun deserializerFor() {
    val result = PersistentDataContainerTypeDefinition.deserializerFor(1)
      .let {
        it.append(0, StringAccessor("CgAPbWluZWNyYWZ0OnN0aW5rBwANbWluZWNyYWZ0OnN1YgAAAAdnb29kYnllAAgAD21pbmVjcmFmdDpidXR0cwAFaGVsbG8A"))
        it.build()
      }

    assertEquals("hello", result.get(NamespacedKey.minecraft("butts"), PersistentDataType.STRING))
    assertTrue(result.has(NamespacedKey.minecraft("stink"), PersistentDataType.TAG_CONTAINER))

    val sub = result.get(NamespacedKey.minecraft("stink"), PersistentDataType.TAG_CONTAINER)

    assertEquals("goodbye", sub!!.get(NamespacedKey.minecraft("sub"), PersistentDataType.BYTE_ARRAY)!!.decodeToString())
  }
}
