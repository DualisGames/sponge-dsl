package games.dualis.dsl.spongeapi

import org.spongepowered.configurate.hocon.HoconConfigurationLoader
import org.spongepowered.configurate.kotlin.dataClassFieldDiscoverer
import org.spongepowered.configurate.objectmapping.ObjectMapper
import org.spongepowered.configurate.yaml.YamlConfigurationLoader
import java.nio.file.Path

/**
 * Creates a [HoconConfigurationLoader] able to serialize/deserialize data classes.
 */
fun hoconLoader(source: Path) = HoconConfigurationLoader.builder()
    .path(source)
    .defaultOptions {
        it.serializers { s ->
            s.registerAnnotatedObjects(
                ObjectMapper.factoryBuilder()
                    .addDiscoverer(dataClassFieldDiscoverer())
                    .build()
            )
        }
    }
    .build()

/**
 * Creates a [YamlConfigurationLoader] able to serialize/deserialize data classes.
 */
fun yamlLoader(source: Path) = YamlConfigurationLoader.builder()
    .path(source)
    .defaultOptions {
        it.serializers { s ->
            s.registerAnnotatedObjects(
                ObjectMapper.factoryBuilder()
                    .addDiscoverer(dataClassFieldDiscoverer())
                    .build()
            )
        }
    }
    .build()


