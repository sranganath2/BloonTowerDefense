package com.teamfourtyone;

public class Statistics {
    private static int enemiesKilled;
    private static double damageDealt;
    private static long startTime;

    public static void resetStats() {
        enemiesKilled = 0;
        damageDealt = 0;
        startTime = System.currentTimeMillis();
    }

    public static void updateEnemiesKilled() {
        enemiesKilled++;
    }

    public static void updateDamageDealt(double damage) {
        damageDealt += damage;
    }

    public static String getStats() {
        return "Enemies Killed: " + enemiesKilled + "\nDamage Dealt: " + damageDealt
                + "\nTime Played: " + ((System.currentTimeMillis() - startTime) / 1000) + " sec";
    }




}
