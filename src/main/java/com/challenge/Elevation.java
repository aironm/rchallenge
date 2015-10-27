package com.challenge;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Elevation {

    private final int weight;
    private final Set<Integer> edges = new HashSet<>();

    public Elevation(int weigth) {
        this.weight = weigth;
    }

    public int getWeight() {
        return weight;
    }

    public Set<Integer> getEdges() {
        return edges;
    }

    public void addEdge(int node) {
        edges.add(node);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, SHORT_PREFIX_STYLE).append("weight", weight)
                .append("edges", edges).toString();
    }

    public boolean isLeaf() {
        return edges.isEmpty();
    }
}
