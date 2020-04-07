/* Skeleton Copyright (C) 2015, 2020 Paul N. Hilfinger and the Regents of the
 * University of California.  All rights reserved. */
package loa;

import static loa.Piece.*;

/** An automated Player.
 *  @author Kelvin Le
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
        if (depth == 0 || board.gameOver()) {
            return heuristic(board, sense);
        }
        int bestVal = 0;
        for (Move move : board.legalMoves()) {
            Board copy = new Board(board);
            copy.makeMove(move);
            int score = findMove(copy, depth - 1, false, -sense, alpha, beta);
            if (score > bestVal) {
                bestVal = score;
                if (saveMove) {
                    _foundMove = move;
                } else {
                    _foundMove = null;
                }
            }
            if (sense == 1) {
                alpha = Math.max(score, alpha);
            } else {
                beta = Math.min(score, beta);
            }
            if (alpha >= beta) {
                break;
            }
        }
        return bestVal;
    }

    /** Return a search depth for the current position. */
    private int chooseDepth() {
        return 2;
    }

    /** Returns an int, for a player represented by SENSE,
     * representing the value of a BOARD. */
    int heuristic(Board board, int sense) {
        int val = 0;
        if (sense == 1) {
            if (board.winner() == WP) {
                return WINNING_VALUE;
            }
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
            if (board.getRegionSizes(board.turn()).size()
                    < board.getRegionSizes(board.turn()).size()) {
                val += 10;
            }
            if (board.getRegionSizes(board.turn()).size() == 2) {
                if (board.getRegionSizes(board.turn()).get(1) == 1) {
                    val += 16;
                }
            }
        }
        if (sense == -1) {
            if (board.winner() == BP) {
                return  -WINNING_VALUE;
            }
            if (board.getRegionSizes(board.turn()).size()
                    == board.getRegionSizes(board.turn()).size()) {
                int whitePieces = 0;
                int blackPieces = 0;
                for (int region : board.getRegionSizes(board.turn())) {
                    whitePieces += region;
                }
                for (int region
                        : board.getRegionSizes(board.turn().opposite())) {
                    blackPieces += region;
                }
                if (whitePieces > blackPieces) {
                    val += -5;
                }
            }
            if (board.getRegionSizes(board.turn()).size()
                    > board.getRegionSizes(board.turn()).size()) {
                val += -10;
            }
            if (board.getRegionSizes(board.turn()).size() == 2) {
                if (board.getRegionSizes(board.turn()).get(1) == 1) {
                    val += -16;
                }
            }
        }
        return val;
    }

    /** Used to convey moves discovered by findMove. */
    private Move _foundMove;

}
