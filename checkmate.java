// import Scanner
import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    // initialize the board
    char[][] board = new char[8][8];

    // make the chess board
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if ((i + j) % 2 == 0) {
          board[i][j] = '.';
        } else {
          board[i][j] = '*';
        }
      }
    }

    // get king coord and put king on the board
    System.out.print("King coordinates: ");
    String[] kpos = input.nextLine().split(" ");
    int kx = Integer.parseInt(kpos[0]);
    int ky = Integer.parseInt(kpos[1]);
    board[kx][ky] = 'k';

    // get queen coord and put queen on the board
    System.out.print("Queen coordinates: ");
    String[] qpos = input.nextLine().split(" ");
    int qx = Integer.parseInt(qpos[0]);
    int qy = Integer.parseInt(qpos[1]);
    board[qx][qy] = 'q';

    // get rook 1 coord and put on the board
    System.out.print("Rook 1 coordinates: ");
    String[] r1pos = input.nextLine().split(" ");
    int r1x = Integer.parseInt(r1pos[0]);
    int r1y = Integer.parseInt(r1pos[1]);
    board[r1x][r1y] = 'r';

    // get rook 2 coord and put on the board
    System.out.print("Rook 2 coordinates: ");
    String[] r2pos = input.nextLine().split(" ");
    int r2x = Integer.parseInt(r2pos[0]);
    int r2y = Integer.parseInt(r2pos[1]);
    board[r2x][r2y] = 'r';

    // get bishop 1 coord and put on the board
    System.out.print("Bishop 1 coordinates: ");
    String[] b1pos = input.nextLine().split(" ");
    int b1x = Integer.parseInt(b1pos[0]);
    int b1y = Integer.parseInt(b1pos[1]);
    board[b1x][b1y] = 'b';

    // get bishop 2 coord and put on board
    System.out.print("Bishop 2 coordinates: ");
    String[] b2pos = input.nextLine().split(" ");
    int b2x = Integer.parseInt(b2pos[0]);
    int b2y = Integer.parseInt(b2pos[1]);
    board[b2x][b2y] = 'b';

    // get knight 1 coord and put on board
    System.out.print("Knight 1 coordinates: ");
    String[] n1pos = input.nextLine().split(" ");
    int n1x = Integer.parseInt(n1pos[0]);
    int n1y = Integer.parseInt(n1pos[1]);
    board[n1x][n1y] = 'n';

    // get knight 2 coord and put on board
    System.out.print("Knight 2 coordinates: ");
    String[] n2pos = input.nextLine().split(" ");
    int n2x = Integer.parseInt(n2pos[0]);
    int n2y = Integer.parseInt(n2pos[1]);
    board[n2x][n2y] = 'n';

    // print the board
    printBoard(board);

    // call the check and the checkmate method
    boolean check = isCheck(board, kx, ky, qx, qy, r1x, r1y, r2x, r2y, b1x, b1y, b2x, b2y, n1x, n1y, n2x, n2y);
    boolean checkmate = isCheckmate(board, kx, ky, qx, qy, r1x, r1y, r2x, r2y, b1x, b1y, b2x, b2y, n1x, n1y, n2x, n2y);

    // if it's a check, then check if it's also a checkmate
    if (check == true && checkmate == true) {
        System.out.println("true");
    } 
    else {
      System.out.println("false");
    }
  }

  // board printing method
  public static void printBoard(char[][] board) {
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        System.out.print(board[i][j]);
        if (j < 8 - 1) {
          System.out.print(" ");
        }
      }
      System.out.println();
    }
  }

  // check method
  public static boolean isCheck(char[][] board, int kx, int ky, int qx, int qy, int r1x, int r1y, int r2x, int r2y, int b1x, int b1y, int b2x, int b2y, int n1x, int n1y, int n2x, int n2y) {
    // queen
    int qxDiff = Math.abs(qx - kx);
    int qyDiff = Math.abs(qy - ky);

    if ((kx != qx && ky == qy) || (kx == qx && ky != qy) || (qxDiff == qyDiff)) {
      return true;
    }

    // rooks
    if ((r1x == kx || r2x == kx) || (r1y == ky || r2y == ky)) {
      return true;
    }

    // bishops
    int b1xDiff = Math.abs(b1x - kx);
    int b1yDiff = Math.abs(b1y - ky);

    int b2xDiff = Math.abs(b2x - kx);
    int b2yDiff = Math.abs(b2y - ky);

    if (b1xDiff == b1yDiff || b2xDiff == b2yDiff) {
      return true;
    }

    // knights
    int[] nx = {-2, -2, -1, -2, 1, 1, 2, 2};
    int[] ny = {-1, 1, -2, 2, -2, 2, -1, 1};

    for (int i = 0; i < 8; i++) {
      int newnx = n1x + nx[i];
      int newny = n1y + ny[i];

      if (newnx == kx && newny == ky) {
        return true;
      }
    }

    for (int i = 0; i < 8; i++) {
      int newnx = n2x + nx[i];
      int newny = n2y + ny[i];

      if (newnx == kx && newny == ky) {
        return true;
      }
    }

    return false;
  }

  // checkmate method
  public static boolean isCheckmate(char[][] board, int kx, int ky, int qx, int qy, int r1x, int r1y, int r2x, int r2y, int b1x, int b1y, int b2x, int b2y, int n1x, int n1y, int n2x, int n2y){
    int[][] possibleKingMoves = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 1}, {1, 0}};

    // used to check for checkmate 
    int count = 0;

    for (int[] kingMove : possibleKingMoves) {
      int x = kx + kingMove[0];
      int y = ky + kingMove[1];

      // if out of bounds skip this iteration
      if (x < 0 || x >= 8 || y < 0 || y >= 8) {
        continue;
      }

      // queen
      int qxDiff = Math.abs(qx - x);
      int qyDiff = Math.abs(qy - y);

      if ((x != qx && y == qy) || (x == qx && y != qy) || (qxDiff == qyDiff)) {
        board [x][y] = 'x';
        //return true;
      }

      // rooks
      if ((r1x == x || r2x == x) || (r1y == y || r2y == y)) {
        board [x][y] = 'x';
        //return true;
      }

      // bishops
      int b1xDiff = Math.abs(b1x - x);
      int b1yDiff = Math.abs(b1y - y);

      int b2xDiff = Math.abs(b2x - x);
      int b2yDiff = Math.abs(b2y - y);

      if (b1xDiff == b1yDiff || b2xDiff == b2yDiff) {
        board [x][y] = 'x';
        //return true;
      }

      // knights
      int[] nx = {-2, -2, -1, -2, 1, 1, 2, 2};
      int[] ny = {-1, 1, -2, 2, -2, 2, -1, 1};

      for (int i = 0; i < 8; i++) {
        int newnx = n1x + nx[i];
        int newny = n1y + ny[i];

        if (newnx == x && newny == y) {
          board [x][y] = 'x';
          //return true;
        }
      }

      for (int i = 0; i < 8; i++) {
        int newnx = n2x + nx[i];
        int newny = n2y + ny[i];

        if (newnx == x && newny == y) {
          board [x][y] = 'x';
          //return true;
        }
      }  
    }

    // print the board
    System.out.println(" ");
    printBoard(board);

    // if all 8 surrouding places are marked x, then it's checkmate
    for (int[] kingMove : possibleKingMoves) {
      int x = kx + kingMove[0];
      int y = ky + kingMove[1]; 

      // if out of bounds add to count and skip this iteration
      if (x < 0 || x >= 8 || y < 0 || y >= 8) {
        count ++;
        continue;
      }

      if (board[x][y] == 'x') {
        count ++;
      }
    }
    if (count == 8){
      return true;
    }
    return false;
  }
}