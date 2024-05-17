package dk.sdu.mmmi.cbse;

import dk.sdu.mmmi.cbse.common.data.EntityManager;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class EntityProcessor implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, EntityManager entityManager) {
        System.out.println("EntityProcessor from Player");
    }
}
