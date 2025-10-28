# Diagramme
```mermaid
classDiagram
    direction BT
    class ArtificialPlayer {
        + ArtificialPlayer(String)
        int[] move
    }
    class Board {
        + Board(int, int)
        ~ int sizeLigne
        ~ int sizeColonne
        - int winLength
        + initBoard() void
        + toArray() Cell[][]
        + getCell(int, int) Cell
        - inBounds(int, int) boolean
        int winLength
        int sizeLigne
        int sizeColonne
        boolean boardFull
    }
    class Cell {
        + Cell()
        - String symbol
        boolean empty
        String representation
        String symbol
    }
    class GameController {
        + GameController()
        - Board board
        # initBoard() void
        - playMove(int, int, Player) void
        + display() void
        - switchPlayer() void
        + start() void
        - checkWin(int, int) boolean
        - countInDirection(int, int, int, int, String) int
        Board board
        Player currentPlayer
        boolean over
        List~int[]~ available
    }
    class GamerInterface {
        <<Interface>>

    }
    class InteractionUtilisateur {
        + InteractionUtilisateur()
        + askInt(String) int
        + askLine(String) String
    }
    class Main {
        + Main()
        + main(String[]) void
    }
    class Menu {
        + Menu()
        - Scanner scanner
        Scanner scanner
    }
    class Player {
        + Player(String, InteractionUtilisateur, ViewConsole)
        - String representation
        String representation
        int[] move
    }
    class TicTacToeController {
        + TicTacToeController()
    }
    class ViewConsole {
        + ViewConsole()
        + print(String) void
        + println(String) void
        + afficherPlateau(Cell[][]) void
    }

    ArtificialPlayer  -->  Player
    Board  ..>  Cell : «create»
    Board "1" *--> "board *" Cell
    GameController  ..>  ArtificialPlayer : «create»
    GameController "1" *--> "board 1" Board
    GameController  ..>  Board : «create»
    GameController  ..>  InteractionUtilisateur : «create»
    GameController "1" *--> "io 1" InteractionUtilisateur
    GameController "1" *--> "players *" Player
    GameController  ..>  Player : «create»
    GameController "1" *--> "viewConsole 1" ViewConsole
    GameController  ..>  ViewConsole : «create»
    InteractionUtilisateur "1" *--> "viewConsole 1" ViewConsole
    Main  ..>  TicTacToeController : «create»
    Player "1" *--> "io 1" InteractionUtilisateur
    Player "1" *--> "viewConsole 1" ViewConsole
    TicTacToeController  -->  GameController
```

```mermaid
classDiagram
    class Main {
        +void main(String[] args)
    }

    class TicTacToe {
        +SIZE : int
        -board : Cell[][]
        -scanner : java.util.Scanner
        -players : Player[2]
        -currentPlayerIndex : int

        +TicTacToe()
        +void start()
        +boolean isOver()
        +int[] getMoveFromPlayer()
        +void setOwner(int x, int y, Player player)
        +void display()

        -Player getCurrentPlayer()
        -void switchPlayer()
        -boolean isBoardFull()
        -boolean hasThreeFrom(int x, int y)
        -int countInDirection(int x, int y, int dx, int dy, String symbol)
        -void playMove(int row, int col, Player player)
        -void initBoard()
    }

    class Menu {
        -scanner : java.util.Scanner
        +Menu()
        +java.util.Scanner getScanner()
    }

    class Cell {
        -symbol : String
        +String getRepresentation()
        +boolean isEmpty()
        +String getSymbol()
        +void setSymbol(String symbol)
    }

    class Player {
        -representation : String
        +Player(String representation)
        +String getRepresentation()
    }

%% Relations
    Main --> TicTacToe
    TicTacToe --> Cell
    TicTacToe -->  Player
    TicTacToe --> Menu





```

Architecture MVC N°1

```mermaid
classDiagram


class TicTacToeController {
  
  -board : Cell[][]
  -player1 : Player
  -player2 : Player  
  -InterractionUtilisateur : interractionutilisateur
  -currentplayer : Player

  +void start()
  -void play()
  -void switchPlayer()
  -boolean isValidCoor()
  
}

class ViewConsole {
      

  +void displayMessage(String message)
  +void displayboard(Board)
  

}

class Cell {
  -representation : String
  

  +String getRepresentation()
  +boolean isEmpty()
  +void setRepresentation(String representation)


}

class Board {
    -board : Cell [][]
    -size int

    +boolean isOver()
    -boolean isWin()
    -boolean isFull()
    -void initBoard()
    +boolean checkAlignement()
    +int getSize()
    +void setOwner(int coorX,int coorY, Player player)



}

class InterractionUtilisateur {
    -Scanner : scanner

    +askInt()

}

class Player {
    <<Abstract>>
  -representation : String
  
  +String getRepresentation()
}

class ArtificialPlayer{
    -Random random

    int getRandom()

    
}

class RealPlayer {
    
}

%% Relation



ArtificialPlayer --|> Player
RealPlayer  --|> Player



Board"1"*--"1.."Cell
TicTacToeController"1"*--"1"Board
TicTacToeController "1" o-- "2" Player
TicTacToeController "1" o-- "1" InterractionUtilisateur
TicTacToeController "1" o-- "1" ViewConsole
 ```

Architecture MVC N°2
```mermaid

classDiagram


class TicTacToeController {

    -tictactoeview : TicTacToeView
    -tictactoemodel : TicTactoeModel
  
  +void start()
  -void play()
  -void switchPlayer()
  -boolean isValidCoor()
  
}

class ViewConsole {
      

  +void displayMessage(String message)
  +void displayboard(Board)
  

}

class Cell {
  -representation : String
  

  +String getRepresentation()
  +boolean isEmpty()
  +void setRepresentation(String representation)


}

class TicTacToeView {

-interractionutilisateur : InterractionUtilisateur
-viewconsole : ViewConsole
}

class TicTactoeModel {

  -board : Cell[][]
  -player1 : Player
  -player2 : Player  
  -currentplayer : Player

    
}

class Board {
    -board : Cell [][]
    -size int

    +boolean isOver()
    -boolean isWin()
    -boolean isFull()
    -void initBoard()
    +boolean checkAlignement()
    +int getSize()
    +void setOwner(int coorX,int coorY, Player player)



}

class InterractionUtilisateur {
    -Scanner : scanner

    +askInt()

}

class Player {
    <<Abstract>>
  -representation : String
  
  +String getRepresentation()
}

class ArtificialPlayer{
    -Random random

    int getRandom()

    
}

class RealPlayer {
    
}

%% Relation



ArtificialPlayer --|> Player
RealPlayer  --|> Player



Board"1"*--"1.."Cell
TicTactoeModel"1"*--"1"Board
TicTactoeModel "1" o-- "2" Player
TicTacToeView "1" o-- "1" InterractionUtilisateur
TicTacToeView "1" o-- "1" ViewConsole
TicTacToeController "1" o-- "1" TicTacToeView
TicTacToeController "1" o-- "1" TicTactoeModel




```




