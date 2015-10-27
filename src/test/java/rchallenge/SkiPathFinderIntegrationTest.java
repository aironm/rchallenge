package rchallenge;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;

import com.challenge.DFSTopologicalSort;
import com.challenge.LongestPath;
import com.challenge.Map;
import com.challenge.SkiPathFinder;

public class SkiPathFinderIntegrationTest {

    private SkiPathFinder finder;

    @Before
    public void setUp() throws Exception {
        finder = new SkiPathFinder(new DFSTopologicalSort());
    }

    @Test
    public void shouldTestSmallInput() {
        Map map = new Map("input/map.txt");

        LongestPath longestPath = finder.getLongestPath(map);
        assertThat(longestPath.getDrop(), is(8));
        assertThat(longestPath.getElevations().size(), is(5));
        assertThat(longestPath.getElevations().get(0).getWeight(), is(9));
        assertThat(longestPath.getElevations().get(longestPath.getElevations().size() - 1).getWeight(), is(1));
    }

    @Test
    public void shouldTestInputWithMuliplteLastOptions() {
        Map map = new Map("input/map1.txt");

        LongestPath longestPath = finder.getLongestPath(map);
        assertThat(longestPath.getDrop(), is(9));
        assertThat(longestPath.getElevations().size(), is(5));
        assertThat(longestPath.getElevations().get(0).getWeight(), is(9));
        assertThat(longestPath.getElevations().get(longestPath.getElevations().size() - 1).getWeight(), is(0));
    }
}
