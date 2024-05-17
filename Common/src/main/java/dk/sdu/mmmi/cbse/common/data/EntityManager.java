package dk.sdu.mmmi.cbse.common.data;

import dk.sdu.mmmi.cbse.common.interfaces.Entity;

import java.util.*;

public class EntityManager {

    private final Collection<Entity> entities = new HashSet<>();

    public boolean addEntity(final Entity entity) {
        return this.entities.add(entity);
    }

    public boolean removeEntity(final Entity entity) {
        return this.entities.remove(entity);
    }

    public Collection<Entity> getAllEntities() {
        return Collections.unmodifiableCollection(this.entities);
    }

    public <E extends Entity> Collection<E> getAllEntitiesByClass(Class<E>... entityTypes) {
        Collection<E> tempList = new HashSet<>();
        this.getAllEntities().forEach(entity -> {
            for (Class<E> entityType : entityTypes) {
                if (entityType.isInstance(entity)) tempList.add(entityType.cast(entity));
            }
        });
        return Collections.unmodifiableCollection(tempList);
    }
}
