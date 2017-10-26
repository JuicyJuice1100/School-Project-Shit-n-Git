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
  // to be completed
}

/* return true iff the given tile is directly to the left of the blank */
function is_to_the_left_of_blank(tile) {
  // to be completed
}

/* return true iff the given tile is directly above the blank */
function is_above_blank(tile) {
  // to be completed
}

/* return true iff the given tile is directly under the blank */
function is_below_blank(tile) {
  // to be completed
}

/* return true iff the given tile is immediately adjacent to the blank */
function is_next_to_blank(tile) {
  // to be completed
}

/* return true iff the tiles are in their initial configuration */
function game_over() {
  // to be completed
}

/* randomly select and return a tile that is adjacent to the blank */
function pick_tile() {
  // to be completed
}

/* position the given tile at the given position on the board
   must update the tile and the board
*/
function position_tile(tile,row,col) {
  // to be completed
}

/* position all of the tiles to build the initial configuration of the board
   must update the tiles (position, size, and font-size), the blank, and
   the board (size)
*/
function initialize_tile_positions() {
  // to be completed
}

/* move the given tile to the blank location
   (assume that the given tile is adjacent to the blank before the move)
   must update the tile, the blank, and the board
*/
function swap_with_blank(tile) {
  // to be completed
}

/* perform num_shuffle_steps random legal moves starting from the current board
   configuration
*/
function shuffle_tiles() {
  // to be completed
}

/* event handler for a click on a tile
   if the clicked tile is adjacent to the blank, move the tile into the blank
   position_tile (otherwise do nothing)
   if this move returns the board to its initial configuration, pop up a
   "Well done!" message
*/
function process_click() {
  // to be completed
}

/* attach all click event handlers (to the buttons and the tiles)
   furthermore, position all of the tiles in their initial configuration
*/
window.onload = function () {
  // to be completed
};
