package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * This interface defines the necessary contract for processing entities
 * within a game world. It is expected to be implemented by services that handle
 * game logic updates for entities based on the game's current state.
 */
public interface IEntityProcessingService {

    /**
     * Processes all relevant game entities based on the current game data and the state of the world.
     * This method updates the state of the game world and its entities based on game logic,
     * which may include moving entities, handling collisions, updating health stats, etc.
     *
     *
     <p><strong>Preconditions:</strong></p>
     * <ul>
     *     <li>{@code gameData} must not be null and should represent the current state of the game (e.g., time elapsed).</li>
     *     <li>{@code world} must not be null and should contain all the entities that need to be processed.</li>
     * </ul>
     *
     * <p><strong>Postconditions:</strong></p>
     * <ul>
     *     <li>The state of {@code world} and its entities may be modified to reflect the results of processing based on {@code gameData}.</li>
     * </ul>
     *
     *
     * @param gameData The current state of the game, providing context such as game window and key configurations.
     * @param world The game world, containing all entities that may need updating or processing.
     */
    void process(GameData gameData, World world);
}
