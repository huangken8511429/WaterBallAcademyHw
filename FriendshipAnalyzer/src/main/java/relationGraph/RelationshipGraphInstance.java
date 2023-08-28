package relationGraph;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedMultigraph;

import java.util.List;

public class RelationshipGraphInstance implements RelationshipGraph {
    private final String script;

    public RelationshipGraphInstance(String script) {
        this.script = script;
    }

    @Override
    public boolean hasConnection(String name1, String name2) {
        AllDirectedPaths<String, DefaultEdge> allPaths = parseScript();
        List<GraphPath<String, DefaultEdge>> paths = allPaths.getAllPaths(name1, name2, true, null);
        return !paths.isEmpty();
    }

    private AllDirectedPaths<String, DefaultEdge> parseScript() {
        Graph<String, DefaultEdge> friendshipGraph = new DirectedMultigraph<>(DefaultEdge.class);
        String[] split = script.split("\n");
        for (String s : split) {
            String currentPeople = s.substring(0, s.indexOf(":"));
            friendshipGraph.addVertex(currentPeople);
            String[] matchPeoples = s.substring(s.indexOf(":") + 2).split(" ");
            for (String matchPeople : matchPeoples) {
                friendshipGraph.addVertex(matchPeople);
                friendshipGraph.addEdge(currentPeople, matchPeople);
            }
        }
        return new AllDirectedPaths<>(friendshipGraph);
    }
}
