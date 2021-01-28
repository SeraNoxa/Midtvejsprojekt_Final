package presentation;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class StartMenu {
    public HBox createStartMenu(){
//        Text lblText = new Text("Fight Club");
//        lblText.setColor(Color.RED);

//        Label titelLabel = new Label("Fight Club");
//        titelLabel.setAlignment(Pos.CENTER);
//        titelLabel.setTextFill(Color.ORANGE);
//        titelLabel.setStyle("-fx-font: 58 arial;" +  "-fx-border-color: blue;");
        //titelLabel.setBorder("Color.RED");
       // VBox labelVBox = new VBox(titelLabel);
        //labelVBox.setAlignment(Pos.TOP_CENTER);

//        Button createTeamBtn = new Button("Create team");
//        Button battleBtn = new Button("Battle");
//        Button leagueTableViewBtn = new Button("Watch League");
//        Button watchTeamBtn = new Button("Inspect Team");
//        watchTeamBtn.setOnAction(klik -> UILoader.instance.changeCenter("seHold"));
//        battleBtn.setOnAction(klik -> UILoader.instance.changeCenter("kamp"));
//        leagueTableViewBtn.setOnAction(klik -> UILoader.instance.changeCenter("liga"));
//        createTeamBtn.setOnAction(klik -> UILoader.instance.changeCenter("opretHold"));
        HBox root = new HBox();//createTeamBtn,battleBtn,leagueTableViewBtn,watchTeamBtn)
        root.setId("startmenu");
        return root;
    }

}
