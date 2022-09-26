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

package games.dualis.dsl.koin

import org.koin.core.module.Module
import org.koin.dsl.module
import org.spongepowered.api.*
import org.spongepowered.api.config.ConfigManager
import org.spongepowered.api.data.DataManager
import org.spongepowered.api.event.EventManager
import org.spongepowered.api.network.channel.ChannelManager
import org.spongepowered.api.plugin.PluginManager
import org.spongepowered.api.scheduler.Scheduler
import org.spongepowered.api.service.ServiceProvider
import org.spongepowered.api.sql.SqlManager
import org.spongepowered.api.util.metric.MetricsConfigManager
import org.spongepowered.plugin.PluginContainer

/**
 * Returns a Koin [Module] that injects basic managers/implementations of [Sponge].
 */
inline val PluginContainer.pluginKoinModule: Module
    get() = module {
        single { this@pluginKoinModule }
        single<Platform> { Sponge.platform() }
        single<DataManager> { Sponge.dataManager() }
        single<PluginManager> { Sponge.pluginManager() }
        single<EventManager> { Sponge.eventManager() }
        single<ConfigManager> { Sponge.configManager() }
        single<ChannelManager> { Sponge.channelManager() }
        single<Server> { Sponge.server() }
        single<Client> { Sponge.client() }
        single<SystemSubject> { Sponge.systemSubject() }
        single<MetricsConfigManager> { Sponge.metricsConfigManager() }
        single<Scheduler> { Sponge.asyncScheduler() }
        single<SqlManager> { Sponge.sqlManager() }
        single<ServiceProvider.GameScoped> { Sponge.serviceProvider() }
    }