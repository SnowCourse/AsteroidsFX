import dk.sdu.mmmi.cbse.collisionsystem.CollisionProcessor;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Collision {
    requires Common;
    provides IPostEntityProcessingService with CollisionProcessor;
}