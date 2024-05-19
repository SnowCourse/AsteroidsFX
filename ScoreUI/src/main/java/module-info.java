import dk.sdu.mmmi.cbse.score.ScoreUI;
import dk.sdu.mmmi.cbse.common.services.IUIProcessingService;

module ScoreUI {
    requires Common;
    requires javafx.graphics;
    requires java.net.http;
    provides IUIProcessingService with ScoreUI;
}