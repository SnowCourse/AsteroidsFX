package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.data.CommonEntity;
import dk.sdu.mmmi.cbse.common.data.EntityManager;
import dk.sdu.mmmi.cbse.common.interfaces.CollidableEntity;

public class SplittingAsteroid extends Asteroid implements CollidableEntity {

    private final int numberOfChildAsteroids;

    public SplittingAsteroid(int numberOfChildAsteroids, float size) {
        if (numberOfChildAsteroids < 1) throw new IllegalArgumentException("numberOfChildAsteroids must be greater than 0");
        this.numberOfChildAsteroids = numberOfChildAsteroids;
        this.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
        this.setRadius(size);
    }

    @Override
    public void Collide(CollidableEntity otherEntity, EntityManager entityManager) {
        if (this.getRadius() > 10) {

            float newSize = this.getRadius() / 2;
            double angleIncrement = 360.0 / numberOfChildAsteroids;

            for (int i = 0; i < numberOfChildAsteroids; i++) {
                double angleDegrees = this.getRotation() + i * angleIncrement;
                double angleRadians = Math.toRadians(angleDegrees);

                CommonEntity newAsteroid = new SplittingAsteroid(numberOfChildAsteroids, newSize);

                newAsteroid.setX(this.getX() + Math.cos(angleRadians) * newSize * 2);
                newAsteroid.setY(this.getY() + Math.sin(angleRadians) * newSize * 2);
                newAsteroid.setRotation(angleDegrees);

                entityManager.addEntity(newAsteroid);
            }
        }
        entityManager.removeEntity(this);
    }
}
