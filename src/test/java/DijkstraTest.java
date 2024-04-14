import org.junit.Assert;
import org.junit.Test;

import javax.xml.transform.SourceLocator;
import java.util.Arrays;

public class DijkstraTest {
    @Test
    public void test1(){
        int[][] edges = {
                {1,0,1},
                {1,2,1},
                {2,3,1}
        };
        int n = 4;
        int k = 2;
        int expected = 2;
        Dijkstra dj = new Dijkstra(edges, n);
        int[] res  = dj.minimumCost(k - 1);
        int actual = -1;
        for(int x : res){
            actual = Math.max(actual, x);
        }
        if(actual == Integer.MAX_VALUE){
            actual = -1;
        }
        Assert.assertEquals(expected, actual);
    }
}
