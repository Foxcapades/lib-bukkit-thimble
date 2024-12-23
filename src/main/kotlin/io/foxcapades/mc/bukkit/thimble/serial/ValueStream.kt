package io.foxcapades.mc.bukkit.thimble.serial

import java.math.BigDecimal
import java.math.BigInteger

interface ValueStream {
  fun writeNull()

  fun writeBoolean(value: Boolean)

  fun writeByte(value: Byte)

  fun writeShort(value: Short)

  fun writeInt(value: Int)

  fun writeLong(value: Long)

  fun writeFloat(value: Float)

  fun writeDouble(value: Double)

  fun writeString(value: String)

  fun writeBigInt(value: BigInteger)

  fun writeBigDec(value: BigDecimal)
}


internal class ValueStreamImpl : ValueStream {
  override fun writeNull() {
    TODO("Not yet implemented")
  }

  override fun writeBoolean(value: Boolean) {
    TODO("Not yet implemented")
  }

  override fun writeByte(value: Byte) {
    TODO("Not yet implemented")
  }

  override fun writeShort(value: Short) {
    TODO("Not yet implemented")
  }

  override fun writeInt(value: Int) {
    TODO("Not yet implemented")
  }

  override fun writeLong(value: Long) {
    TODO("Not yet implemented")
  }

  override fun writeFloat(value: Float) {
    TODO("Not yet implemented")
  }

  override fun writeDouble(value: Double) {
    TODO("Not yet implemented")
  }

  override fun writeString(value: String) {
    TODO("Not yet implemented")
  }

  override fun writeBigInt(value: BigInteger) {
    TODO("Not yet implemented")
  }

  override fun writeBigDec(value: BigDecimal) {
    TODO("Not yet implemented")
  }

}
