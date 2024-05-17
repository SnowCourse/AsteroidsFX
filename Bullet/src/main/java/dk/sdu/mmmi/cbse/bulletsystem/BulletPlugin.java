package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.CommonEntity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.EntityManager;
import dk.sdu.mmmi.cbse.common.interfaces.Entity;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class BulletPlugin implements IGamePluginService {

    @Override
    public void start(GameData gameData, EntityManager entityManager) {

    }

    @Override
    public void stop(GameData gameData, EntityManager entityManager) {
        for (Entity bullet : entityManager.getAllEntitiesByClass(Bullet.class)) {
            entityManager.removeEntity(bullet);
        }
    }

}
