package BackEnd;

import Entities.Enemy;

public class SaverLoader {
    public static void changeEnemy() {
        for (Enemy enemy : MainProcess.enemies) {
            enemy.setCanPlaceBomb(DefaultParameter.canEnemyPlaceBomb);

        }
    }
}
