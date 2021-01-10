import java.util.Random;
import java.util.ArrayList;

class DecisionMaker
{
	private final int[][] winConditions;
	private Random rand;
	private final int[] corners;
	private final int[] edges;

	public DecisionMaker()
	{
		winConditions = new int[][] { {0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6} };
		rand = new Random();
		corners = new int[] { 0, 2, 6, 8 };
		edges = new int[] { 1, 3, 5, 7 };
	}

	private int check2InLine(CellState[] gameboard, CellState state)
	{
		int cell = 10;

		for (int[] winCondition : winConditions)
		{
			int counter = 0;
			for(int winCell : winCondition)
			{
				if(gameboard[winCell] == CellState.BLANK && cell == 10) cell = winCell;
				else if (gameboard[winCell] == state && counter != 2) counter++;
				else
				{
					counter = 0;
					break;
				}
			}
			if (counter == 2 && cell != 10) return cell;
			else cell = 10;
		}
		return cell;
	}

	public int selectNextCell(CellState[] gameboard, CellState robot, CellState opponent)
	{
		if (check2InLine(gameboard, robot) != 10) return check2InLine(gameboard, robot);
		else if (check2InLine(gameboard, opponent) != 10) return check2InLine(gameboard, opponent);
		else if (gameboard[4] == CellState.BLANK) return 4;
		else
		{
			ArrayList<Integer> empty;

			// Corners
			empty = new ArrayList<Integer>();
			for (int i = 0; i < corners.length; i++)
			{
				if (gameboard[corners[i]] == CellState.BLANK) empty.add(corners[i]);
			}

			if (empty.size() != 0) empty.get(rand.nextInt(empty.size()));

			// Edges
			empty = new ArrayList<Integer>();
			for (int i = 0; i < edges.length; i++)
			{
				if (gameboard[edges[i]] == CellState.BLANK) empty.add(edges[i]);
			}

			if (empty.size() != 0) return empty.get(rand.nextInt(empty.size()));
		}

		return 0;
	}

	public boolean checkValidInput(CellState[] gameboard, int selectedCell)
	{
		return (gameboard[selectedCell] == CellState.BLANK);
	}

	public int checkGameEnd(CellState[] gameboard)
	{
		for (int[] winCondition : winConditions)
		{
			if (gameboard[winCondition[0]] == CellState.BLANK) continue;
			else if (gameboard[winCondition[0]] == gameboard[winCondition[1]] &&
				gameboard[winCondition[1]] == gameboard[winCondition[2]]) return 1;
		}
		for (CellState cell : gameboard)
		{
			if (cell == CellState.BLANK) return 0;
		}
		return 2;
	}
}