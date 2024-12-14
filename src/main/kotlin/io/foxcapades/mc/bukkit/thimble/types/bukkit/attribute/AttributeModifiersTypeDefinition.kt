package io.foxcapades.mc.bukkit.thimble.types.bukkit.attribute

import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.ComplexTypeDefinition
import io.foxcapades.mc.bukkit.thimble.util.B1
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

import com.google.common.collect.ArrayListMultimap
import com.google.common.collect.ImmutableListMultimap
import com.google.common.collect.Multimap

import org.bukkit.NamespacedKey
import org.bukkit.Registry
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier


open class AttributeModifiersTypeDefinition : ComplexTypeDefinition<Multimap<Attribute, AttributeModifier>> {
  @Suppress("UNCHECKED_CAST")
  override val actualType     get() = Multimap::class.java as Class<out Multimap<Attribute, AttributeModifier>>
  override val typeIdentifier get() = "b:a:AMs"
  override val currentVersion get() = B1

  override fun serialize(value: Multimap<Attribute, AttributeModifier>, writer: ValueWriter) {
    val keys = value.keySet()

    writer.writeInt(keys.size)

    for (key in keys) {
      val v = value[key]

      if (v.isNullOrEmpty())
        writer.writeNull()
      else if (v is List<*>)
        writer.writeComplex(v)
      else
        writer.writeComplex(v.toList())
    }
  }

  override fun deserializerFor(version: Byte): ComplexDeserializer<out Multimap<Attribute, AttributeModifier>>? =
    when (version) {
      B1   -> AttributeModifiersDeserializerV1()
      else -> null
    }
}

private class AttributeModifiersDeserializerV1 : ComplexDeserializer<Multimap<Attribute, AttributeModifier>> {
  /**
   * Index `0`:
   *
   * Size of the multimap.
   *
   * Indices `1+`:
   *
   * Values are broken up into 2 element pairs with odd numbers being keys, and
   * even numbers being values.
   *
   * So for example:
   * ```
   * 1 = "foo.bar"
   * 2 = [modifier, modifier]
   * 3 = "fizz.buzz"
   * 4 = null
   * ```
   *
   * Null values represent empty lists, which should not happen, but that isn't
   * our job to fix.
   */
  private var multimap: Multimap<Attribute, AttributeModifier> = ImmutableListMultimap.of()

  /**
   * Used to hold keys from one item to the next.
   */
  private lateinit var lastKey: Attribute

  @Suppress("UNCHECKED_CAST")
  override fun append(index: Int, value: ValueAccessor) {
    if (index == 0)
      multimap = ArrayListMultimap.create(value.asInt(), 2)
    else if (index % 2 == 0)
      multimap.putAll(lastKey, value.asComplex().asType(List::class.java) as List<AttributeModifier>)
    else
      lastKey = Registry.ATTRIBUTE.get(NamespacedKey.minecraft(value.asString()))!!
  }

  override fun build(): Multimap<Attribute, AttributeModifier> = multimap
}
