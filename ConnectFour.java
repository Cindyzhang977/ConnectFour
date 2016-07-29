/**
 * 
 */
import java.util.*;
/**
 * @author Cindy Zhang
 *
 */
public class ConnectFour {
	static Scanner s = new Scanner(System.in);
	static char board [][] = new char [6][7]; 

	/*initialize - starts/resets the game with a board of all '0's
	 * @param: char [][] array - passes in a 2D array that will become the start game board
	 * @return: void
	 * uses for loop to assign each value in char[][] board to '0'
	 */
	public static void initialize(char[][] array){
		for (int i = 0; i < array.length; i++){
			for (int j = 0; j < array[i].length; j++){
				array[i][j] = 'o';
			}
		}
	}

	/*processMove - checks if user input is valid for each move
	 * @param: char token - correct player token to be placed in position
	 * @return: int
	 * converts user input from String to int if the user entered an integer
	 * and either returns that valid integer or tells user that entry is invalid
	 */
	public static int processMove(char token){
		System.out.print("Player " + token + " enter a column number between 0 and 6: ");
		String x = s.next();
		int newX = 0;

		//keep telling user to enter an integer
		//once integer is entered, then convert the String input to int
		isInt(x);
		while (isInt(x) == false){
			System.out.println("Input invalid. Try again: ");
			x = s.next();
			isInt(x);
		}
		if(isInt(x) == true){
			newX = Integer.parseInt(x);
		}
		return newX;

	}

	/*placeToken - places a token where user wants
	 * @param:int col - the column number where the token wants to be placed
	 * @return: boolean
	 * uses for loop to determine if selected column is full
	 * returns true if column is full
	 */
	public static boolean checkFull(int col){
		int count = 0;
		for (int i = board.length - 1; i >= 0; i--){
			if(board[i][col] != 'o'){
				count ++;
			}	
		}
		if(count == board.length){
			return true;
		}
		else{
			return false;
		}
	}

	/*placeToken - places a token where user wants
	 * @param: char token - correct player token to be placed in position
	 * 			int col - the column number where the token should be placed
	 * @return: void
	 * uses if/else if statements and a for loop to determine if the position desired is available for a token
	 */
	public static void placeToken(int col, char token){
		//place valid token in position using for loop to find first blank position in column
		if (col >= 0 && col < board[0].length){
			int count = 0;
			for (int i = board.length - 1; i >= 0; i--){
				count++;
				if(board[i][col] == 'o'){
					board[i][col] = token;
					i = -1;
				}
				//if column is full, entry is invalid
				else if (count == board.length){
					System.out.println("Invalid entry. Column chosen is already full. Next player's turn.");
					i = -1;
				}
			}
		}

		//if invalid position entered, player loses a turn //check this
		else{
			System.out.println("Invalid entry. Next player's turn.");
		}
	}

	/*horizontal - returns true after a move is a player has 4 in a row left to right
	 *  @param: char[][]board - the current game board
	 * 			char token- the token the player put down
	 * @return: boolean
	 * for loop that goes through array to determine if there are 4 tokens in a row horizontally
	 */
	public static boolean horizontal(char[][] board, char token){
		boolean b = false;
		for (int i = 0; i < board.length; i++){
			int horizontal = 0;
			for (int j = 0; j < board[i].length; j++){
				if (board[i][j] == token){
					horizontal++;
					if (horizontal >= 4){
						b = true;
						return b;
					}
				}
				else{
					horizontal = 0;
				}
			}
		}
		return b;
	}

	/*vertical - returns true after a move is a player has 4 in a row up and down
	 *  @param: char[][]board - the current game board
	 * 			char token- the token the player put down
	 * @return: boolean
	 * for loop that goes through array to determine if there are 4 tokens in a row vertically
	 */
	public static boolean vertical(char[][] board, char token){
		boolean b = false;
		for (int i = 0; i < board[0].length; i++){
			int vertical = 0;
			for (int j = 0; j < board.length; j++){
				if (board[j][i] == token){
					vertical++;
					if (vertical >= 4){
						b = true;
						return b;
					}
				}
				else{
					vertical = 0;
				}
			}
		}
		return b;
	}

