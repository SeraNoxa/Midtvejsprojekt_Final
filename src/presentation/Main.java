package presentation;

import data.DataLayer;
import javafx.application.Application;
import javafx.stage.Stage;
import logic.AllTeams;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Main extends Application {
    AllTeams allTeams = new AllTeams();
    public static MediaPlayer mediaPlayerMain;
    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("DataMatiker StreetFighter");
        String musicFile = "src/media/sound/StartMenu.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayerMain = new MediaPlayer(sound);
        mediaPlayerMain.play();
        mediaPlayerMain.setVolume(0.1);

        primaryStage.setScene(UILoader.instance.createScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        DataLayer datalayer = new DataLayer("MidtVejsProjektDB");
        System.out.println(datalayer.getAllTeams());
        launch(args);
    }
}
