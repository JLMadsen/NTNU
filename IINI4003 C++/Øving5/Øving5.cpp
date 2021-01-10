#include <iostream>
#include <memory>
#include <string>
#include <vector>

using namespace std;

class ChessBoard {
public:
	enum class Color {
		WHITE,
		BLACK
	};

	class Piece {
	public:
		Piece(Color color) : color(color) {}
		virtual ~Piece() {}

		Color color;
		std::string color_string() const {
			if (color == Color::WHITE)
				return "white";
			else
				return "black";
		}

		/// Return color and type of the chess piece
		virtual std::string type() const = 0;

		/// Returns true if the given chess piece move is valid
		virtual bool valid_move(int from_x, int from_y, int to_x, int to_y) const = 0;

		virtual std::string symbol() const = 0;
	};

	class King : public Piece {
	public:
		King(Color color_) : Piece(color_) {};

		std::string type() const override
		{
			return Piece::color_string() + " King.";
		}

		bool valid_move(int from_x, int from_y, int to_x, int to_y) const override
		{
			// hvis den har flyttet mer enn en rute, sjekker ikke Rokade
			int change = abs(from_x - to_x) + abs(from_y - to_y);
			if (change > 1)
			{
				return false;
			}
			// må holde seg på minst en samme akse
			if (from_x != to_x && to_x != to_y)
			{
				return false;
			}
			return true;
		}

		std::string symbol() const override
		{
			if (Piece::color == Color::WHITE)
			{
				return "K";
			}
			else
			{
				return "k";
			}
		}

	};

	class Knight : public Piece {
	public:
		Knight(Color color_) : Piece(color_) {};

		std::string type() const override
		{
			return Piece::color_string() + " Knight.";
		}

		bool valid_move(int from_x, int from_y, int to_x, int to_y) const override
		{
			int xChange = abs(from_x - to_x);
			int yChange = abs(from_y - to_y);

			if ((xChange == 1 && yChange == 2) || (xChange == 2 && yChange == 1))
			{
				return true;
			}

			return false;
		}

		std::string symbol() const override
		{
			if (Piece::color == Color::WHITE)
			{
				return "N";
			}
			else
			{
				return "n";
			}
		}
	};

	ChessBoard() {
		// Initialize the squares stored in 8 columns and 8 rows:
		squares.resize(8);
		for (auto& square_column : squares)
			square_column.resize(8);
	}
	
	void display()
	{
		int width = 8;
		int height = 8;

		for (int i = 0; i < height; i++)
		{
			std::string line = "|_";

			for (int j = 0; j < width; j++)
			{
				auto& piece = squares[i][j];
				if(piece)
				{
					line.append(piece->symbol());
					line.append("_|_");
				}
				else
				{
					line.append(" _|_");
				}
			}
			cout << line << endl;
		}
	}
	
	/// 8x8 squares occupied by 1 or 0 chess pieces
	vector<vector<unique_ptr<Piece>>> squares;

	/// Move a chess piece if it is a valid move.
	/// Does not test for check or checkmate.
	bool move_piece(const std::string& from, const std::string& to) {

		int from_x = from[0] - 'a';
		int from_y = stoi(string() + from[1]) - 1;
		int to_x = to[0] - 'a';
		int to_y = stoi(string() + to[1]) - 1;

		auto& piece_from = squares[from_x][from_y];
		if (piece_from) {
			if (piece_from->valid_move(from_x, from_y, to_x, to_y)) {
				cout << piece_from->type() << " is moving from " << from << " to " << to << endl;
				auto& piece_to = squares[to_x][to_y];
				if (piece_to) {
					if (piece_from->color != piece_to->color) {
						cout << piece_to->type() << " is being removed from " << to << endl;
						if (auto king = dynamic_cast<King*>(piece_to.get()))
							cout << king->color_string() << " lost the game" << endl;
					}
					else {
						// piece in the from square has the same color as the piece in the to square
						cout << "can not move " << piece_from->type() << " from " << from << " to " << to << endl;
						return false;
					}
				}
				piece_to = move(piece_from);
				display();
				return true;
			}
			else { // illegal
				cout << "can not move " << piece_from->type() << " from " << from << " to " << to << endl;
				return false;
			}
		}// illegal
		else {
			cout << "no piece at " << from << endl;
			return false;
		}
	}
};

int main() {
	ChessBoard board;

	board.squares[4][0] = make_unique<ChessBoard::King>(ChessBoard::Color::WHITE);
	board.squares[1][0] = make_unique<ChessBoard::Knight>(ChessBoard::Color::WHITE);
	board.squares[6][0] = make_unique<ChessBoard::Knight>(ChessBoard::Color::WHITE);

	board.squares[4][7] = make_unique<ChessBoard::King>(ChessBoard::Color::BLACK);
	board.squares[1][7] = make_unique<ChessBoard::Knight>(ChessBoard::Color::BLACK);
	board.squares[6][7] = make_unique<ChessBoard::Knight>(ChessBoard::Color::BLACK);

	cout << "Invalid moves:" << endl;
	board.move_piece("e3", "e2");
	board.move_piece("e1", "e3");
	board.move_piece("b1", "b2");
	cout << endl;

	cout << "A simulated game:" << endl;
	board.move_piece("e1", "e2");
	board.move_piece("g8", "h6");
	board.move_piece("b1", "c3");
	board.move_piece("h6", "g8");
	board.move_piece("c3", "d5");
	board.move_piece("g8", "h6");
	board.move_piece("d5", "f6");
	board.move_piece("h6", "g8");
	board.move_piece("f6", "e8");
}