	/*diagonalR - returns true after a move is a player has 4 in a row diagonally down to the right
	 *  @param: char[][]board - the current game board
	 * 			char token- the token the player put down
	 * @return: boolean
	 * for loop that goes through array to determine if there are 4 tokens in a row diagonally to the right
	 */
	public static boolean diagonalR (char[][] board, char token){
		for(int i = 0; i < board.length; i++){
			int diagR = 0;
			for (int j = 0; j < board[i].length; j++){
				if(board[i][j] == token){
					int r = i;
					int c = j;
					while(r < board.length && c < board[0].length && board[r][c] == token){
						diagR++;
						r++;
						c++;
					}
				}
				if(diagR >= 4){
					return true;
				}
				else{
					diagR = 0;
				}
			}
		}
		return false;
	}

	/*diagonalL - returns true after a move is a player has 4 in a row diagonally down to the left
	 *  @param: char[][]board - the current game board
	 * 			char token- the token the player put down
	 * @return: boolean
	 * for loop that goes through array to determine if there are 4 tokens in a row diagonally to the left
	 */
	public static boolean diagonalL(char[][] board, char token){
		for(int i = 0; i < board.length; i++){
			int diagR = 0;
			for (int j = 0; j < board[i].length; j++){
				if(board[i][j] == token){
					int r = i;
					int c = j;
					while(r < board.length && c >= 0 && board[r][c] == token){
						diagR++;
						r++;
						c--;
					}
				}
				if(diagR >= 4){
					return true;
				}
				else{
					diagR = 0;
				}
			}
		}
		return false;
	}

	/*checkWin - returns true after a move is a player has won
	 * @param: char[][]board - the current game board
	 * 			char token- the token the player put down
	 * @return: boolean
	 * calls all four methods of determining a win using if/else statements
	 */
	public static boolean checkWin(char[][] board, char token){
		if(horizontal(board, token) == true){
			return true;
		}
		else if(vertical(board, token) == true){
			return true;
		}
		else if (diagonalR(board, token) == true){
			return true;
		}
		else if (diagonalL(board, token) == true){
			return true;
		}
		return false;
	}

	/*checkTie - returns true if the board is full, indicating a tie
	 * @param: char[][]board- passes in current game board
	 * @return: boolean
	 * for each loop that goes through entire array to determine if all spaces are full and no one has won
	 */
	public static boolean checkTie(char[][]board){
		boolean b = false;
		int count = 0;
		for (int i = 0; i < board.length; i++){
			for (int j = 0; j < board[i].length; j++){
				if (board[i][j] != 'o'){
					count ++;
				}
			}
		}
		if(count == board.length * board[0].length){
			System.out.println("It's a tie! Good game!");
			b = true;
			return b;
		}
		return b;
	}

	/*isInt - returns true if user input is an integer
	 * @param: String str - string read from console
	 * @return: boolean
	 * compares string to integer values 0 - 9, and returns true if so
	 */
	public static boolean isInt(String x){
		boolean b = false;
		if (x.equals("0") || x.equals("1") || x.equals("2") || x.equals("3") || x.equals("4") ||
				x.equals("5") || x.equals("6") || x.equals("7") || x.equals("8") || x.equals("9")){
			b = true;
			return b;
		}
		return b;
	}

	/*dumbMove - simulate a dumb computer
	 * @param: none
	 * @return: int
	 * use the Math.Random() function to place a token in a random valid spot 
	 */
	public static int dumbMove(){
		int col = (int)(Math.random() * 6);
		while(checkFull(col) == true){
			col = (int)(Math.random() * 6);
		}
		return col;
	}

	/*smartV - helps computer wisely place tokens vertically
	 * @param: char t - an arbitrary token to look for (either self token to win, or opponent token to block)
	 * @return: int - the column where token should be placed
	 * for loops to check how many tokens there are in a row vertically 
	 * depending on parameter passes in, computer would either win or block
	 */
	public static int smartV(char t){
		int col = -1;
		for (int i = 0; i < board[0].length; i++){
			int v = 0;
			for (int j = 0; j < board.length; j++){
				if (board[j][i] == t){
					v++;
					if(v == 3){
						if((j - 3) >= 0 && board[j - 3][i] == 'o' && checkFull(i) != true){
							col = i;
							return col;
						}
					}
				}
				else{
					v = 0;
				}
			}
		}
		return col;
	}

