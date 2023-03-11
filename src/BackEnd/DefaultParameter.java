package BackEnd;

/**
 * Lớp trừu tượng chứa các thông số mặc định
 */
public abstract class DefaultParameter {
    // Parameter
    // Entity
    public static int entityHeath = 100;
    public static int entityDamage = 10;
    public static int entitySpeed = 10;
    public static int entityRange = 1;
    public static double entityAngle = 0;
    public static String entityName = "Default";
    // Enemy
    public static int enemyHeath = 100;
    public static int enemyDamage = 10;
    public static int enemySpeed = 10;
    public static int enemyRange = 1;
    public static double enemyAngle = 0;
    public static int enemyCooldown = 100;
    public static int enemySightDistance = 300;
    public static boolean enemyDamageToTerrain = true;
    public static boolean enemyDamageToPlayer = true;
    public static boolean enemyDamageToEnemy = false;
    // Player
    public static int playerHeath = 100;
    public static int playerDamage = 10;
    public static int playerSpeed = 10;
    public static int playerRange = 3;
    public static double playerAngle = 0;
    public static boolean playerDamageToTerrain = true;
    public static boolean playerDamageToPlayer = false;
    public static boolean playerDamageToEnemy = true;
    public static int playerMoveCount = 5;
    // Projectile
    public static int projectileHeath = 100;
    public static int projectileDamage = 10;
    public static int projectileSpeed = 0;
    public static int projectileRange = 0;
    public static double projectileAngle = 0;
    public static int bombDecay = 15;
    public static int bombFragmentDecay = 10;
    // Terrain
    public static int terrainHeath = 100;
    public static int terrainDamage = 0;
    public static int terrainSpeed = 0;
    public static int terrainRange = 0;
    public static double terrainAngle = 0;
    public static boolean terrainPassable = false;
    public static boolean treePassable = false;
    public static boolean grassPassable = true;
    // Time
    public static final int baseClockDelay = 10;
    public static final int renderDelay = 10;
    public static final int enemyMoveDelay = 5;
    public static final int playerMoveDelay = 1;
    public static final int bombDelay = 10;
    public static final int deathDelay = 10;
    public static final int damageDelay = 10;
}
