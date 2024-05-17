package dk.sdu.mmmi.cbse.common.interfaces;

import dk.sdu.mmmi.cbse.common.data.EntityManager;

public interface CollidableEntity extends Entity{

    public void Collide(CollidableEntity otherEntity, EntityManager entityManager);
}
