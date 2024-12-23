package io.foxcapades.mc.bukkit.thimble.codecs.fields

import kotlin.reflect.KFunction1
import kotlin.reflect.KFunction2


internal typealias Getter<T, V> = KFunction1<T, V>
internal typealias Setter<T, V> = KFunction2<T, V, *>
