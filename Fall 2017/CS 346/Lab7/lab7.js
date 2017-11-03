/*jslint browser: true */
/*jslint white: true */
"use strict";

var N = 4;                   // number of rows and columns of tiles on the board
var board_size = 400;                 // size of the (square) board in pixels
                                      // not including borders
var tile_size;                        // size of each (square) tile in pixels
var blank = { "row": 0, "col": 0 };   // initial position of the blank
var board = [ null ];                 // no tile at position 0
var num_shuffle_steps = 10;  // number of random moves when shuffling board

/* return true iff the given tile is directly to the right of the blank */
function is_to_the_right_of_blank(tile) {
  return blank.col != N -1 && tile === board[blank.row][blank.col + 1];
}

/* return true iff the given tile is directly to the left of the blank */
function is_to_the_left_of_blank(tile) {
  return blank.col != 0 && tile === board[blank.row][blank.col - 1];
}

/* return true iff the given tile is directly above the blank */
function is_above_blank(tile) {
  return blank.row != 0 && tile === board[blank.row - 1][blank.col];
}

/* return true iff the given tile is directly under the blank */
function is_below_blank(tile) {
  return blank.row != N -1 && tile === board[blank.row + 1][blank.col];
}

/* return true iff the given tile is immediately adjacent to the blank */
function is_next_to_blank(tile) {
  return is_to_the_right_of_blank(tile) || is_to_the_left_of_blank(tile) || is_above_blank(tile) || is_below_blank(tile);
}

/* return true iff the tiles are in their initial configuration */
function game_over() {
  var boardSingleArray = [];
  for(var i = 0; i < N; i+=1){
    boardSingleArray = boardSingleArray.concat(board[i]);
  }
  for(var i = 1; i < N*N; i+=1){
    if(boardSingleArray[i] != document.getElementById(i)) {
      return false;
    }
  }
  return true;
}

/* randomly select and return a tile that is adjacent to the blank */
function pick_tile() {
  var tileLeft = ((blank.col != 0)) ? board[blank.row][blank.col - 1]: -1;
  var tileRight = ((blank.col != N - 1)) ? board[blank.row][blank.col + 1]: -1;
  var tileUp = ((blank.row != 0)) ? board[blank.row - 1][blank.col]: -1;
  var tileBelow = ((blank.row != N - 1)) ? board[blank.row + 1][blank.col]: -1;
  var tile = -1;
  var randomizer;
  while(tile === -1){
    randomizer = Math.floor(Math.random()*4);
    switch (randomizer) {
      case 0:
        tile = tileLeft;
        break;
      case 1:
        tile = tileRight;
        break;
      case 2:
        tile = tileUp;
        break;
      case 3:
        tile = tileBelow;
        break;
      default:
        break;
    }
  }
  return tile;
}

/* position the given tile at the given position on the board
   must update the tile and the board
*/
function position_tile(tile,row,col) {
  if(is_to_the_right_of_blank(tile)){
    board[row][col + 1] = null;
    board[row][col] = tile;
    blank.col+=1;
  }
  else if(is_to_the_left_of_blank(tile)){
    board[row][col - 1] = null;
    board[row][col] = tile;
    blank.col-=1;
  }
  else if(is_above_blank(tile)){
    board[row - 1][col] = null;
    board[row][col] = tile;
    blank.row-=1;
  }
  else{
    board[row + 1][col] = null;
    board[row][col] = tile;
    blank.row+=1;
  }
}

/* move the given tile to the blank location
   (assume that the given tile is adjacent to the blank before the move)
   must update the tile, the blank, and the board
*/
function swap_with_blank(tile) {
  tile.style.left = blank.col * board_size/N + "px";
  tile.style.top = blank.row * board_size/N + "px";
  position_tile(tile, blank.row, blank.col);
}

/* event handler for a click on a tile
   if the clicked tile is adjacent to the blank, move the tile into the blank
   position_tile (otherwise do nothing)
   if this move returns the board to its initial configuration, pop up a
   "Well done!" message
*/
function process_click(tile) {
  if(is_next_to_blank(tile)){
    swap_with_blank(tile);
  }
  if(game_over()){
    window.alert("Well done!");
  }
}

/* empty the board of its tiles and recreate enough
   tiles to fill the board using the current value of N
*/
function clearAndRefillBoard() {
  var boardNode = document.getElementById("board");
  board = [ null ];
  blank.row = 0;
  blank.col = 0;
  N = document.getElementById("N").value;
  while (boardNode.hasChildNodes()){
    boardNode.removeChild(boardNode.lastChild);
  }
}

/* update the value of the global N, call the previous function, and finally
   position all of the tiles to build the initial configuration of the board
   [must update the tiles (position, size, and font-size), the blank, and
   the board (size)]
*/
function initialize_tile_positions() {
  clearAndRefillBoard();
  var rowPx = 0;
  var colPx = board_size/N;
  var tempArray = [];
  for(var i = 1; i < N*N; i+=1){
    var block = document.createElement("div");
    var node = document.createTextNode(i);
    block.id = i;
    block.className = "tile";
    block.onclick = "process_click(this)";
    block.appendChild(node);
    document.getElementById("board").appendChild(block);
    board = board.concat(block);
    board[i].style.width = board_size/N + "px";
    board[i].style.height = board_size/N + "px";
    board[i].style.fontSize = board_size/N/2 + "px";
    board[i].style.display = "absolute";
    board[i].setAttribute("onclick", "process_click(this)");
    if(i%N === 0){
      rowPx += board_size/N;
      colPx = 0;
    }
    board[i].style.left = colPx + "px";
    board[i].style.top = rowPx + "px";
    colPx += board_size/N;
  }
  for(var i = 0; i < N; i+=1){
    tempArray = board.splice(0, N);
    board.push(tempArray);
  }
}

/* perform num_shuffle_steps random legal moves starting from the current board
   configuration
*/
function shuffle_tiles() {
  for(var i = 0; i < num_shuffle_steps; i+=1){
    swap_with_blank(pick_tile());
  }
}

/* attach all click event handlers (to the buttons and the tiles)
   furthermore, position all of the tiles in their initial configuration
*/
window.onload = function () {
  document.getElementById("board").style.width = "400px";
  document.getElementById("board").style.height = "400px";
  document.getElementById("shuffle").setAttribute("onclick", "shuffle_tiles()");
  document.getElementById("reset").setAttribute("onclick", "initialize_tile_positions()");
  initialize_tile_positions();
};
