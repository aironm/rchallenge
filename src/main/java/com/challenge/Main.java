package com.challenge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Map map = new Map("input/puzzle-map.txt");
        SkiPathFinder finder = new SkiPathFinder(new DFSTopologicalSort());
        log.info("Longest Path {}", finder.getLongestPath(map));
        log.info("Running Time(second) = {}", (System.currentTimeMillis() - startTime) / 1000);
    }
}
