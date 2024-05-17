import dk.sdu.mmmi.cbse.EnemyControlSystem;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

module Enemy {
    requires Common;
    requires CommonBullet;
    uses dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
    provides IEntityProcessingService with EnemyControlSystem;
}