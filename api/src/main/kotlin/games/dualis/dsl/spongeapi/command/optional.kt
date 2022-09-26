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

@file:Suppress("NOTHING_TO_INLINE")

package games.dualis.dsl.spongeapi.command

import org.jetbrains.annotations.ApiStatus
import org.spongepowered.api.command.parameter.Parameter

/**
 * A class used for syntax purposes.
 */
@ApiStatus.Internal
data class KNullableParameter<T>(val builder: Parameter.Value.Builder<T>) :
    Parameter.Value.Builder<T> by builder {

    init {
        builder.optional()
    }
}

/**
 * Marks given [Parameter.Value.Builder] as `nullable` meaning the value is optional and the delegation
 * will actually return a nullable value.
 */
inline fun <T> Parameter.Value.Builder<T>.nullable() = KNullableParameter(this)
