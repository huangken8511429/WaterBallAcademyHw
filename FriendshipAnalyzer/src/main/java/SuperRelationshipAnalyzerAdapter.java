import relationGraph.RelationshipGraph;
import relationGraph.RelationshipGraphInstance;
import superRelationship.SuperRelationshipAnalyzer;

import java.util.List;

import static util.ReadFile.getAllPeople;

public class SuperRelationshipAnalyzerAdapter implements RelationshipAnalyzer {
    private String scriptContent;
    private final SuperRelationshipAnalyzer analyzer = new SuperRelationshipAnalyzer();

    @Override
    public RelationshipGraph parse(String script) {
        scriptContent = script;
        analyzer.init(convertScript(script));
        return new RelationshipGraphInstance(scriptContent);
    }

    @Override
    public String getMutualFriends(String name1, String name2) {
        StringBuilder mutualFriends = new StringBuilder();
        List<String> peoples = getAllPeople(scriptContent);
        for (String people : peoples) {
            if (analyzer.isMutualFriend(people, name1, name2)) {
                mutualFriends.append(people).append(" ");
            }
        }
        return mutualFriends.toString();
    }

    /*

     A: B C D  ->  A -- B
                   A -- C
                   A -- D

     */
    private String convertScript(String script) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] split = script.split("\n");
        for (String s : split) {
            String currentPeople = s.substring(0, s.indexOf(":"));
            String[] matchPeoples = s.substring(s.indexOf(":") + 2).split(" ");
            for (String matchPeople : matchPeoples) {
                stringBuilder.append(currentPeople)
                        .append(" -- ")
                        .append(matchPeople)
                        .append("\n");
            }
        }
        return stringBuilder.toString();
    }

}
