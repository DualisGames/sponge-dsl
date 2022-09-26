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

package games.dualis.dsl.spongeapi

import org.spongepowered.api.entity.EntityArchetype
import org.spongepowered.api.entity.EntitySnapshot

/**
 * Inlined function that applies [block] to a new [EntityArchetype.builder].
 */
inline fun entityArchetype(crossinline block: EntityArchetype.Builder.() -> Unit) =
    EntityArchetype.builder()
        .apply(block)
        .build()

/**
 * Inlined function that applies [block] to a new [EntitySnapshot.builder].
 */
inline fun entitySnapshot(crossinline block: EntitySnapshot.Builder.() -> Unit) =
    EntitySnapshot.builder()
        .apply(block)
        .build()
