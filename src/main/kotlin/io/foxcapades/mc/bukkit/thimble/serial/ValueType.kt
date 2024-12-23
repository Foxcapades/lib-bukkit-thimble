package io.foxcapades.mc.bukkit.thimble.serial

@JvmInline
value class ValueType private constructor(val identifier: Int) {
  fun toByte(): Byte = identifier.toByte()

  companion object {
    /**
     * {A}
     *
     * A = Type Indicator (uint8)
     */
    val Null = ValueType(0)

    /**
     * {A}{B}
     *
     * A = Type Indicator (uint8)
     * B = Value (uint8)
     */
    val Boolean = ValueType(1)

    /**
     * {A}{B}
     *
     * A = Type Indicator (uint8)
     * B = Value (uint8)
     */
    val Byte = ValueType(2)

    /**
     * {A}{BB}
     *
     * A = Type Indicator (uint8)
     * B = Value (2x uint8)
     */
    val Short = ValueType(3)

    /**
     * {A}{BBBB}
     *
     * A = Type Indicator (uint8)
     * B = Value (4x uint8)
     */
    val Int = ValueType(4)

    /**
     * {A}{BBBBBBBB}
     *
     * A = Type Indicator
     * B = Value (8 bytes)
     */
    val Long = ValueType(5)

    /**
     * {A}{BBBB}
     *
     * A = Type Indicator
     * B = Value (4 bytes)
     */
    val Float = ValueType(6)

    /**
     * {A}{BBBBBBBB}
     *
     * A = Type Indicator
     * B = Value (8 bytes)
     */
    val Double = ValueType(7)

    /**
     * {A}{CC}[{D...}]
     *
     * A = Type Indicator (9)
     * B = Size (uint16)
     * C = Value
     */
    val String = ValueType(8)

    /**
     * {A}{BB}{C...}
     *
     * A = Type Indicator (14)
     * B = Data length in bytes (uint16)
     * C = Value Data
     */
    val BigInt = ValueType(9)

    /**
     * {A}{BB}{CCCC}{D...}
     *
     * A = Type Indicator (15)
     * B = Data length in bytes (uint16)
     * C = Scale value (int32)
     * D = Value Data
     */
    val BigFloat = ValueType(10)

    /**
     * {A}{BB}{CC}{D...}[{E...}...]
     *
     * A = Type indicator (10)
     * B = Version indicator (uint16)
     * C = Size (uint16)
     * D = Contained type
     * E = Value (0 or more)
     */
    val Sequence = ValueType(11)

    /**
     * {A}{BB}{CC}{D...}{E...}[({F...}{G...})...]
     *
     * A = Type Indicator (11)
     * B = Version Indicator (int16)
     * C = Size (int16)
     * D = Key Type
     * E = Value Type
     * F = Key
     * G = Value
     */
    val DictSequence = ValueType(12)

    /**
     * {A}{B}{C...}{DDDD}[{E...}]
     *
     * A = Type Indicator (12)
     * B = Size of Complex type ID   // TODO: it should be up to the complex type to determine whether size should be written.
     * C = Complex type ID
     * D = Size of complex data block (uint32)
     * E = Type specific data.
     */
    val Complex = ValueType(13)

    /**
     * {A}{B}[({C}{D...})...]
     *
     * A = Type Indicator (13)
     * B = Combined type count (uint8)
     * C = Contained value type indicator (uint8)
     * D = Contained value data (type specific)
     */
    val Compound = ValueType(14)

    val Unknown = ValueType(15)
  }
}
