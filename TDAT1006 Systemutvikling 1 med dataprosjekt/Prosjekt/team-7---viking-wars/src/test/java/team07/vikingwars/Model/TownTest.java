package team07.vikingwars.Model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class TownTest {

    @Test
    public void test_Town_Coords(){
        Town town1 = new Town(null, null, null, 1, 2,"Test Town", 1, 1);

        assertEquals(1, town1.getX());
        assertEquals(2, town1.getY());
    }

    @Test
    public void test_Town_Name(){
        String name = "town name";
        Town town1 = new Town(null, null, null, 1, 2,name, 1, 1);

        assertEquals(name, town1.getName());
    }

    @Test
    public void test_Town_Owner(){
        Town town1 = new Town(null, null, null, 1, 2,"Test Town", 420, 1);

        assertEquals(420, town1.getOwner());
    }

    @Test
    public void test_Town_TownId(){
        Town town1 = new Town(null, null, null, 1, 2,"Test Town", 1, 555);

        assertEquals(555, town1.getTownId());
    }

}

