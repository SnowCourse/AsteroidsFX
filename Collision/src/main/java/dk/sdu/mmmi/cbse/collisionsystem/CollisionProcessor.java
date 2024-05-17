package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.interfaces.CollidableEntity;
import dk.sdu.mmmi.cbse.common.interfaces.Entity;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.EntityManager;

public class CollisionProcessor implements IPostEntityProcessingService {

    @Override
    public void process(GameData gameData, EntityManager entityManager) {
        // two for loops for all entities in the world
        for (Entity entity1 : entityManager.getAllEntities()) {
            if (entity1 instanceof CollidableEntity collidableEntity1) {
                for (Entity entity2 : entityManager.getAllEntities()) {
                    if (entity2 instanceof CollidableEntity collidableEntity2) {
                        // if the two entities are identical, skip the iteration
                        if (collidableEntity1.equals(collidableEntity2)) {
                            continue;
                        }

                        // CollisionDetection
                        if (this.collides(entity1, entity2)) {
                            collidableEntity1.Collide(collidableEntity2, entityManager);
                            collidableEntity2.Collide(collidableEntity1, entityManager);
                            return;
                        }
                    }
                }
            }
        }
    }

    public Boolean collides(Entity entity1, Entity entity2) {
        float dx = (float) entity1.getX() - (float) entity2.getX();
        float dy = (float) entity1.getY() - (float) entity2.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (entity1.getRadius() + entity2.getRadius());
    }
}
