package dk.sdu.mmmi.cbse;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.CommonEntity;
import dk.sdu.mmmi.cbse.common.data.EntityManager;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.Random;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class EnemyControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, EntityManager entityManager) {
        // adding enemies
        if (entityManager.getAllEntitiesByClass(Enemy.class).size() < 3) {
            Random random = new Random();
            CommonEntity newEnemy = new Enemy();
            newEnemy.setX(random.nextInt(gameData.getDisplayWidth()));
            newEnemy.setY(random.nextInt(gameData.getDisplayHeight()));
            entityManager.addEntity(newEnemy);
        }

        // enemy movement
        for (Enemy enemy : entityManager.getAllEntitiesByClass(Enemy.class)) {
            Random random = new Random();
            double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
            double changeY = Math.sin(Math.toRadians(enemy.getRotation()));

            enemy.setX(enemy.getX() + changeX * 0.5);
            enemy.setY(enemy.getY() + changeY * 0.5);

            if (enemy.timeForNewDecision()) {
                enemy.setRotation(enemy.getRotation() + random.nextInt(-90, 90));
                enemy.newDecision(random.nextLong(2_000, 7_000));
            }

            // if enemy reaches border
            if (enemy.getX() < 0) {
                enemy.setX(gameData.getDisplayWidth());
            }

            if (enemy.getX() > gameData.getDisplayWidth()) {
                enemy.setX(0);
            }

            if (enemy.getY() < 0) {
                enemy.setY(gameData.getDisplayHeight());
            }

            if (enemy.getY() > gameData.getDisplayHeight()) {
                enemy.setY(0);
            }

            // shooting
            if (enemy.canShoot()) {
                getBulletSPIs().stream().findFirst().ifPresent(
                        bulletSPI -> {
                            entityManager.addEntity(bulletSPI.createBullet(enemy, gameData));
                            enemy.shoot();
                        }
                );
            }

        }

    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

}
