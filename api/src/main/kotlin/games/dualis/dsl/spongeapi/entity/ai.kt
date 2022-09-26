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

package games.dualis.dsl.spongeapi.entity

import org.spongepowered.api.entity.ai.goal.builtin.LookAtGoal
import org.spongepowered.api.entity.ai.goal.builtin.LookRandomlyGoal
import org.spongepowered.api.entity.ai.goal.builtin.SwimGoal
import org.spongepowered.api.entity.living.Agent

/**
 * Inlined function that applies [block] to a new [LookAtGoal.builder].
 */
inline fun Agent.lookAtGoal(crossinline block: LookAtGoal.Builder.() -> Unit) =
    LookAtGoal.builder()
        .apply(block)
        .build(this)

/**
 * Inlined function that applies [block] to a new [LookRandomlyGoal.builder].
 */
inline fun Agent.lookRandomlyGoal(crossinline block: LookRandomlyGoal.Builder.() -> Unit) =
    LookRandomlyGoal.builder()
        .apply(block)
        .build(this)

/**
 * Inlined function that applies [block] to a new [SwimGoal.builder].
 */
inline fun Agent.swimGoal(crossinline block: SwimGoal.Builder.() -> Unit) =
    SwimGoal.builder()
        .apply(block)
        .build(this)
