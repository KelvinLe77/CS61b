/* Skeleton Copyright (C) 2015, 2020 Paul N. Hilfinger and the Regents of the
 * University of California.  All rights reserved. */
package loa;

import static loa.Piece.*;

/** An automated Player.
 *  @author
 */
class MachinePlayer extends Player {

    /** A position-score magnitude indicating a win (for white if positive,
     *  black if negative). */
    private static final int WINNING_VALUE = Integer.MAX_VALUE - 20;
    /** A magnitude greater than a normal value. */
    private static final int INFTY = Integer.MAX_VALUE;

    /** A new MachinePlayer with no piece or controller (intended to produce
     *  a template). */
    MachinePlayer() {
        this(null, null);
    }

    /** A MachinePlayer that plays the SIDE pieces in GAME. */
    MachinePlayer(Piece side, Game game) {
        super(side, game);
    }

    @Override
    String getMove() {
        Move choice;

        assert side() == getGame().getBoard().turn();
        int depth;
        choice = searchForMove();
        getGame().reportMove(choice);
        return choice.toString();
    }

    @Override
    Player create(Piece piece, Game game) {
        return new MachinePlayer(piece, game);
    }

    @Override
    boolean isManual() {
        return false;
    }

    /** Return a move after searching the game tree to DEPTH>0 moves
     *  from the current position. Assumes the game is not over. */
    private Move searchForMove() {
        Board work = new Board(getBoard());
        int value;
        assert side() == work.turn();
        _foundMove = null;
        if (side() == WP) {
            value = findMove(work, chooseDepth(), true, 1, -INFTY, INFTY);
        } else {
            value = findMove(work, chooseDepth(), true, -1, -INFTY, INFTY);
        }
        return _foundMove;
    }

    /** Find a move from position BOARD and return its value, recording
     *  the move found in _foundMove iff SAVEMOVE. The move
     *  should have maximal value or have value > BETA if SENSE==1,
     *  and minimal value or value < ALPHA if SENSE==-1. Searches up to
     *  DEPTH levels.  Searching at level 0 simply returns a static estimate
     *  of the board value and does not set _foundMove. If the game is over
     *  on BOARD, does not set _foundMove. */
    private int findMove(Board board, int depth, boolean saveMove,
                         int sense, int alpha, int beta) {
        // FIXME
        //if depth is zero, calculate here???
        //have to figure out how savemove works
        if (depth == 0 || board.gameOver()) {
            heuristic(board, sense);
        }
        int bestVal = 0;
        for (Move move : board.legalMoves()) {
            Board copy = new Board(board);
            copy.makeMove(move);
            int score = findMove(copy, depth - 1, false, -sense, alpha, beta);
            if (score > bestVal) {
                bestVal = score;
                if (saveMove) {
                    _foundMove = move; // FIXME
                }
                //_foundMove = move;
            }
            if (sense == 1) {
                alpha = Math.max(score, alpha);
                //sense = -sense;
            } else {
                beta = Math.min(score, beta);
                //sense = -sense;
            }
            if (alpha >= beta) {
                break;
            }
        }
//        if (saveMove) {
//            _foundMove = null; // FIXME
//        }
        //return 0; // FIXME
        return bestVal;
    }

    /** Return a search depth for the current position. */
    private int chooseDepth() {
        return 2;  // FIXME
    }

    // FIXME: Other methods, variables here.
    private int heuristic(Board board, int Sense) {
        int val = 0;
        if (Sense == 1) {
            if (board.winner() == WP) {
                return WINNING_VALUE;
            }
            /** if they have same # of regions and white has less pieces than black*/
            if (board.getRegionSizes(board.turn()).size() == board.getRegionSizes(board.turn()).size()) {
                int whitePieces = 0; int blackPieces = 0;
                for (int region : board.getRegionSizes(board.turn())) {
                    whitePieces += region;
                }
                for (int region : board.getRegionSizes(board.turn().opposite())) {
                    blackPieces += region;
                }
                if (whitePieces < blackPieces) {
                    val += 5;
                }
            }
            /** if white has less regions than black*/
            if (board.getRegionSizes(board.turn()).size() < board.getRegionSizes(board.turn()).size()) {
                val += 10;
            }
            /** if white has one move to win*/
            if (board.getRegionSizes(board.turn()).size() == 2) {
                if (board.getRegionSizes(board.turn()).get(1) == 1) {
                    val += 20;
                }
            }
        }
        if (Sense == -1) {
            if (board.winner() == BP) {
                return  -WINNING_VALUE;
            }
            /** if they have same # of regions and black has less pieces than white**/
            if (board.getRegionSizes(board.turn()).size() == board.getRegionSizes(board.turn()).size()) {
                int whitePieces = 0;
                int blackPieces = 0;
                for (int region : board.getRegionSizes(board.turn())) {
                    whitePieces += region;
                }
                for (int region : board.getRegionSizes(board.turn().opposite())) {
                    blackPieces += region;
                }
                if (whitePieces > blackPieces) {
                    val += -5;
                }
            }
            /** if black has less regions than white*/
            if (board.getRegionSizes(board.turn()).size() > board.getRegionSizes(board.turn()).size()) {
                val += -10;
            }
            /** if black has one move to win*/
            if (board.getRegionSizes(board.turn()).size() == 2) {
                if (board.getRegionSizes(board.turn()).get(1) == 1) {
                    val += -20;
                }
            }
        }
        return val;
    }

    /** Used to convey moves discovered by findMove. */
    private Move _foundMove;

}
