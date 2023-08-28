package superRelationship;

public class SuperRelationshipAnalyzer {
    private String script;

    public void init(String script) {
        this.script = script;
    }

    public boolean isMutualFriend(String targetName, String name1, String name2) {
        String relation1 = String.format("%s -- %s", targetName, name1);
        String relation2 = String.format("%s -- %s", targetName, name2);
        return script.contains(relation1) && script.contains(relation2);
    }
}
