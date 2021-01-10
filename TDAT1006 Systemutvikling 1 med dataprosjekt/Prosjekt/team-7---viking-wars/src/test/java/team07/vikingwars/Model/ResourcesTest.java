package team07.vikingwars.Model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ResourcesTest {
    @Test
    public void testSilverSetandGetAmount() {
        Resource silver = new Silver(10);
        assertEquals(10,silver.getAmount());
        silver.setAmount(20);
        assertEquals(20,silver.getAmount());
        silver.addAmount(10);
        assertEquals(30, silver.getAmount());
    }
    @Test
    public void testWoodSetandGetAmount() {
        Resource wood = new Wood(10);
        assertEquals(10,wood.getAmount());
        wood.setAmount(20);
        assertEquals(20,wood.getAmount());
        wood.addAmount(10);
        assertEquals(30, wood.getAmount());
    }
    @Test
    public void testIronSetandGetAmount() {
        Resource iron = new Iron(10);
        assertEquals(10,iron.getAmount());
        iron.setAmount(20);
        assertEquals(20,iron.getAmount());
        iron.addAmount(10);
        assertEquals(30, iron.getAmount());
    }
    @Test
    public void testSilverGetName() {
        Resource silver = new Silver(10);
        assertEquals("Silver",silver.getName());
    }
    @Test
    public void testWoodGetName() {
        Resource wood = new Wood(10);
        assertEquals("Wood",wood.getName());
    }
    @Test
    public void testIronGetName() {
        Resource iron = new Iron(10);
        assertEquals("Iron",iron.getName());
    }
    @Test
    public void testSilverToString() {
        Resource silver = new Silver(10);
        assertEquals("Silver: 10",silver.toString());
    }
    @Test
    public void testWoodToString() {
        Resource wood = new Wood(10);
        assertEquals("Wood: 10",wood.toString());
    }
    @Test
    public void testIronToString() {
        Resource iron = new Iron(10);
        assertEquals("Iron: 10",iron.toString());
    }
}
