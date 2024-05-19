package dk.sdu.mmmi.cbse.score;

import dk.sdu.mmmi.cbse.common.services.IUIProcessingService;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ScoreUI implements IUIProcessingService {
    private long score = 0;
    private final HttpClient httpClient = HttpClient.newHttpClient();

    @Override
    public void processUI(Pane gameWindow) {
        this.updateScore();
        Text scoreText = (Text) gameWindow.lookup("#scoreLabel");
        if (scoreText != null) {
            scoreText.setText("Score: " + this.score);
        } else {
            scoreText = new Text(10,40,"Score: ");
            scoreText.setId("scoreLabel");
            gameWindow.getChildren().add(scoreText);
        }
    }

    private void updateScore() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/score"))
                .GET()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            this.score = Integer.parseInt(response.body());
        } catch (IOException | InterruptedException e) {
            System.out.println("Failed to get score");
        }
    }
}