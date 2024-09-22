package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction) {
        case "up":
            entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol];
            tileNum2 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];

            if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                entity.collisionOn = true;
            }
            break;
        case "down":
            entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];
            tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol];
            if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                entity.collisionOn = true;
            }
            break;
        case "left":
            entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol];
            tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];
            if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                entity.collisionOn = true;
            }
            break;
        case "right":
            entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];
            tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol];
            if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                entity.collisionOn = true;
            }
            break;
        }
    }

    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        for(int currentObject = 0; currentObject < gp.obj.length; currentObject++) {
            if (gp.obj[currentObject] != null) {
                // Get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                // Get entity's solid area position
                gp.obj[currentObject].solidArea.x = gp.obj[currentObject].worldX + gp.obj[currentObject].solidArea.x;
                gp.obj[currentObject].solidArea.y = gp.obj[currentObject].worldY + gp.obj[currentObject].solidArea.y;

                switch(entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[currentObject].solidArea)) {
                            if (gp.obj[currentObject].collision) {
                                entity.collisionOn = true;
                            }

                            if (player) {
                                index = currentObject;
                            }
                        }
                    break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[currentObject].solidArea)) {
                            if (gp.obj[currentObject].collision) {
                                entity.collisionOn = true;
                            }

                            if (player) {
                                index = currentObject;
                            }
                        }
                    break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[currentObject].solidArea)) {
                            if (gp.obj[currentObject].collision) {
                                entity.collisionOn = true;
                            }

                            if (player) {
                                index = currentObject;
                            }
                        }
                    break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[currentObject].solidArea)) {
                            if (gp.obj[currentObject].collision) {
                                entity.collisionOn = true;
                            }

                            if (player) {
                                index = currentObject;
                            }
                        }
                    break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[currentObject].solidArea.x = gp.obj[currentObject].solidAreaDefaultX;
                gp.obj[currentObject].solidArea.y = gp.obj[currentObject].solidAreaDefaultY;
            }
        }

        return index;
    }
}
