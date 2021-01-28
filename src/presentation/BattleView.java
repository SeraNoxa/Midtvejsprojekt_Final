package presentation;

import data.Team;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javafx.scene.transform.Scale;
import logic.BattleEvents;
import logic.TimeTasker;

import java.io.File;
import java.util.Timer;

public class BattleView {

    BattleEvents battleEvents;
    Team team1;
    Team team2;
    public static Label timeLeftLabel;
    public static boolean timeIsLeft;
    public static  Label battleTextLbl;
    public static MediaPlayer battlePlayer;
    MediaPlayer rightAttackPlayer;
    MediaPlayer leftAttackPlayer;
    MediaPlayer leftHealPlayer;
    MediaPlayer rightHealPlayer;

    public BattleView(BattleEvents battleEvents) {
        this.battleEvents = battleEvents;
    }

    public VBox createBattleView(Team team1, Team team2){

        this.team1 = battleEvents.team1;
        this.team2 = battleEvents.team2;
        battleEvents.startTime();

        String musicFile = "src/media/sound/16-Bit_Wave_Super_Nintendo__Sega_Genesis_RetroWave_Mix_mp3cut.net.mp3";
        Media sound2 = new Media(new File(musicFile).toURI().toString());
        battlePlayer = new MediaPlayer(sound2);
        battlePlayer.play();
        battlePlayer.setVolume(0.02);

        Label teamNameLeft = new Label(team1.getTeamName());
        Label teamNameRight = new Label(team2.getTeamName());
        Label leftHp = new Label();
        leftHp.textProperty().bind(team1.hp.asString());
        Label rightHp = new Label();
        rightHp.textProperty().bind(team2.hp.asString());
        Button leftAttackBtn = new Button("Angrib");
        Button rightAttackBtn = new Button("Angrib");
        leftAttackBtn.setId("leftattack");
        rightAttackBtn.setId("rightattack");
        Button leftHealBtn = new Button("");
        Button rightHealBtn = new Button("");
        leftHealBtn.setId("heal");
        rightHealBtn.setId("heal");

        Scale scaleTransformation = new Scale();
        scaleTransformation.setX(0);
        scaleTransformation.setY(0);

        timeLeftLabel = new Label();
        timeLeftLabel.setAlignment(Pos.CENTER);
        timeLeftLabel.setPadding(new Insets(50, 50, 50, 50));

        battleTextLbl = new Label();
        battleTextLbl.setAlignment(Pos.CENTER);
        battleTextLbl.setPadding(new Insets(50, 50, 50, 50));

        leftAttackBtn.setOnAction(klik ->{ battleEvents.attack(team1, team2);
            battleEvents.BattleDecided(team1, team2);
            Media leftAttack = new Media(new File("src/media/sound/Street_fighter_sound_Shoryuken.mp3").toURI().toString());
            rightAttackPlayer = new MediaPlayer(leftAttack);
            rightAttackPlayer.setVolume(0.09);
            rightAttackPlayer.play();
        });
        rightAttackBtn.setOnAction(klik ->{ battleEvents.attack(team2, team1);
            battleEvents.BattleDecided(team1, team2);
            Media rightAttack = new Media(new File("src/media/sound/Street_Fighter_sound_Hadouken.mp3").toURI().toString());
            leftAttackPlayer = new MediaPlayer(rightAttack);
            leftAttackPlayer.setVolume(0.05);
            leftAttackPlayer.play();
        });
        leftHealBtn.setOnAction(klik ->{
            battleEvents.heal(team1);
            leftHealBtn.setDisable(true);
            leftHealBtn.getTransforms().add(scaleTransformation);
            Media heal = new Media(new File("src/media/sound/PokeCenter_Healing_sound_mp3cut.net.mp3").toURI().toString());
            leftHealPlayer = new MediaPlayer(heal);
            leftHealPlayer.setVolume(0.05);
            leftHealPlayer.play();
        });
        rightHealBtn.setOnAction(klik -> {
            battleEvents.heal(team2);
            rightHealBtn.setDisable(true);
            rightHealBtn.getTransforms().add(scaleTransformation);
            Media heal = new Media(new File("src/media/sound/PokeCenter_Healing_sound_mp3cut.net.mp3").toURI().toString());
            rightHealPlayer = new MediaPlayer(heal);
            rightHealPlayer.setVolume(0.05);
            rightHealPlayer.play();
        });

        VBox leftSideVBox = new VBox(teamNameLeft, leftHp,leftAttackBtn,leftHealBtn);
        leftSideVBox.setMinWidth(200);
        leftSideVBox.setAlignment(Pos.CENTER);

        VBox rightSideVBox = new VBox(teamNameRight,rightHp,rightAttackBtn,rightHealBtn);
        rightSideVBox.setMinWidth(200);
        rightSideVBox.setAlignment(Pos.CENTER);

        HBox bothSidesVBox= new HBox(leftSideVBox,rightSideVBox);
        bothSidesVBox.setMinWidth(590);
        bothSidesVBox.setAlignment(Pos.CENTER);

        VBox root = new VBox(timeLeftLabel,bothSidesVBox, battleTextLbl);
        root.setAlignment(Pos.TOP_CENTER);
        root.setId("battleView");
        return root;
    }
}
