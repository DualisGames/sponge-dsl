/*
 * sponge-dsl, a Kotlin DSL for the SpongePowered/SpongeAPI project.
 * Copyright (C) 2022  Dualis Games
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

@file:Suppress("UNCHECKED_CAST")

package games.dualis.dsl.spongeapi.command

import org.jetbrains.annotations.ApiStatus
import org.spongepowered.api.command.Command
import org.spongepowered.api.command.parameter.Parameter
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * A class used for syntax purposes.
 */
@ApiStatus.Internal
class KCommand(private val command: Command.Builder) : Command.Builder by command {

    /**
     * Stores the values for each parameter.
     */
    internal val dslParameters = mutableMapOf<Parameter.Key<*>, Any?>()

    /**
     * Provides delegations for non-nullable command [Parameter]s.
     */
    operator fun <T> Parameter.Value.Builder<T>.provideDelegate(
        _this: Any?, property: KProperty<*>
    ): ReadOnlyProperty<Any?, T> {
        val parameter = key(property.name)
            .build()
            .also(command::addParameter)
            .also { p -> dslParameters[p.key()] = null }
        return ReadOnlyProperty { _, _ -> dslParameters[parameter.key()] as T }
    }

    /**
     * Provides delegations for nullable command [Parameter]s.
     */
    operator fun <T> KNullableParameter<T>.provideDelegate(
        _this: Any?, property: KProperty<*>
    ): ReadOnlyProperty<Any?, T?> {
        val parameter = key(property.name)
            .build()
            .also(command::addParameter)
            .also { p -> dslParameters[p.key()] = null }
        return ReadOnlyProperty { _, _ -> dslParameters[parameter.key()] as T? }
    }

    /**
     * Registers given [cmd] with [this] [String] as an alias.
     */
    infix fun String.runs(cmd: Command.Parameterized) = addChild(cmd, this)

    /**
     * Registers given [cmd] with [this] array of [String] as aliases.
     */
    infix fun Array<String>.runs(cmd: Command.Parameterized) =
        addChild(cmd, this[0], *this.drop(1).toTypedArray())

    /**
     * Registers given [cmd] with [this] list of [String] as aliases.
     */
    infix fun List<String>.runs(cmd: Command.Parameterized) =
        addChild(cmd, this[0], *this.drop(1).toTypedArray())

}

/**
 * Builds a new [KCommand] using given [block].
 */
inline fun command(crossinline block: KCommand.() -> Unit) =
    KCommand(Command.builder())
        .apply(block)
        .build()
