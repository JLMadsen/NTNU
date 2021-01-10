package team07.vikingwars.Model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class UnitTest {
    @Test
    public void test_Spearman_getName() {
        Unit testunit = new Spearman(1);
        assertEquals("Spearman", testunit.getName());
    }
    @Test
    public void test_Spearman_getHealth() {
        Unit testunit = new Spearman(1);
        assertEquals(100, testunit.getHealth());
    }
    @Test
    public void test_Spearman_getTroopCount() {
        Unit testunit = new Spearman(1);
        assertEquals(1, testunit.getTroopCount());
    }
    @Test
    public void test_Spearman_setTroopCount() {
        Unit testunit = new Spearman(1);
        testunit.setTroopCount(10);
        assertEquals(10, testunit.getTroopCount());
    }
    @Test
    public void test_Spearman_getIndex() {
        Unit testunit = new Spearman(1);
        assertEquals(0, testunit.getIndex());
    }
    @Test
    public void test_Spearman_getDamage() {
        Unit testunit = new Spearman(1);
        assertEquals(50, testunit.getDamage());
    }
    @Test
    public void test_Spearman_getPrice() {
        Unit testunit = new Spearman(1);
        assertEquals(5, testunit.getPrice()[0]);
        assertEquals(0, testunit.getPrice()[2]);
        assertEquals(0, testunit.getPrice()[1]);
    }
    @Test
    public void test_Spearman_death() {
        Unit testunit = new Spearman(2);
        testunit.death(1);
        assertEquals(1, testunit.getTroopCount());
    }
    @Test
    public void test_Spearman_toString() {
        Unit testunit = new Spearman(1);
        assertEquals("Spearman | 50 Damage | 100 Health | Cost: 5 Silver, 0 Wood, 0 Iron.", testunit.toString());
    }
    @Test
    public void test_Cavalry_getName() {
        Unit testunit = new Cavalry(1);
        assertEquals("Cavalry", testunit.getName());
    }
    @Test
    public void test_Cavalry_getHealth() {
        Unit testunit = new Cavalry(1);
        assertEquals(100, testunit.getHealth());
    }
    @Test
    public void test_Cavalry_getTroopCount() {
        Unit testunit = new Cavalry(1);
        assertEquals(1, testunit.getTroopCount());
    }
    @Test
    public void test_Cavalry_setTroopCount() {
        Unit testunit = new Cavalry(1);
        testunit.setTroopCount(10);
        assertEquals(10, testunit.getTroopCount());
    }
    @Test
    public void test_Cavalry_getIndex() {
        Unit testunit = new Cavalry(1);
        assertEquals(2, testunit.getIndex());
    }
    @Test
    public void test_Cavalry_getDamage() {
        Unit testunit = new Cavalry(1);
        assertEquals(75, testunit.getDamage());
    }
    @Test
    public void test_Cavalry_getPrice() {
        Unit testunit = new Cavalry(1);
        assertEquals(50, testunit.getPrice()[0]);
        assertEquals(50, testunit.getPrice()[2]);
        assertEquals(50, testunit.getPrice()[1]);
    }
    @Test
    public void test_Cavalry_death() {
        Unit testunit = new Cavalry(2);
        testunit.death(1);
        assertEquals(1, testunit.getTroopCount());
    }
    @Test
    public void test_Cavalry_toString() {
        Unit testunit = new Cavalry(1);
        assertEquals("Cavalry | 75 Damage | 100 Health | Cost: 50 Silver, 50 Wood, 50 Iron.", testunit.toString());
    }
    @Test
    public void test_Archer_getName() {
        Unit testunit = new Archer(1);
        assertEquals("Archer", testunit.getName());
    }
    @Test
    public void test_Archer_getHealth() {
        Unit testunit = new Archer(1);
        assertEquals(100, testunit.getHealth());
    }
    @Test
    public void test_Archer_getTroopCount() {
        Unit testunit = new Archer(1);
        assertEquals(1, testunit.getTroopCount());
    }
    @Test
    public void test_Archer_setTroopCount() {
        Unit testunit = new Archer(1);
        testunit.setTroopCount(10);
        assertEquals(10, testunit.getTroopCount());
    }
    @Test
    public void test_Archer_getIndex() {
        Unit testunit = new Archer(1);
        assertEquals(1, testunit.getIndex());
    }
    @Test
    public void test_Archer_getDamage() {
        Unit testunit = new Archer(1);
        assertEquals(40, testunit.getDamage());
    }
    @Test
    public void test_Archer_getPrice() {
        Unit testunit = new Archer(1);
        assertEquals(0, testunit.getPrice()[0]);
        assertEquals(10, testunit.getPrice()[2]);
        assertEquals(0, testunit.getPrice()[1]);
    }
    @Test
    public void test_Archer_death() {
        Unit testunit = new Archer(2);
        testunit.death(1);
        assertEquals(1, testunit.getTroopCount());
    }
    @Test
    public void test_Archer_toString() {
        Unit testunit = new Archer(1);
        assertEquals("Archer | 40 Damage | 100 Health | Cost: 0 Silver, 10 Wood, 0 Iron.", testunit.toString());
    }

}
