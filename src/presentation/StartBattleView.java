package presentation;

import data.DataLayer;
import data.Team;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.AllTeams;
import logic.BattleSummary;

public class StartBattleView {
    Team team1;
    Team team2;
    BattleView battleView;
    public VBox createBattleView(){
        AllTeams.allTeams.clear();
        DataLayer.instance.getAllTeams();
        ChoiceBox opponent1 = new ChoiceBox(FXCollections.observableArrayList(AllTeams.allTeams));
        opponent1.setTooltip(new Tooltip("Select the first team"));
        ChoiceBox opponent2 = new ChoiceBox(FXCollections.observableArrayList(AllTeams.allTeams));
        opponent2.setTooltip(new Tooltip("Select second team"));
        opponent1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                team1 =(Team)t1;
            }
        });
        opponent2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                team2 =(Team)t1;
            }
        });

        HBox selectTeams = new HBox(opponent1, opponent2);
        Button startBattleBtn = new Button("Start match");
        startBattleBtn.setOnAction(Klik -> {
            UILoader.instance.battleEvents.setTeams(team1, team2);
            UILoader.instance.changeCenter("aktivKamp");
            Main.mediaPlayerMain.stop();
            BattleSummary.instance.clearRapport();
        });

        VBox root = new VBox(selectTeams,startBattleBtn);
        root.setId("startKamp");

        selectTeams.setAlignment(Pos.TOP_CENTER);
        selectTeams.setSpacing(20);
        root.setAlignment(Pos.TOP_CENTER);
        root.setSpacing(10);
        root.setPadding(new Insets(50, 50, 50, 50));

        root.setId("startBattle");

        return root;
    }
}
