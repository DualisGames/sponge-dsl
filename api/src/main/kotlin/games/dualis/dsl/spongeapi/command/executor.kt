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

import org.jetbrains.annotations.ApiStatus
import org.spongepowered.api.command.CommandExecutor
import org.spongepowered.api.command.CommandResult
import org.spongepowered.api.command.parameter.CommandContext

/**
 * A class used for syntax purposes.
 */
@ApiStatus.Internal
abstract class KCommandExecutor(private val command: KCommand) : CommandExecutor {
    override fun execute(context: CommandContext): CommandResult {
        command.dslParameters.entries.forEach {
            it.setValue(context.one(it.key).get())
        }
        return CommandResult.success()
    }
}

/**
 * Builds a new [KCommandExecutor] using given [block].
 */
inline fun KCommand.execute(crossinline block: CommandContext.() -> CommandResult) {
    executor(object : KCommandExecutor(this) {
        override fun execute(context: CommandContext): CommandResult {
            super.execute(context)
            return block(context)
        }
    })
}
