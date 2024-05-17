package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.EntityManager;

/**
 * This interface defines the contract for post-processing entities within a game world.
 * Implementations are expected to handle tasks that finalize the states of entities after
 * the primary entity processing has occurred. This may include tasks such as state synchronization,
 * clean-up operations, or updating user interface components based on the new state of the game.
 */
public interface IPostEntityProcessingService {

    /**
     * Performs final processing of game entities and the world state after initial entity updates have been completed.
     * This method is typically called after all {@link IEntityProcessingService} implementations have run.
     * It allows for any necessary adjustments or additional processing that should not interfere with the primary
     * game logic, such as preparing data for the next update tick, or cleaning up temporary states.
     *
     * <p><strong>Preconditions:</strong></p>
     * <ul>
     *     <li>{@code gameData} must not be null and should accurately reflect the state of the game after initial processing.</li>
     *     <li>{@code world} must not be null and should contain the current state of all entities post-initial processing.</li>
     * </ul>
     *
     * <p><strong>Postconditions:</strong></p>
     * <ul>
     *     <li>The state of {@code world} and its entities may be further modified to finalize processing for the current game tick.</li>
     *     <li>Any clean-up or preparation for future game states or rounds should be completed.</li>
     * </ul>
     *
     * @param gameData The current state of the game, providing context such as game window and key configurations.
     * @param entityManager The game world, reflecting the state of all entities after the initial processing phase.
     */
    void process(GameData gameData, EntityManager entityManager);
}
