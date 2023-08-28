import relationGraph.RelationshipGraph;

import static util.ReadFile.readFileContent;

public class Main {
    public static void main(String[] args) {
        String script = readFileContent("src/main/java/script/script.txt");
        SuperRelationshipAnalyzerAdapter superRelationshipAnalyzerAdapter = new SuperRelationshipAnalyzerAdapter();
        RelationshipGraph graph = superRelationshipAnalyzerAdapter.parse(script);
        System.out.println(superRelationshipAnalyzerAdapter.getMutualFriends("A", "E"));
        System.out.println(graph.hasConnection("A", "C"));
    }
}