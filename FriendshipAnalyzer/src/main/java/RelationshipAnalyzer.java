import relationGraph.RelationshipGraph;

public interface RelationshipAnalyzer {
    RelationshipGraph parse(String script);
    String getMutualFriends(String name1, String name2);
}
