package dk.sdu.mmmi.cbse.common.bullet;

import dk.sdu.mmmi.cbse.common.data.CommonEntity;
import dk.sdu.mmmi.cbse.common.data.EntityManager;
import dk.sdu.mmmi.cbse.common.interfaces.AttackingEntity;
import dk.sdu.mmmi.cbse.common.interfaces.CollidableEntity;

public class Bullet extends CommonEntity implements CollidableEntity, AttackingEntity {

    @Override
    public void Collide(CollidableEntity otherEntity, EntityManager entityManager) {
        entityManager.removeEntity(this);
    }

    @Override
    public double getDamage() {
        return 1.0;
    }
}
