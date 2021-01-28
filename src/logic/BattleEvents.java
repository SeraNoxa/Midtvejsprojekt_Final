package logic;

import data.DataLayer;
import data.Team;
import presentation.BattleView;
import presentation.GameOverView;
import presentation.UILoader;
import java.util.Random;
import java.util.Timer;


public class BattleEvents {
    public Team team1;
    public Team team2;
    public Timer timer = new Timer();
    public TimeTasker task;
    public boolean timeIsLeft;
    UserFeedback userFeedback = new UserFeedback();

    public void timeRunOut(){
        BattleDraw(team1, team2);
    }

    public void startTime(){
        timer = new Timer();
        task = new TimeTasker(60, this);
        timer.scheduleAtFixedRate(task, 1000, 1000);
        timeIsLeft = true;
    }

    public void setTeams(Team team1, Team team2) {
        this.team1 = new Team(team1);
        this.team2 = new Team(team2);
    }

    public void heal(Team team) {
        BattleDecided(team1, team2);
        Random random = new Random();
        int healed = random.nextInt(16);
        BattleView.battleTextLbl.setText(team.getTeamName() + " blev healet for " + healed + " hp");
        BattleSummary.instance.skrivTilSB(team.getTeamName() + " blev healet for " + healed + " hp");
        BattleSummary.instance.timeStamp();
        team.hp.setValue(team.hp.getValue().intValue() + healed);
    }

    public void attack(Team attacker, Team target) {
        String attack = userFeedback.randomAttack(attacker, target);
        BattleDecided(attacker, target);
        Random random = new Random();
        int dmg = random.nextInt(6);
        int dmgCalculation = target.hp.getValue().intValue() - dmg;
        target.hp.setValue(dmgCalculation);
        BattleView.battleTextLbl.setText(attack+ " og "+ target.getTeamName()+" tog " + dmg + " Skade!");
        BattleSummary.instance.skrivTilSB(attack+ " og "+ target.getTeamName()+" tog " + dmg + " Skade!");
        BattleSummary.instance.timeStamp();
        hpCheck(target);
    }

    public void hpCheck(Team team) {
        if (team.hp.getValue().intValue() <= 0) {
            System.out.println(team.getTeamName() + " har ikke flere hp, og har tabt!");
            team.hasWon = false;
        }
    }

    public void BattleDecided(Team team1, Team team2) {
        if (team1.hasWon == false) {
            timer.cancel();
            team2.point.setValue(team2.getPoint() + 3);
            team2.won.setValue(team2.getWon() + 1);
            team1.lost.setValue(team1.getLost() + 1);
            System.out.println("hold 2 har vundet");
            System.out.println("hold 2 har: " + team2.point + " point");
            BattleSummary.instance.skrivTilSB(team2.getTeamName() + " har vundet !!!!");
            UILoader.instance.changeCenter("liga");
            GameOverView.display("Kampen er ovre", team2.getTeamName() + " har vundet !");

        } else if (team2.hasWon == false) {
            timer.cancel();
            team1.point.setValue(team1.getPoint() + 3);
            team1.won.setValue(team1.getWon() + 1);
            team2.lost.setValue(team2.getLost() + 1);
            System.out.println("hold 1 har vundet");
            System.out.println("hold 1 har: " + team1.point + " point");
            BattleSummary.instance.skrivTilSB(team1.getTeamName() + " har vundet !!!!");
            UILoader.instance.changeCenter("liga");
            GameOverView.display("Kampen er ovre", team1.getTeamName() + " har vundet !");
        }
        DataLayer.instance.updateTeam(team1);
        DataLayer.instance.updateTeam(team2);
    }
    public void BattleDraw(Team team1, Team team2){
            timer.cancel();
            team1.draws.setValue(team1.getDraws()+1);
            team2.draws.setValue(team2.getDraws()+1);
            team1.point.setValue(team1.getPoint()+1);
            team2.point.setValue(team2.getPoint()+1);
            DataLayer.instance.updateTeam(team1);
            DataLayer.instance.updateTeam(team2);
            BattleSummary.instance.skrivTilSB("Det blev uafgjort, begge hold tildeles 1 point");
            UILoader.instance.changeCenter("liga");
            GameOverView.display("Game Over","DET BLEV UAFGJORT. BEGGE HOLD FÅR 1 POINT");
    }
}

