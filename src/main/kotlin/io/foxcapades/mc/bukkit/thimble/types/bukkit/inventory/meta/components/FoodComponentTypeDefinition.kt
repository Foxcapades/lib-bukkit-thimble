package io.foxcapades.mc.bukkit.thimble.types.bukkit.inventory.meta.components

import io.foxcapades.mc.bukkit.thimble.hax.meta.FoodComponent
import io.foxcapades.mc.bukkit.thimble.parse.ComplexDeserializer
import io.foxcapades.mc.bukkit.thimble.parse.ThimbleDeserializationException
import io.foxcapades.mc.bukkit.thimble.read.ValueAccessor
import io.foxcapades.mc.bukkit.thimble.types.ComplexTypeDefinition
import io.foxcapades.mc.bukkit.thimble.util.B1
import io.foxcapades.mc.bukkit.thimble.write.ValueWriter

import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.components.FoodComponent
import org.bukkit.inventory.meta.components.FoodComponent.FoodEffect


@Suppress("UnstableApiUsage")
open class FoodComponentTypeDefinition : ComplexTypeDefinition<FoodComponent> {
  override val actualType     get() = FoodComponent::class.java
  override val typeIdentifier get() = "b:m:FC"
  override val currentVersion get() = B1

  override fun serialize(value: FoodComponent, writer: ValueWriter) {
    writer.writeInt(value.nutrition)
    writer.writeFloat(value.saturation)
    writer.writeBoolean(value.canAlwaysEat())
    writer.writeFloat(value.eatSeconds)

    if (value.usingConvertsTo != null)
      writer.writeComplex(value.usingConvertsTo!!)
    else
      writer.writeNull()

    if (value.effects.isNotEmpty())
      writer.writeComplex(value.effects)
    else
      writer.writeNull()
  }

  override fun deserializerFor(version: Byte): ComplexDeserializer<out FoodComponent>? =
    when (version) {
      B1 -> object : ComplexDeserializer<FoodComponent> {
        private var nutrition = 0
        private var saturation = 0f
        private var canAlwaysEat = false
        private var eatSeconds = 0f
        private var convertsTo: ItemStack? = null
        private var effects: List<FoodEffect>? = null

        override fun append(index: Int, value: ValueAccessor) {
          @Suppress("UNCHECKED_CAST")
          when (index) {
            0 -> nutrition = value.asInt()
            1 -> saturation = value.asFloat()
            2 -> canAlwaysEat = value.asBoolean()
            3 -> eatSeconds = value.asFloat()
            4 -> if (!value.isNull) convertsTo = value.asComplex().asType(ItemStack::class.java)
            5 -> if (!value.isNull) effects = value.asComplex().asType(List::class.java) as List<FoodEffect>
            else -> throw ThimbleDeserializationException("invalid value index: $index")
          }
        }

        override fun build(): FoodComponent = FoodComponent(
          nutrition,
          saturation,
          canAlwaysEat,
          eatSeconds,
          convertsTo,
          effects
        )
      }

      else -> null
    }
}
