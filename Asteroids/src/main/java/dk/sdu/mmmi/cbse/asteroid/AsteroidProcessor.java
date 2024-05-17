package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.data.CommonEntity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.EntityManager;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Random;

public class AsteroidProcessor implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, EntityManager entityManager) {
        if (entityManager.getAllEntitiesByClass(Asteroid.class).size() < 5) {
            Random rand = new Random();
            CommonEntity newAsteroid = new SplittingAsteroid(3, rand.nextInt(20,30));

            newAsteroid.setX(rand.nextInt(100));
            newAsteroid.setY(rand.nextInt(100));
            newAsteroid.setRotation(rand.nextInt(360));

            entityManager.addEntity(newAsteroid);
        }


        for (CommonEntity asteroid : entityManager.getAllEntitiesByClass(Asteroid.class)) {
            double changeX = Math.cos(Math.toRadians(asteroid.getRotation()));
            double changeY = Math.sin(Math.toRadians(asteroid.getRotation()));

            asteroid.setX(asteroid.getX() + changeX * 0.5);
            asteroid.setY(asteroid.getY() + changeY * 0.5);

            if (asteroid.getX() < 0) {
                asteroid.setX(gameData.getDisplayWidth());
            }

            if (asteroid.getX() > gameData.getDisplayWidth()) {
                asteroid.setX(0);
            }

            if (asteroid.getY() < 0) {
                asteroid.setY(gameData.getDisplayHeight());
            }

            if (asteroid.getY() > gameData.getDisplayHeight()) {
                asteroid.setY(0);
            }

        }

    }
}
