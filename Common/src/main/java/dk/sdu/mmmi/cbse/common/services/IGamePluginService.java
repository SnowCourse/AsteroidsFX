package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.EntityManager;

/**
 * This interface defines the contract for lifecycle management of game plugins.
 * It provides methods to start and stop game plugins, facilitating the setup and cleanup
 * of resources associated with them. Implementations should ensure that plugins can
 * be seamlessly integrated and removed from the game environment.
 */
public interface IGamePluginService {

    /**
     * Initializes the plugin, setting up necessary game data and resources in the world.
     * This method is called when a plugin is loaded into the game environment, and is responsible
     * for preparing the game world and data structures required by the plugin.
     *
     * <p><strong>Preconditions:</strong></p>
     * <ul>
     *     <li>{@code gameData} must not be null and should provide the necessary context for initializing the plugin.</li>
     *     <li>{@code world} must not be null and should be ready to accept new entities or modifications required by the plugin.</li>
     * </ul>
     *
     * <p><strong>Postconditions:</strong></p>
     * <ul>
     *     <li>Plugin-specific entities, systems, or resources are added to {@code world} and are ready for game updates.</li>
     *     <li>{@code gameData} may be modified to reflect new game settings or configurations introduced by the plugin.</li>
     * </ul>
     *
     * @param gameData The game data containing settings and state information needed to initialize the plugin.
     * @param entityManager The game world where the plugin will operate.
     */
    void start(GameData gameData, EntityManager entityManager);

    /**
     * Cleans up the plugin by removing resources, entities, or other modifications made to the game world.
     * This method is called when a plugin is being unloaded from the game environment, ensuring that all
     * resources allocated by the plugin are properly disposed of and that the game world remains consistent.
     *
     * <p><strong>Preconditions:</strong></p>
     * <ul>
     *     <li>{@code gameData} must not be null and should reflect the current state of the game for proper cleanup.</li>
     *     <li>{@code world} must not be null and should contain entities or systems introduced by the plugin that need removal.</li>
     * </ul>
     *
     * <p><strong>Postconditions:</strong></p>
     * <ul>
     *     <li>All entities, systems, and resources introduced by the plugin are removed or reverted, maintaining the integrity of the game world.</li>
     *     <li>{@code gameData} is updated to remove any configurations or settings specific to the plugin.</li>
     * </ul>
     *
     * @param gameData The game data containing current settings and state information that may need modification.
     * @param entityManager The game world from which the plugin's effects will be removed.
     * @throws IllegalArgumentException if either {@code gameData} or {@code world} is null.
     */
    void stop(GameData gameData, EntityManager entityManager);
}
