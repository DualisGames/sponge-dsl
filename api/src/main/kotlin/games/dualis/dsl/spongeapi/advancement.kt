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

import org.spongepowered.api.advancement.Advancement
import org.spongepowered.api.advancement.DisplayInfo
import org.spongepowered.api.advancement.criteria.AdvancementCriterion

/**
 * Inlined function that applies [block] to a new [Advancement.builder].
 */
inline fun advancement(crossinline block: Advancement.Builder.() -> Unit) =
    Advancement.builder()
        .apply(block)
        .build()

/**
 * Inlined function that applies [block] to a new [DisplayInfo.builder].
 */
inline fun Advancement.Builder.display(crossinline block: DisplayInfo.Builder.() -> Unit) =
    DisplayInfo.builder()
        .apply(block)
        .build()
        .apply { displayInfo(this) }

/**
 * Inlined function that applies [block] to a new [DisplayInfo.builder].
 */
inline fun Advancement.Builder.criterion(crossinline block: AdvancementCriterion.Builder.() -> Unit) =
    AdvancementCriterion.builder()
        .apply(block)
        .build()
        .apply { criterion(this) }
