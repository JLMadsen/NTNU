package team07.vikingwars.Model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BuildingTest {

    @Test
    public void test_Raider_Upgrade(){
        RaidersHut hut1 = new RaidersHut(0);

        hut1.setLevel(hut1.getLevel()+1);
        assertEquals(1, hut1.getLevel());

        hut1.setLevel(hut1.getLevel()+1);
        assertEquals(2, hut1.getLevel());
    }

    @Test
    public void test_Radier_Production(){
        RaidersHut hut1 = new RaidersHut(0);
        int level = 0;

        assertEquals(3, hut1.getBaseProduction());

        hut1.setLevel(hut1.getLevel()+1);
        level++;
        assertEquals(level*3, hut1.getResourcePerTurn());
    }

    @Test
    public void test_Ironmine_Upgrade(){
        Ironmine iron1 = new Ironmine(0);

        iron1.setLevel(iron1.getLevel()+1);
        assertEquals(1, iron1.getLevel());

        iron1.setLevel(iron1.getLevel()+1);
        assertEquals(2, iron1.getLevel());
    }

    @Test
    public void test_Ironmine_Production(){
        Ironmine iron1 = new Ironmine(0);
        int level = 0;

        assertEquals(3, iron1.getBaseProduction());

        iron1.setLevel(iron1.getLevel()+1);
        level++;
        assertEquals(level*3, iron1.getResourcePerTurn());
    }

    @Test
    public void test_Woodcutter_Upgrade(){
        Woodcutter wood1 = new Woodcutter(0);

        wood1.setLevel(wood1.getLevel()+1);
        assertEquals(1, wood1.getLevel());

        wood1.setLevel(wood1.getLevel()+1);
        assertEquals(2, wood1.getLevel());
    }

    @Test
    public void test_Woodcutter_Production(){
        Woodcutter wood1 = new Woodcutter(0);
        int level = 0;

        assertEquals(3, wood1.getBaseProduction());

        wood1.setLevel(wood1.getLevel()+1);
        level++;
        assertEquals(level*3, wood1.getResourcePerTurn());
    }

    @Test
    public void test_Farm_Upgrade(){
        Farm farm1 = new Farm(0);

        farm1.setLevel(farm1.getLevel()+1);
        assertEquals(1, farm1.getLevel());

        farm1.setLevel(farm1.getLevel()+1);
        assertEquals(2, farm1.getLevel());
    }

    @Test
    public void test_Farm_Capacity(){
        Farm farm1 = new Farm(0);
        int level = 0;

        assertEquals(0, farm1.getCapacity());

        farm1.setLevel(farm1.getLevel()+1);
        level++;
        assertEquals(level*20, farm1.getCapacity());
    }

    @Test
    public void test_Wall_upgrade(){
        Wall wall1 = new Wall(0);

        wall1.setLevel(wall1.getLevel()+1);
        assertEquals(1, wall1.getLevel());

        wall1.setLevel(wall1.getLevel()+1);
        assertEquals(2, wall1.getLevel());
    }

    @Test
    public void test_Wall_protection(){
        Wall wall1 = new Wall(0);
        int level = 0;

        assertEquals(1.25, wall1.getDefenseFactor(), 1);

        wall1.setLevel(wall1.getLevel()+1);
        level++;
        assertEquals(1.25*(level*1.05), wall1.getDefenseFactor(),1);
    }

    @Test
    public void test_Barracks_upgrade(){
        Barracks barr1 = new Barracks(0);

        barr1.setLevel(barr1.getLevel()+1);
        assertEquals(1, barr1.getLevel());

        barr1.setLevel(barr1.getLevel()+1);
        assertEquals(2, barr1.getLevel());
    }

    @Test
    public void test_Townhall_upgrade(){
        TownHall townh1 = new TownHall(0);

        townh1.setLevel(townh1.getLevel()+1);
        assertEquals(1, townh1.getLevel());

        townh1.setLevel(townh1.getLevel()+1);
        assertEquals(2, townh1.getLevel());
    }
}