	/*smartH - helps computer wisely place tokens horizontally
	 * @param: char t - an arbitrary token to look for (either self token to win, or opponent token to block)
	 * @return: int - the column where token should be placed
	 * for loops to check how many tokens there are in a row horizontally 
	 * depending on parameter passes in, computer would either win or block
	 */
	public static int smartH(char t){
		int col = -1;
		for (int i = 0; i < board.length; i++){
			int h = 0;
			for (int j = 0; j < board[i].length; j++){
				if (board[i][j] == t){
					h++;
					if(h == 3){
						//place token only if there is a token below desired spot to serve as platform 
						//to get 4 in a row or if that is the bottom row
						if((j + 1) < board[i].length && checkFull(j + 1) != true && 
								board[i][j+1] == 'o' && (i + 1) < board.length && board[i+1][j+1] != 'o'){
							col = j + 1;
							return col;	
						}
						else if((j + 1) < board[i].length && checkFull(j + 1) != true && 
								board[i][j+1] == 'o' && i == board.length - 1){
							col = j + 1;
							return col;	
						}
						else if((j - 3) >= 0 && checkFull(j - 3) != true && 
								board[i][j-3] == 'o' && (i + 1) < board.length && board[i+1][j-3] != 'o'){
							col = j - 3;
							return col;		
						}
						else if((j - 3) >= 0 && checkFull(j - 3) != true && 
								board[i][j-3] == 'o' && i == board.length - 1){
							col = j - 3;
							return col;		
						}
					}
				}
				else{
					h = 0;
				}
			}
		}
		return col;
	}

	/*smartDR - helps computer wisely place tokens down to diagonally to the right
	 * @param: char t - an arbitrary token to look for (either self token to win, or opponent token to block)
	 * @return: int - the column where token should be placed
	 * for loops to check how many tokens there are in a row diagonally to the right
	 * depending on parameter passes in, computer would either win or block
	 */
	public static int smartDR(char t){
		int col = -1;
		for(int i = 0; i < board.length; i++){
			int dr = 0;
			for (int j = 0; j < board[i].length; j++){
				if(board[i][j] == t){
					int r = i;
					int c = j;
					while(r < board.length && c < board[0].length && board[r][c] == t){
						dr++;
						r++;
						c++;
					}
					if(dr >= 3){
						//System.out.println("First token dr: " + i + " " + j);
						//System.out.println("Last token dr: " + r + " " + c);
						//System.out.println("dr: " + dr);
						//place token only if there is a token below desired spot to serve as platform to block opponent
						//or if that is the bottom row (avoid placing token to help opponent win)
						if((c - 4) >=0  && checkFull(c - 4) != true &&
								(r - 4) > 0 && board[r-4][c-4] == 'o' && board[r-3][c-4] != 'o'){
							col = c - 4;
							return col;
						}
						else if(c < board[i].length && checkFull(c) != true &&
								r < board.length && board[r][c] == 'o' && 
								(r + 1) < board.length && board[r+1][c] != 'o'){
							col = c;
							return col;
						}
						else if(c < board[i].length && checkFull(c) != true &&
								r == board.length - 1 && board[r][c] == 'o'){
							col = c;
							return col;
						}
						else{
							dr = 0;
						}
					}
					else{
						dr = 0;
					}
				}
			}
		}
		return col;
	}

