package BackEnd;

import java.awt.*;

/**
 * Lớp trừu tượng chứa các thông số mặc định
 */
public abstract class DefaultParameter {
    //Other
    public static int difficulty = 1;
    public static final boolean sandbox = false;
    public static int treeRatio = 5;
    public static final int maxNumberOfBuffs = 5;  // Số loại buff
    public static boolean adventureMode = false;
    // Sound
    public static float backgroundMusicVolume = 10;
    public static float soundEffectVolume = 10;
    public static float systemEffectVolume = 50;
    // Color
    public static final Color menuColor = new Color(170,210,239);
    public static final Color settingColor = new Color(239,239,139);
    public static final Color borderColor = new Color(39,139,139);
    // Frame
    public static int adventureModeX = 1;
    public static int adventureModeY = 1;
    public static final int menuPanelWidth = 1400;
    public static final int menuPanelHeight = 50;
    public static final int panelWidth = 1400;   // Chiều rộng của màn chơi
    public static final int panelHeight = 750;   // Chiều cao của màn chơi
    // Label
    public static final int labelWidth = 50;
    public static final int labelHeight = 50;
    // Time
    public static int buffDelay = 100;
    public static int enemySpawn = 500;
    public static int enemySpawnD = 500;
    public static int buffSpawn = 1000;
    public static int buffSpawnD = 1000;
    public static final int baseClockDelay = 10;
    public static final int renderDelay = 10;
    public static final int enemyMoveDelay = 10;
    public static final int playerMoveDelay = 1;
    public static final int playerPlaceBombDelay = 100;
    public static final int bombDelay = 10;
    public static final int damageDelay = 10;
    public static final int maxRenderStates = 12;
    // Parameter
    // Entity
    public static int entityMaxHeath = 100;
    public static int entityHeath = 100;
    public static int entityDamage = 10;
    public static int entitySpeed = 10;
    public static int entityRange = 1;
    public static double entityAngle = 0;
    public static String entityName = "Default";
    // Enemy
    public static int enemyMaxHeath = 50;
    public static int enemyMaxHeathD = 50;
    public static int enemyHeath = 50;
    public static int enemyHeathD = 50;
    public static int enemyDamage = 10;
    public static int enemyDamageD = 10;
    public static int enemySpeed = 10;
    public static int enemyRange = 1;
    public static double enemyAngle = 0;
    public static int enemyCooldown = 100;
    public static int enemySightDistance = 300;
    public static boolean canEnemyPlaceBomb = false;
    public static boolean enemyDamageToTerrain = true;
    public static boolean enemyDamageToPlayer = true;
    public static boolean enemyDamageToEnemy = false;
    // Player
    public static int playerMaxHeath = 100;
    public static int playerHeath = 100;
    public static int playerDamage = 10;
    public static int playerSpeed = 5;
    public static int playerRange = 1;
    public static double playerAngle = 0;
    public static boolean intersectDamage = true;
    public static boolean playerDamageToTerrain = true;
    public static boolean playerDamageToPlayer = false;
    public static boolean playerDamageToEnemy = true;
    public static int playerMoveCount = 10;
    public static int playerScore =0;
    // Projectile
    public static int projectileMaxHeath = 100;
    public static int projectileHeath = 100;
    public static int projectileDamage = 10;
    public static int projectileSpeed = 0;
    public static int projectileRange = 0;
    public static double projectileAngle = 0;
    public static int bombDecay = 15;
    public static int bombFragmentDecay = 10;
    public static boolean canBombCauseDamage = false;
    // Terrain
    public static int terrainMaxHeath = 1;
    public static int terrainHeath = 1;
    public static int terrainDamage = 0;
    public static int terrainSpeed = 0;
    public static int terrainRange = 0;
    public static double terrainAngle = 0;
    public static boolean terrainPassable = false;
    public static boolean treePassable = false;
    public static boolean grassPassable = true;
}
