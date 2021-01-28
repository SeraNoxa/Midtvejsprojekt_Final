package presentation;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameOverView {

    private static final String MEDIA_URL =
            "http://seranoxa.com/downloads/TrueSurvivor.mp4";

    private static final Duration FADE_DURATION = Duration.seconds(2.0);

    public static void display(String title, String message){

        final MediaPlayer mediaPlayer = new MediaPlayer(
                new Media(
                        MEDIA_URL
                )
        );
        final MediaView mediaView = new MediaView(mediaPlayer);

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(400);
        window.setMinHeight(300);
        BattleView.battlePlayer.stop();
        mediaPlayer.setVolume(0.5);
        Label messageLbl = new Label(message);
        Button backToStartBtn = new Button("Start Menu");

        backToStartBtn.setOnAction(klik ->{
            UILoader.instance.changeCenter("startMenu");
            window.close();
        });

        Button watchBattleReportBtn = new Button("Battle Report");
        watchBattleReportBtn.setOnAction(Klik -> {
            UILoader.instance.changeCenter("visKampRapport");
            window.close();
        });
        Button eastereggBtn = new Button();
        eastereggBtn.setOnAction(Klik -> UILoader.instance.changeCenter("easteregg"));
        eastereggBtn.setId("eastereggbtn");
        HBox buttons = new HBox(backToStartBtn,watchBattleReportBtn,eastereggBtn);
        buttons.setAlignment(Pos.CENTER);
        VBox layout = new VBox(messageLbl,buttons,eastereggBtn);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setId("GameOver");
        Scene scene = new Scene(layout);

        window.setScene(scene);
        scene.getStylesheets().add("presentation/main.css");
        window.showAndWait();
    }

}
