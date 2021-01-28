package logic;

import data.Team;
import java.util.ArrayList;
import java.util.Random;

public class UserFeedback {
    Team team1;
    Team team2;

    public String randomAttack(Team team1, Team team2) {

        ArrayList<String> givenList = new ArrayList<String>();
        givenList.add(team1.getTeamName()+ " smadrede "+ team2.getTeamName());
        givenList.add(team1.getTeamName()+ " tæskede "+ team2.getTeamName()+" med en våd avis");
        givenList.add(team1.getTeamName()+ " tæskede "+ team2.getTeamName()+" med en kæp");
        givenList.add(team1.getTeamName()+ " daskede "+ team2.getTeamName()+" i næseryggen");
        givenList.add(team1.getTeamName()+ " sparkede "+ team2.getTeamName()+" i knæhasen");
        givenList.add(team1.getTeamName()+ " råber verbale beskeder på svensk mod "+ team2.getTeamName()+" med en våd avis");
        givenList.add(team1.getTeamName()+ " slog en prut i retningen af "+ team2.getTeamName());
        givenList.add(team1.getTeamName()+ " Flemming fortæller en anekdote og "+ team2.getTeamName());
        givenList.add(team1.getTeamName()+ " får Jan til at kaste sin skrå pæl fra haven på "+ team2.getTeamName());
        givenList.add(team1.getTeamName()+ " kaster sand i øjnene på "+ team2.getTeamName());
        givenList.add(team1.getTeamName()+ " giver "+ team2.getTeamName()+" to fingre i øjnene");
        givenList.add(team1.getTeamName()+ " giver "+ team2.getTeamName()+" en olfert");
        givenList.add(team1.getTeamName()+ " giver "+ team2.getTeamName()+" en lussing");
        givenList.add(team1.getTeamName()+ " giver "+ team2.getTeamName()+" en sheriff Stjerne");
        givenList.add(team1.getTeamName()+ " får Flemming til at smadre "+ team2.getTeamName()+" i skak");
        givenList.add(team1.getTeamName()+ " kaster sin 95 siders powerpoint presentation i hovedet på "+ team2.getTeamName());
        givenList.add(team1.getTeamName()+ " fortæller en din-mor joke ");
        givenList.add(team1.getTeamName()+ " fortæller død baby joke ");
        givenList.add(team1.getTeamName()+ " giver "+ team2.getTeamName()+" smølfespark lige i øjet");

        Random random = new Random();
        for (int i = 0; i < 1; i++) {
            String randomAttacks = givenList.get(random.nextInt(givenList.size()));
            return randomAttacks;
        }
        return null;
    }
}
