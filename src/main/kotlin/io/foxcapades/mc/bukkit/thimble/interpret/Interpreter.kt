package io.foxcapades.mc.bukkit.thimble.interpret

import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import io.foxcapades.mc.bukkit.thimble.InvalidThimbleValueException
import java.io.Reader

private const val NOT_STARTED = 0
private const val WANT_HEADER = 1
private const val WANT_VALUES = 2

internal class Interpreter(stream: Reader) {
  private val stream = JsonReader(stream)
  private var next: JsonToken? = null
  private var depth = 0
  private var state = NOT_STARTED

  operator fun hasNext(): Boolean {
    if (next == null)
      next = stream.peek()

    return next != JsonToken.END_DOCUMENT
  }

  operator fun next(): Event {
    if (!hasNext())
      throw NoSuchElementException()

    return when (state) {
      WANT_VALUES -> {
        when (next!!) {
          JsonToken.BEGIN_ARRAY -> pop(TypeStartEvent()).also { state = WANT_HEADER }

          JsonToken.STRING -> pop(stream::nextString, ::StringEvent)

          JsonToken.NUMBER -> pop(stream::nextString, ::NumberEvent)

          JsonToken.BOOLEAN -> pop(stream::nextBoolean, ::BooleanEvent)

          JsonToken.NULL -> pop(stream::nextNull, ::NullEvent)

          JsonToken.END_ARRAY -> endComplex()

          else -> throw errUnexpectedToken(next!!, JsonToken.END_ARRAY,
            JsonToken.BEGIN_ARRAY, JsonToken.STRING, JsonToken.NUMBER,
            JsonToken.BOOLEAN, JsonToken.NULL)
        }
      }

      WANT_HEADER -> beginComplex().also { state = WANT_VALUES }

      NOT_STARTED -> pop(TypeStartEvent()).also { state = WANT_HEADER }

      else -> throw IllegalStateException()
    }
  }

  private fun beginStream(): Event {
    val value = when (next) {
      JsonToken.BEGIN_ARRAY -> TypeStartEvent().also { state = WANT_HEADER }
      JsonToken.STRING -> pop(stream::nextString, ::StringEvent)
      JsonToken.NUMBER -> pop(stream::nextString, ::NumberEvent)
      JsonToken.BOOLEAN -> pop(stream::nextBoolean, ::BooleanEvent)
      JsonToken.NULL -> pop(stream::nextNull, ::NullEvent)
      else -> throw errUnexpectedToken(next!!, JsonToken.BEGIN_ARRAY,
        JsonToken.STRING, JsonToken.NUMBER, JsonToken.BOOLEAN, JsonToken.NULL)
    }

    // If we aren't in a complex type, then require the next thing to be EOF
    if (value !is TypeStartEvent)
      requireEOF()

    return value
  }

  private fun beginComplex(): Event {
    stream.beginArray()
    depth++
    state = WANT_VALUES
    return HeaderEvent(
      stream.requireValue(JsonToken.STRING, JsonReader::nextString),
      try {
        stream.requireValue(JsonToken.NUMBER, JsonReader::nextInt)
      } catch (e: NumberFormatException) {
        throw InvalidThimbleValueException("invalid type version number: was not an int")
      }
    )
  }

  private fun endComplex(): Event {
    stream.endArray()
    depth--

    if (depth < 1)
      requireEOF()

    return TypeEndEvent()
  }

  private fun requireEOF() {
    next = when (val n = stream.peek()) {
      JsonToken.END_DOCUMENT -> n
      else -> throw errUnexpectedToken(n, JsonToken.END_DOCUMENT)
    }
  }

  @Suppress("NOTHING_TO_INLINE")
  private inline fun <R> pop(value: R) = value.also { next = null }

  private inline fun <T, R> pop(get: () -> T, map: (T) -> R): R {
    return map(get()).also { next = stream.peek() }
  }

  private inline fun <R> JsonReader.requireValue(type: JsonToken, fn: JsonReader.() -> R): R =
    when (val n = peek()) {
      type -> fn()
      else -> throw errUnexpectedToken(n, type)
    }

  private fun errUnexpectedToken(got: JsonToken, vararg wanted: JsonToken) =
    when (wanted.size) {
      1    -> "unexpected token; expected ${wanted[0]}, got $got"
      2    -> "unexpected token; expected ${wanted[0]} or ${wanted[1]}, got $got"
      else -> buildString(256) {
        append(wanted[0].name)
        for (i in 1 ..< wanted.lastIndex)
          append(", ", append(wanted[i].name))
        append(", or ").append(wanted.last().name)
      }
    }.let(::InvalidThimbleValueException)
}
