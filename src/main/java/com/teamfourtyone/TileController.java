package com.teamfourtyone;

import com.teamfourtyone.TowerEntity.Tower;

public class TileController {
    private static Tileable[][] tileGrid = new Tileable[App.TILEHEIGHT][App.TILEWIDTH];
    private static int[][] path;

    public static boolean placeTile(int row, int column, Tileable newTile) {
        if (newTile != null && row < App.TILEHEIGHT && column < App.TILEWIDTH) {
            tileGrid[row][column] = newTile;
            return true;
        } else {
            return false;
        }
    }
    public static boolean setTile(int row, int column, Tileable newTile) {
        return placeTile(row, column, newTile);
    }

    public static boolean removeTile(int row, int column) {
        if (row < App.TILEHEIGHT && column < App.TILEWIDTH) {
            tileGrid[row][column] = null;
            return true;
        } else {
            return false;
        }
    }

    public static int[][] getPath() {
        return path;
    }

    public static boolean setPathCoords(int[][] newCoords) {
        if (newCoords == null || newCoords[0].length != 2) {
            return false;
        }
        path = newCoords;
        for (int[] set : newCoords) {
            tileGrid[set[0]][set[1]] = new Path();
        }
        return true;
    }

    public static boolean isEmpty(int row, int col) {
        return (tileGrid[row][col] == null);
    }

    public static void clearTiles() {
        tileGrid = new Tileable[App.TILEWIDTH][App.TILEHEIGHT];
    }

    public static boolean setTileGrid(Tileable[][] savedTiles) {
        if (savedTiles == null) {
            return false;
        } else {
            tileGrid = savedTiles;
            return true;
        }
    }
    public static boolean isTower(int row, int col) {
        return (tileGrid[row][col] instanceof Tower);
    }
    public static Tileable[][] getTileGrid() {
        return tileGrid;
    }

    public static Tileable getTile(int row, int column) {
        if (row < 0 || column < 0 || row >= App.TILEHEIGHT || column >= App.TILEWIDTH) {
            return null;
        }
        return tileGrid[row][column];
    }
}