	/*smartDL - helps computer wisely place tokens diagonally down to the left
	 * @param: char t - an arbitrary token to look for (either self token to win, or opponent token to block)
	 * @return: int - the column where token should be placed
	 * for loops to check how many tokens there are in a row diagonally to the left
	 * depending on parameter passes in, computer would either win or block
	 */
	public static int smartDL(char t){
		int col = -1;
		for(int i = 0; i < board.length; i++){
			int dl = 0;
			for (int j = 0; j < board[i].length; j++){
				if(board[i][j] == t){
					int r = i;
					int c = j;
					while(r < board.length && c >= 0 && board[r][c] == t){
						dl++;
						r++;
						c--;
					}
					if(dl >= 3){
						//System.out.println("First token dl: " + i + " " + j);
						//System.out.println("Last token dl: " + r + " " + c);
						//System.out.println("dl: " + dl);
						//place token only if there is a token below desired spot to serve as platform to get 4 in a row
						//or if that is the bottom row
						if((c + 4) < board[i].length  && checkFull(c+4) != true &&
								(r - 4) >= 0 && board[r-4][c+4] == 'o' && board[r-3][c+4] != 'o'){
							col = c + 4;
							return col;
						}
						else if(c >= 0 && checkFull(c) != true &&
								r < board.length && board[r][c] == 'o' &&
								(r + 1) < board.length && board[r+1][c] != 'o'){
							col = c;
							return col;
						}
						else if(c >= 0 && checkFull(c) != true &&
								r == board.length - 1 && board[r][c] == 'o'){
							col = c;
							return col;
						}
						else{
							dl = 0;
						}
					}
					else{
						dl = 0;
					}
				}
			}
		}
		return col;
	}
	

	/*smartH2 - helps computer wisely place tokens horizontally in situations like **-* or *-**
	 * @param: char t - an arbitrary token to look for (either self token to win, or opponent token to block)
	 * @return: int - the column where token should be placed
	 * for loops to check how many tokens there are in a row horizontally 
	 * depending on parameter passes in, computer would either try to win win or block
	 */
	public static int smartH2(char t){
		int col = -1;
		for (int i = 0; i < board.length; i++){
			int h = 0;
			for (int j = 0; j < board[i].length; j++){
				if (board[i][j] == t){
					h++;
					if(h == 2){
						//System.out.println("Last token h2: " + i + " " + j);
						//place token only if opponent has a chance to get 3 in row 
						//there is a token below desired spot to serve as platform for opponent to get 3 in a row
						//or if that is the bottom row ex: -**-
						if((j + 1) < board[i].length && board[i][j+1] == 'o' && (j - 2) >= 0 && board[i][j-2] == 'o'){
							if((i + 1) > board.length){
								if(board[i+1][j+1] != 'o'){
									col = j + 1;
									return col;		
								}
								else if(board[i+1][j-2] != 'o'){
									col = j - 2;
									return col;		
								}
							}
							else if(i == board.length - 1){
								col = j + 1;
								return col;	
							}
						}
						//place token only if opponent has a chance to get 4 in row
						//ex: **-* 
						else if((j + 2) < board[i].length && board[i][j+1] == 'o' && board[i][j+2] == t){
							if((i+1) < board.length && board [i+1][j+1] != 'o'){
								col = j + 1;
								return col;
							}
							else if(i == board.length - 1){
								col = j + 1;
								return col;
							}
						}
						//another option: *-**
						else if((j - 3) >= 0 && board[i][j-2] == 'o' && board[i][j-3] == t){
							if((i+1) < board.length && board [i+1][j-2] != 'o'){
								col = j - 2;
								return col;
							}
							else if(i == board.length - 1){
								col = j - 2;
								return col;
							}
						}
					}
				}
			}
		}
		return col;
	}

