package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.CommonEntity;
import dk.sdu.mmmi.cbse.common.data.EntityManager;
import dk.sdu.mmmi.cbse.common.interfaces.AttackingEntity;
import dk.sdu.mmmi.cbse.common.interfaces.CollidableEntity;

public class Player extends CommonEntity implements CollidableEntity {
    private double health = 3;

    public double getHealth() {
        return this.health;
    }

    @Override
    public void Collide(CollidableEntity otherEntity, EntityManager entityManager) {
        if (otherEntity instanceof AttackingEntity) {
            double damageTaken = ((AttackingEntity) otherEntity).getDamage();
            this.health -= damageTaken;
            if (this.health <= 0) {
                this.health = 0;
                entityManager.removeEntity(this);
            }
        } else {
            entityManager.removeEntity(this);
        }
    }
}
