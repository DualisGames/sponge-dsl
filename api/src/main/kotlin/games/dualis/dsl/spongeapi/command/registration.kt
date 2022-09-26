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

package games.dualis.dsl.spongeapi.command

import games.dualis.dsl.spongeapi.listener
import org.jetbrains.annotations.ApiStatus
import org.spongepowered.api.command.Command
import org.spongepowered.api.event.lifecycle.RegisterCommandEvent
import org.spongepowered.plugin.PluginContainer

/**
 * A class used for syntax purposes.
 */
@ApiStatus.Internal
class KCommandRegistration(
    private val event: RegisterCommandEvent<Command.Parameterized>,
    private val container: PluginContainer
) {

    /**
     * Registers given [cmd] with [this] [String] as an alias.
     */
    infix fun String.runs(cmd: Command.Parameterized) = event.register(container, cmd, this)

    /**
     * Registers given [cmd] with [this] array of [String] as aliases.
     */
    infix fun Array<String>.runs(cmd: Command.Parameterized) =
        event.register(container, cmd, this[0], *this.drop(1).toTypedArray())

    /**
     * Registers given [cmd] with [this] list of [String] as aliases.
     */
    infix fun List<String>.runs(cmd: Command.Parameterized) =
        event.register(container, cmd, this[0], *this.drop(1).toTypedArray())

}


/**
 * A DSL syntax to register commands.
 */
inline fun commands(container: PluginContainer, crossinline block: KCommandRegistration.() -> Unit) =
    container.listener<RegisterCommandEvent<Command.Parameterized>> {
        block(KCommandRegistration(this, container))
    }