	/*smartMove - simulates smart computer
	 * @param: char self - the computer's token 
	 * 			char opponent - the opponent's token
	 * @return: int - the column where token should be placed
	 * for loops to check how many tokens there are in a row vertically/horizontally/diagonally
	 * first use for loops to check if there is a direct winning possibility, then checks if there is a need to block
	 * if none of the above, returns a random valid column number
	 */
	public static int smartMove(char self, char opponent){
		int col = 0;

		//check horizontally for winning possibility
		col = smartH(self);
		if (col != -1){
			//System.out.println("Return from h-win");
			return col;
		}

		//check vertically for winning possibility
		col = smartV(self);
		if (col != -1){
			//System.out.println("Return from v-win");
			return col;
		}
		
		//check vertically if need to block 
		col = smartV(opponent);
		if (col != -1){
			//System.out.println("Return from v-block");
			return col;
		}

		//check horizontally to check if there is an immediate need to block (opponent has 3 in a row)
		col = smartH(opponent);
		if (col != -1){
			//System.out.println("Return from h-block");
			return col;
		}

		//check diagonally to the right for winning possibility
		col = smartDR(self);
		if(col != -1){
			//System.out.println("Return from dr-win");
			return col;
		}

		//check diagonally to the left for winning possibility
		col = smartDL(self);
		if (col != -1){
			//System.out.println("Return from dl-win");
			return col;
		}

		//check diagonally to the right if need to block (opponent has 3 in a row)
		col = smartDR(opponent);
		if(col != -1){
			//System.out.println("Return from dr-block");
			return col;
		}

		//check diagonally to the left if immediate need to block (opponent has 3 in a row)
		col = smartDL(opponent);
		if (col != -1){
			//System.out.println("Return from dl-block");
			return col;
		}

		//check horizontally for potential threat (2 opponent tokens in a row)
		col = smartH2(opponent);
		if(col != -1){
			//System.out.println("Return from H2 block");
			return col;
		}
		
		//check horizontally for win in **-* or *-**
		col = smartH2(self);
		if(col != -1){
			//System.out.println("Return form H2 win");
			return col;
		}
		
		//generate random column since it doesn't matter where computer places token
		col = dumbMove();
		//System.out.println("Return random");
		return col;
	}

	/*human - human vs human
	 * @param: none
	 * @return: void
	 * uses while loop to allow each player to enter a token as long as there is no winner and no tie
	 */
	public static void human(){
		System.out.println("You have chosen to play against another human. Game on!");
		initialize(board);
		print(board);
		int moveNum = 0;
		char token = '#';
		//while loop and boolean value to check if there's a winner
		boolean win = false;
		while(win == false){
			moveNum++;
			if (moveNum % 2 == 1){
				token = '#';
			}
			else{
				token = '*';
			}
			//allows user to enter a column and accepts it if column entered is valid
			int col = processMove(token);
			//places valid token in array and prints current game board
			placeToken(col, token);
			print(board);
			win = checkWin(board, token);
			if(win == true){
				System.out.println("Congratulations Player " + token + ", you won!");
			}
			checkTie(board);
			if (checkTie(board) == true){
				win = true;
			}
		}
		//return to main menu
		System.out.println("\n\n");
		menu();
	}

	/*dumb - human vs dumb computer
	 * @param: none
	 * @return: void
	 * allows one human to play against a dumb computer that inputs valid tokens randomly
	 */
	public static void dumb(){
		System.out.println("You have chosen to play with the dumb CPU. Next time pick a harder oponent.");
		initialize(board);
		print(board);
		int moveNum = 0;
		char token = '#';
		//while loop and boolean value to check if there's a winner
		boolean win = false;
		while(win == false){
			moveNum++;
			if (moveNum % 2 == 1){
				token = '#';
				int col = processMove(token);
				placeToken(col, token);
				print(board);
				win = checkWin(board, token);
				if(win == true){
					System.out.println("Congratulations, you won!");
				}
			}
			else{
				token = '*';
				int col = dumbMove();
				placeToken(col, token);
				System.out.println("The CPU has placed token in column " + col);
				print(board);
				win = checkWin(board, token);
				if(win == true){
					System.out.println("Oh no! You've been beat by a dumb computer!");
				}
			}
			checkTie(board);
			if (checkTie(board) == true){
				win = true;
			}
		}
		//return to main menu
		System.out.println("\n\n");
		menu();
	}

	/*smart - human vs smart computer
	 * @param: none
	 * @return: void
	 * allows one human to play against a smart computer that will 
	 * strategically place tokens to try to win
	 */
	public static void smart(){ 
		System.out.println("You have chosen to play with the smart CPU. Good luck!");
		initialize(board);
		print(board);
		int moveNum = 0;
		char token = '#';
		//while loop and boolean value to check if there's a winner
		boolean win = false;
		while(win == false){
			moveNum++;
			if (moveNum % 2 == 1){
				token = '#';
				int col = processMove(token);
				placeToken(col, token);
				print(board);
				win = checkWin(board, token);
				if(win == true){
					System.out.println("Congratulations, you won!");
				}
			}
			else{
				token = '*';
				int col = smartMove(token, '#');
				placeToken(col, token);
				System.out.println("The CPU has placed token in column " + col);
				print(board);
				win = checkWin(board, token);
				if(win == true){
					System.out.println("You lose! Told you the computer is smart!");
				}
			}
			checkTie(board);
			if (checkTie(board) == true){
				win = true;
			}
		}
		//return to main menu
		System.out.println("\n\n");
		menu();
	}

