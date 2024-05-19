package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.EntityManager;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerMovementTest {
    private final PlayerControlSystem playerControlSystem = new PlayerControlSystem();

    private final GameData gameData = new GameData();

    private final EntityManager entityManager = new EntityManager();

    private final Player player = new Player();
    private double playerLastRotation;
    private double playerLastXPosition;
    private double playerLastYPosition;

    @BeforeEach
    void setUp() {
        this.gameData.setDisplayHeight(300);
        this.gameData.setDisplayWidth(300);

        this.player.setY(gameData.getDisplayHeight() / 2.0);
        this.player.setX(gameData.getDisplayWidth() / 2.0);

        this.playerLastRotation = player.getRotation();
        this.playerLastXPosition = player.getX();
        this.playerLastYPosition = player.getY();

        this.entityManager.addEntity(player);
    }

    @Test
    void LeftRotation() {
        this.gameData.getKeys().setKey(GameKeys.LEFT, true);
        assertTrue(this.gameData.getKeys().isDown(GameKeys.LEFT));

        // check for rotation
        this.playerControlSystem.process(this.gameData, this.entityManager);
        assertTrue(this.player.getRotation() < playerLastRotation);

        this.gameData.getKeys().setKey(GameKeys.LEFT, false);
        assertFalse(this.gameData.getKeys().isDown(GameKeys.LEFT));
    }

    @Test
    void RightRotation() {
        this.gameData.getKeys().setKey(GameKeys.RIGHT, true);
        assertTrue(this.gameData.getKeys().isDown(GameKeys.RIGHT));

        // check for rotation
        this.playerControlSystem.process(this.gameData, this.entityManager);
        assertTrue(this.player.getRotation() > playerLastRotation);

        this.gameData.getKeys().setKey(GameKeys.RIGHT, false);
        assertFalse(this.gameData.getKeys().isDown(GameKeys.RIGHT));
    }

    @Test
    void Movement() {
        this.gameData.getKeys().setKey(GameKeys.UP, true);
        assertTrue(this.gameData.getKeys().isDown(GameKeys.UP));

        // moving right
        this.player.setRotation(0);
        this.playerControlSystem.process(this.gameData, this.entityManager);
        assertTrue(this.player.getX() > playerLastXPosition);
        this.playerLastXPosition = this.player.getX();
        this.playerLastYPosition = this.player.getY();

        // moving down
        this.player.setRotation(90);
        this.playerControlSystem.process(this.gameData, this.entityManager);
        assertTrue(this.player.getY() > playerLastYPosition);
        this.playerLastXPosition = this.player.getX();
        this.playerLastYPosition = this.player.getY();

        // moving left
        this.player.setRotation(180);
        this.playerControlSystem.process(this.gameData, this.entityManager);
        assertTrue(this.player.getX() < playerLastXPosition);
        this.playerLastXPosition = this.player.getX();
        this.playerLastYPosition = this.player.getY();

        // moving up
        this.player.setRotation(270);
        this.playerControlSystem.process(this.gameData, this.entityManager);
        assertTrue(this.player.getY() < playerLastYPosition);
        this.playerLastXPosition = this.player.getX();
        this.playerLastYPosition = this.player.getY();


        this.gameData.getKeys().setKey(GameKeys.UP, false);
        assertFalse(this.gameData.getKeys().isDown(GameKeys.UP));
    }

    @Test
    void border() {
        // right border
        this.player.setX(this.gameData.getDisplayWidth() + 10);
        this.playerControlSystem.process(this.gameData, this.entityManager);
        assertTrue(this.player.getX() <= gameData.getDisplayWidth());

        // bottom border
        this.player.setY(this.gameData.getDisplayHeight() + 10);
        this.playerControlSystem.process(this.gameData, this.entityManager);
        assertTrue(this.player.getY() <= gameData.getDisplayHeight());

        // left border
        this.player.setX(-10);
        this.playerControlSystem.process(this.gameData, this.entityManager);
        assertTrue(this.player.getX() >= 0);

        // top border
        this.player.setY(-10);
        this.playerControlSystem.process(this.gameData, this.entityManager);
        assertTrue(this.player.getY() >= 0);

    }
}