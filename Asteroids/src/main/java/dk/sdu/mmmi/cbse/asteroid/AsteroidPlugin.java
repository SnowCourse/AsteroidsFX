package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.data.CommonEntity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.EntityManager;
import dk.sdu.mmmi.cbse.common.interfaces.Entity;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import java.util.Random;

public class AsteroidPlugin implements IGamePluginService {

    private int numberOfAsteroids = 5;

    @Override
    public void start(GameData gameData, EntityManager entityManager) {
        Random random = new Random();
        for (int i = 0; i < numberOfAsteroids; i++) {
            CommonEntity asteroid = new SplittingAsteroid(3, random.nextInt(20, 40));
            asteroid.setX(random.nextInt(100));
            asteroid.setY(random.nextInt(100));
            asteroid.setRotation(random.nextInt(360));
            entityManager.addEntity(asteroid);
        }
    }

    @Override
    public void stop(GameData gameData, EntityManager entityManager) {
        // Remove entities
        for (Entity asteroid : entityManager.getAllEntitiesByClass(Asteroid.class)) {
            entityManager.removeEntity(asteroid);
        }
    }
}
