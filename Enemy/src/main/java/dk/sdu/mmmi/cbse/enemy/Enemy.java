package dk.sdu.mmmi.cbse.enemy;

import dk.sdu.mmmi.cbse.common.data.CommonEntity;
import dk.sdu.mmmi.cbse.common.data.EntityManager;
import dk.sdu.mmmi.cbse.common.interfaces.AttackingEntity;
import dk.sdu.mmmi.cbse.common.interfaces.CollidableEntity;

public class Enemy extends CommonEntity implements CollidableEntity {
    private double health = 3;

    public double getHealth() {
        return this.health;
    }

    long timeOfLastShot;
    long shootingCooldown = 300_000_000;

    long timeOfLastDecision;
    long timeOfDecision = 3_000;

    public boolean canShoot(){
        long currentTime = System.nanoTime();
        return currentTime - timeOfLastShot >= shootingCooldown;
    }

    public void shoot() {
        timeOfLastShot = System.nanoTime();
    }

    public Enemy() {
        this.setPolygonCoordinates(-7, -7, 10, -3, 10, 3, -7, 7);
        this.setRadius(10);
    }

    public boolean timeForNewDecision() {
        long currentTime = System.currentTimeMillis();
        return currentTime - timeOfLastDecision >= timeOfDecision;
    }

    public void newDecision(long timeOfDecision) {
        this.timeOfLastDecision = System.currentTimeMillis();
        this.timeOfDecision = timeOfDecision;
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
