package presentation;

import data.Team;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import logic.AllTeams;

import java.util.ArrayList;

public class AllTeamsView {
    public Team teamToSee;

    public VBox createSeeTeamView() {

        ArrayList<Button> buttonList = new ArrayList<Button>();

        for (Team team : AllTeams.allTeams) {
            Button teamButton = new Button(team.getTeamName());
            teamButton.setPrefSize(150,50);
            teamButton.setOnAction(klik -> {
                            getHoldInfo(teamButton);
                            UILoader.instance.changeCenter("holdInfo");
                        });
            buttonList.add(teamButton);
        }

        TilePane teamsTilePane = new TilePane();
        teamsTilePane.getChildren().addAll(buttonList);
        teamsTilePane.setPrefColumns(4);
        teamsTilePane.setAlignment(Pos.CENTER);
        teamsTilePane.setHgap(10);
        teamsTilePane.setVgap(10);

        Button backBtn = new Button("Back");
        backBtn.setOnAction(Klik -> UILoader.instance.changeCenter("startMenu"));

        VBox root = new VBox(teamsTilePane, backBtn);
        root.setPadding(new Insets(20,20,20,20));
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);

        root.setId("allTeams");
        root.setFillWidth(false);
        return root;
    }

    public Team getHoldInfo(Button button) {
        for (Team checkTeam : AllTeams.allTeams) {
            if (button.getText().equals(checkTeam.getTeamName())) {
                teamToSee = checkTeam;
                return teamToSee;
            }
        }
        return null;
    }
}



