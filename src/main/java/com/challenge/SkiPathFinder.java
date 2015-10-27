package com.challenge;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SkiPathFinder {

    private TopologicalSort topologicalSort;

    public SkiPathFinder(TopologicalSort topologicalSort) {
        this.topologicalSort = topologicalSort;
    }

    public LongestPath getLongestPath(Map map) {
        List<Integer> sortedElevations = topologicalSort.sort(map);
        return findLongestPath(map, sortedElevations)[0];
    }

    private LongestPath[] findLongestPath(Map map, List<Integer> sortedElevations) {
        LongestPath[] paths = new LongestPath[sortedElevations.size()];
        for (int node : sortedElevations) {
            if (paths[node] == null) {
                paths[node] = getLongestPath(map, paths, node);
            }
        }
        Arrays.sort(paths, Collections.reverseOrder());
        return paths;
    }

    private LongestPath getLongestPath(Map map, LongestPath[] paths, int node) {
        LongestPath path = paths[node];
        if (path == null) {
            path = new LongestPath(map.getElevations()[node]);
            LongestPath subGraphLongestPath = new LongestPath();
            for (int n : map.getElevations()[node].getEdges()) {
                LongestPath tmpPath = getLongestPath(map, paths, n);
                paths[n] = tmpPath;
                if (tmpPath.isLonger(subGraphLongestPath)) {
                    subGraphLongestPath = tmpPath;
                }
            }
            path.addPath(subGraphLongestPath.getElevations());
        }
        return path;
    }
}
