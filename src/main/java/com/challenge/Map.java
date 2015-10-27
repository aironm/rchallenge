package com.challenge;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A DAG implemented using adjacency list.
 */
public class Map {
    private static final Logger log = LoggerFactory.getLogger(Map.class);
    private final Elevation elevations[];

    public Map(String fileName) {
        log.info("Starting Reading Input");
        Scanner sc;
        try {
            sc = new Scanner(Paths.get(fileName));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        int row = sc.nextInt();
        int column = sc.nextInt();

        elevations = new Elevation[row * column];

        populateMap(sc, row, column);
        log.info("Reading input finished");
    }

    private void populateMap(Scanner sc, int row, int column) {
        for (int i = 0; i < row * column; i++) {
            int weight = sc.nextInt();
            elevations[i] = new Elevation(weight);
            for (Integer node : getNavigableElevations(i, column)) {
                switch (Integer.compare(weight, elevations[node].getWeight())) {
                case -1:
                    elevations[node].addEdge(i);
                    break;
                case 1:
                    elevations[i].addEdge(node);
                    break;
                default:
                    break;
                }
            }
        }
    }

    private List<Integer> getNavigableElevations(int i, int colum) {
        List<Integer> elevations = new ArrayList<>();

        if ((i % colum) - 1 >= 0) {
            elevations.add(i - 1);
        }

        if (i - colum >= 0) {
            elevations.add(i - colum);
        }
        return elevations;
    }

    public Elevation[] getElevations() {
        return elevations;
    }

    @Override
    public String toString() {
        ToStringBuilder stringBuilder = new ToStringBuilder(this);
        for (int i = 0; i < elevations.length; i++) {
            stringBuilder.append("\n").append("node", i).append(elevations[i]);
        }
        return stringBuilder.toString();
    }

}
