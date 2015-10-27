package com.challenge;

import static org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class LongestPath implements Comparable<LongestPath> {

    private List<Elevation> elevations = new ArrayList<Elevation>();

    public LongestPath(Elevation elevation) {
        elevations.add(elevation);
    }

    public LongestPath() {
    }

    public void addPath(List<Elevation> list) {
        elevations.addAll(list);
    }

    @Override
    public int compareTo(LongestPath o) {
        int result = Integer.compare(length(), o.length());
        if (result != 0) {
            return result;

        }
        return Integer.compare(getDrop(), getDrop(o));
    }

    public int getDrop() {
        return getDrop(this);
    }

    private int length() {
        return elevations.size();
    }

    private int getDrop(LongestPath path) {
        if (path.isSingleElevation()) {
            return -path.elevations.get(0).getWeight();
        }
        return path.getElevations().get(0).getWeight()
                - path.getElevations().get(path.getElevations().size() - 1).getWeight();
    }

    private boolean isSingleElevation() {
        return elevations.size() == 1;
    }

    public boolean isLonger(LongestPath lastPath) {
        return compareTo(lastPath) == 1;
    }

    public List<Elevation> getElevations() {
        return elevations;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, MULTI_LINE_STYLE).append("Length", length()).append("Drop", getDrop())
                .append("Path", elevations.stream().map(e -> e.getWeight()).toArray()).toString();
    }
}
