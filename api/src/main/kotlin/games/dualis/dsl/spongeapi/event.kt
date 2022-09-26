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

import io.leangen.geantyref.TypeToken
import org.spongepowered.api.event.Event
import org.spongepowered.api.event.EventListenerRegistration
import org.spongepowered.api.event.Order
import org.spongepowered.plugin.PluginContainer

/**
 * Returns a new [EventListenerRegistration] that runs [block].
 */
inline fun <reified T : Event> PluginContainer.listener(
    order: Order = Order.DEFAULT,
    beforeModifications: Boolean = true,
    crossinline block: T.() -> Unit
) = EventListenerRegistration.builder(object : TypeToken<T>() {})
    .plugin(this)
    .order(order)
    .beforeModifications(beforeModifications)
    .listener { e -> block(e) }
