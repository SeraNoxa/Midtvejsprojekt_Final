package presentation;

import data.DataLayer;
import data.Team;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class EasterEgg {

    private static final String MEDIA_URL ="http://seranoxa.com/downloads/TrueSurvivor.mp4";
    private static final Duration FADE_DURATION = Duration.seconds(2.0);

    public HBox createEasterEgg(){

        final MediaPlayer mediaPlayer = new MediaPlayer(
                new Media(MEDIA_URL));
        final MediaView mediaView = new MediaView(mediaPlayer);


        mediaView.setFitWidth(600);
        mediaView.setFitHeight(384);
        mediaView.setPreserveRatio(true);

        HBox videolayout = new HBox(75);
        videolayout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
        videolayout.getChildren().addAll(
                createVolumeControls(mediaPlayer),
                mediaView
        );
        mediaPlayer.setVolume(0.3);


        mediaPlayer.play();
        HBox root = new HBox(videolayout);
        root.setId("easteregg");
        return root;
    }
    public static Region createVolumeControls(final MediaPlayer mediaPlayer) {
        final Slider volumeSlider = new Slider(0, 1, 0);
        volumeSlider.setOrientation(Orientation.VERTICAL);

        mediaPlayer.volumeProperty().bindBidirectional(volumeSlider.valueProperty());

        final Timeline fadeInTimeline = new Timeline(
                new KeyFrame(
                        FADE_DURATION,
                        new KeyValue(mediaPlayer.volumeProperty(), 1.0)
                )
        );

        final Timeline fadeOutTimeline = new Timeline(
                new KeyFrame(
                        FADE_DURATION,
                        new KeyValue(mediaPlayer.volumeProperty(), 0.0)
                )
        );

        Button fadeIn = new Button("Fade In");
        fadeIn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent t) {
                fadeInTimeline.play();
            }
        });
        fadeIn.setMaxWidth(Double.MAX_VALUE);

        Button fadeOut = new Button("Fade Out");
        fadeOut.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent t) {
                fadeOutTimeline.play();
            }
        });
        fadeOut.setMaxWidth(Double.MAX_VALUE);

        VBox controls = new VBox(5);
        controls.getChildren().setAll(
                volumeSlider,
                fadeIn,
                fadeOut
        );
        controls.setAlignment(Pos.CENTER);
        VBox.setVgrow(volumeSlider, Priority.ALWAYS);

        controls.disableProperty().bind(
                Bindings.or(
                        Bindings.equal(Timeline.Status.RUNNING, fadeInTimeline.statusProperty()),
                        Bindings.equal(Timeline.Status.RUNNING, fadeOutTimeline.statusProperty())
                )
        );

        return controls;
    }
}