	/*computers - smart computer vs dumb computer
	 * @param: none
	 * @return: void
	 * user watches as smart computer will try to win while dumb computer places tokens randomly
	 * end result: smart computer wins
	 */
	public static void computers(){
		System.out.println("You have chosen to watch the smart CPU beat the dumb CPU.");
		initialize(board);
		print(board);
		int moveNum = 0;
		char token = '#';
		//while loop and boolean value to check if there's a winner
		boolean win = false;
		while(win == false){
			moveNum++;
			if (moveNum % 2 == 1){
				token = '#';
				int col = dumbMove();
				placeToken(col, token);
				System.out.println("The dumb CPU has placed token in column " + col);
				print(board);
				win = checkWin(board, token);
				if(win == true){
					System.out.println("The dumb computer won! That's just dumb luck.");
					menu();
					System.out.println("\n\n");
				}
				System.out.print("Enter anything to continue or QUIT to return to main menu: ");
				String input = s.next();
				if(input.equalsIgnoreCase("quit")){
					System.out.println("\n\n");
					menu();
				}
			}
			else{
				token = '*';
				int col = smartMove(token, '#');
				placeToken(col, token);
				System.out.println("The smart CPU has placed token in column " + col);
				print(board);
				win = checkWin(board, token);
				if(win == true){
					System.out.println("The smart computer won!");
					System.out.println("\n\n");
					menu();
				}
				System.out.print("Enter anything to continue or QUIT to return to main menu: ");
				String input = s.next();
				if(input.equalsIgnoreCase("quit")){
					System.out.println("\n\n");
					menu();
				}
			}
			checkTie(board);
			if (checkTie(board) == true){
				win = true;
				menu();
			}
		}
	}

	/*print - prints out the game board
	 * @param: char[][] array - passes in a 2D array game board to be printed
	 * @return: void
	 * uses a for loop to print out each element in 2D array to show the current game board
	 */
	public static void print(char[][] array){
		System.out.println("Current board: ");
		System.out.println(0 + " " + 1 + " " + 2 + " " + 3 + " " + 4 + " " + 5 + " " + 6); 
		for (int i = 0; i < array.length; i++){
			for (int j = 0; j < array[i].length; j++){
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	/*menu - main menu of game
	 * @param: none
	 * @return: void
	 * while loop that loops until user enters 5 to quit 
	 * or enters another valid number (1-4) to navigate to another method
	 */
	public static void menu(){
		System.out.println("Welcome to ConnectFour! " +
				"\nEnter 1 to play against another human. " +
				"\nEnter 2 to play against dumb CPU " +
				"\nEnter 3 to play against smart CPU" + 
				"\nEnter 4 to watch dumb CPU play against smart CPU" +
		"\nEnter 5 to quit");
		//check for valid input
		boolean b = false;
		String x ="";
		int newX = 0;
		while(b == false){
			System.out.println("Enter a number: ");
			x = s.next();
			if(isInt(x) == false){
				System.out.println("Invalid entry. Please try again.");
			}
			else{
				b = true;
			}
		}
		if(isInt(x) == true){
			newX = Integer.parseInt(x);
		}
		//go to desired option
		while (newX != 5){
			if(newX == 1){
				human();
				newX = 5;
			}
			else if(newX == 2){
				dumb();
				newX = 5;
			}
			else if(newX == 3){
				smart();
				newX = 5;
			}
			else if(newX == 4){
				computers();
				newX = 5;
			}
			else{
				System.out.print("Invalid entry. Please try again: ");
				x = s.next();
				newX = Integer.parseInt(x);
			}
		}	
	}
	public static void main(String[] args) {
		//start with main menu
		menu();
	}
}
