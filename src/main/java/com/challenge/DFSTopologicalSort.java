package com.challenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * DFA based topological sort implementation.
 */
public class DFSTopologicalSort implements TopologicalSort {

    @Override
    public List<Integer> sort(Map map) {
        boolean[] visited = new boolean[map.getElevations().length];
        List<Integer> result = new ArrayList<>(map.getElevations().length);
        Set<Integer> processedNodes = new HashSet<>(map.getElevations().length, 1);

        for (int i = 0; i < visited.length; i++) {
            doDfs(map, visited, result, processedNodes, i);
        }

        Collections.reverse(result);
        return result;

    }

    private void doDfs(Map map, boolean[] visited, List<Integer> result, Set<Integer> processedNodes, int currentNode) {
        if (!visited[currentNode]) {
            visited[currentNode] = true;
            for (int n : map.getElevations()[currentNode].getEdges()) {
                doDfs(map, visited, result, processedNodes, n);
            }
            result.add(currentNode);
            processedNodes.add(currentNode);
        } else if (!processedNodes.contains(currentNode)) {
            throw new IllegalArgumentException("Map has a cycle");
        }
    }
}
