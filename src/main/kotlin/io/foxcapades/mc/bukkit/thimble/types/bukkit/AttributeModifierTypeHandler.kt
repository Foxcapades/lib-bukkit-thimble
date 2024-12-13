@file:Suppress("UnstableApiUsage")
package io.foxcapades.mc.bukkit.thimble.types.bukkit

import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.parse.ThimbleDeserializationException
import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.ComplexTypeHandler
import io.foxcapades.mc.bukkit.thimble.util.B1
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

import org.bukkit.NamespacedKey
import org.bukkit.attribute.AttributeModifier
import org.bukkit.attribute.AttributeModifier.Operation
import org.bukkit.inventory.EquipmentSlotGroup


data object AttributeModifierTypeHandler : ComplexTypeHandler<AttributeModifier> {
  override val actualType     get() = AttributeModifier::class.java
  override val typeIndicator  get() = "b:a:AM"
  override val currentVersion get() = B1

  override fun serialize(value: AttributeModifier, writer: ValueWriter) {
    writer.writeString(value.key.toString())
    writer.writeDouble(value.amount)
    writer.writeInt(value.operation.ordinal)
    writer.writeString(value.slotGroup.toString())
  }

  override fun deserializerFor(version: Byte): ComplexDeserializer<out AttributeModifier>? =
    when (version) {
      B1   -> AttributeModifierDeserializerV1()
      else -> null
    }
}

private class AttributeModifierDeserializerV1 : ComplexDeserializer<AttributeModifier> {
  /**
   * Index `0`
   */
  private var namespacedKey = ""

  /**
   * Index `1`
   */
  private var amount = 0.0

  /**
   * Index `2`
   *
   * Operation enum value index.
   *
   * Options as of 1.21.1 are:
   * ```
   * 0 = ADD_NUMBER
   * 1 = ADD_SCALAR
   * 2 = MULTIPLY_SCALAR_1
   * ```
   */
  private var operation = 0

  /**
   * Index `3`
   *
   * Name of the [org.bukkit.inventory.EquipmentSlotGroup] predicate.
   *
   * As of 1.21.1, the options are:
   * * `any`
   * * `mainhand`
   * * `offhand`
   * * `hand`
   * * `feet`
   * * `legs`
   * * `chest`
   * * `head`
   * * `armor`
   */
  private var slotGroup = ""

  override fun append(index: Int, value: ValueAccessor) {
    when (index) {
      0    -> namespacedKey = value.asString()
      1    -> amount = value.asDouble()
      2    -> operation = value.asInt()
      3    -> slotGroup = value.asString()
      else -> throw ThimbleDeserializationException("invalid value index: $index")
    }
  }

  override fun build(): AttributeModifier =
    AttributeModifier(
      NamespacedKey.fromString(namespacedKey)!!,
      amount,
      Operation.entries[0],
      EquipmentSlotGroup.getByName(slotGroup)!!,
    )


}
