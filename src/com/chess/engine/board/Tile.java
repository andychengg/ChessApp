package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

import java.util.HashMap;
import java.util.Map;

/*
abstract Tile class to be defined in a subclass
* */
public abstract class Tile {
    //protected final to help with immutability
    protected final int tileCoordinate;
    private static final Map<Integer, EmptyTile> Empty_Tiles = createAllPossibleEmptyTiles();

    private static Map<Integer,EmptyTile> createAllPossibleEmptyTiles() {

        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();
        for(int i = 0; i < 64; i++) {
            emptyTileMap.put(i, new EmptyTile(i));
        }
        return emptyTileMap;
    }
    public static Tile createTile(final int tileCoordinate, final Piece piece) {
        return piece != null ? new OccupiedTile(tileCoordinate, piece) : Empty_Tiles.get(tileCoordinate);
    }

    //constructor for tile Coordinate
    Tile(int tileCoordinate) {
        this.tileCoordinate = tileCoordinate;

    }

    public abstract boolean isTileOccupied();
    public abstract Piece getPiece();
    
    public static final class EmptyTile extends Tile{
        EmptyTile(int coordinate) {
            super(coordinate);
        }
        @Override
        public boolean isTileOccupied() {
            return false;
        }
        @Override
        public Piece getPiece() {
            return null;
        }
    }
    
    public static final class OccupiedTile extends Tile {
        //private final to help with immutability
        private final Piece pieceOnTile;
        OccupiedTile(int tileCoordinate, Piece pieceOnTile) {
            super(tileCoordinate);
            this.pieceOnTile = pieceOnTile;
        }
        @Override
        public boolean isTileOccupied() {
            return true;
        }
        @Override
        public Piece getPiece() {
            return this.pieceOnTile;
        }
    }

}

