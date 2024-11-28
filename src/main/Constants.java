package main;

public class Constants {

    public static class ArcherConstants{
        public static final int ATTACK_D = 0;
        public static final int IDLE_D = 1;
        public static final int ATTACK_S = 2;
        public static final int IDLE_S = 3;
        public static final int ATTACK_U = 4;
        public static final int IDLE_U = 5;
        public static final int ATTACK_L = 2;

        public static final int RANGE = 144;
        public static final int DMG = 15;
        public static final int COOLDOWN = 250; //200 ms - 0.25s
        public static final int PRICE = 100;
        

        public static int getSpriteAmount(int action){
            switch (action) {
                case ATTACK_D:
                case ATTACK_S:
                case ATTACK_U:
                    return 6;
                case IDLE_D:
                case IDLE_S:
                case IDLE_U:
                    return 4;
                default:
                    System.out.println("sxxxxxxxxxx");
                    return -1;
            }
        }
    }

    public static class TurretConstants{
        public static final int IDLE = 0;
        public static final int ATTACK = 1;

        public static final int RANGE = 200;
        public static final int DMG = 1;
        public static final int COOLDOWN = 50; //50 ms - 0.05s
        public static final int PRICE = 225;

        public static int getSpriteAmount(int action){
            switch (action) {
                case ATTACK:
                    return 9;
                case IDLE:
                    return 1;
                default:
                    return 0;
            }
        }
    }

    public static class RedTowerConstants{
        public static final int ATTACK = 0;
        public static final int IDLE = 1;

        public static final int RANGE = 144;
        public static final int DMG = 15;
        public static final int COOLDOWN = 250; //200 ms - 0.25s
        public static final int PRICE = 800;
        

        public static int getSpriteAmount(int action){
            switch (action) {
                case IDLE:
                    return 3;
                case ATTACK:
                    return 1;
                default:
                    System.out.println("sxxxxxxxxxx");
                    return -1;
            }
        }
    }

    public static class BeeConstants{
        public static final int MOVE_D = 0;
        public static final int MOVE_S = 1;
        public static final int MOVE_U = 2;

        public static final String TYPE = "bee";
        public static final int X_START = -96;
        public static final int Y_START = 336;
        public static final int MAX_HEALH = 100;
        public static final int SPEED = 3;
        public static final int MONEY_KILL = 100;
        public static final int MONEY_ESCAPE = -100;
        public static final int POINTS = 50;



        public static int getSpriteAmount(int action){
            switch (action) {
                case MOVE_D:
                case MOVE_S:
                case MOVE_U:
                    return 6;
                default:
                    return 1;
            }
        }
    }

    public static class BloopConstants{
        public static final int MOVE_D = 0;
        public static final int MOVE_S = 1;
        public static final int MOVE_U = 2;

        public static final String TYPE = "bloop";
        public static final int X_START = -96;
        public static final int Y_START = 336;
        public static final int MAX_HEALH = 75;
        public static final int SPEED = 5;
        public static final int MONEY_KILL = 150;
        public static final int MONEY_ESCAPE = -150;
        public static final int POINTS = 69;

        public static int getSpriteAmount(int action){
            switch (action) {
                case MOVE_D:
                case MOVE_S:
                case MOVE_U:
                    return 6;
                default:
                    return 1;
            }
        }
    }

    public static class OrcConstants{
        public static final int MOVE_U = 0;
        public static final int MOVE_S = 1;
        public static final int MOVE_D = 2;

        public static final String TYPE = "orc";
        public static final int X_START = -96;
        public static final int Y_START = 336;
        public static final int MAX_HEALH = 250;
        public static final int SPEED = 2;
        public static final int MONEY_KILL = 200;
        public static final int MONEY_ESCAPE = -200;
        public static final int POINTS = 69;

        public static int getSpriteAmount(int action){
            switch (action) {
                case MOVE_D:
                case MOVE_S:
                case MOVE_U:
                    return 6;
                default:
                    return 1;
            }
        }
    }

    public static class WolfConstants{
        public static final int MOVE_D = 0;
        public static final int MOVE_S = 1;
        public static final int MOVE_U = 2;

        public static final String TYPE = "wolf";
        public static final int X_START = -96;
        public static final int Y_START = 336;
        public static final int MAX_HEALH = 250;
        public static final int SPEED = 2;
        public static final int MONEY_KILL = 200;
        public static final int MONEY_ESCAPE = -200;
        public static final int POINTS = 69;

        public static int getSpriteAmount(int action){
            switch (action) {
                case MOVE_D:
                case MOVE_S:
                case MOVE_U:
                    return 6;
                default:
                    return 1;
            }
        }
    }

    public static class CheeseConstants{
        public static final int MOVE_D = 0;
        public static final int MOVE_S = 2;
        public static final int MOVE_U = 3;

        public static final String TYPE = "cheese";
        public static final int X_START = -96;
        public static final int Y_START = 336;
        public static final int MAX_HEALH = 350;
        public static final int SPEED = 1;
        public static final int MONEY_KILL = 250;
        public static final int MONEY_ESCAPE = -250;
        public static final int POINTS = 69;

        public static int getSpriteAmount(int action){
            switch (action) {
                case MOVE_D:
                case MOVE_S:
                case MOVE_U:
                    return 3;
                default:
                    return 1;
            }
        }
    }

    public static class RobotConstants{
        public static final int MOVE_D = 0;
        public static final int MOVE_S = 2;
        public static final int MOVE_U = 3;

        public static final String TYPE = "robot";
        public static final int X_START = -96;
        public static final int Y_START = 336;
        public static final int MAX_HEALH = 500;
        public static final int SPEED = 1;
        public static final int MONEY_KILL = 400;
        public static final int MONEY_ESCAPE = -400;
        public static final int POINTS = 69;

        public static int getSpriteAmount(int action){
            switch (action) {
                case MOVE_D:
                case MOVE_S:
                case MOVE_U:
                    return 3;
                default:
                    return 1;
            }
        }
    }

    public static class TowerConstants{
        public static final int IDLE = 0;

        public static final int COOLDOWN = 100; //2000 ms - 2.0s
        public static final int PRICE = 1000;
        

        public static int getSpriteAmount(int action){
            switch (action) {
                case IDLE:
                    return 6;
                default:
                    System.out.println("sxxxxxxxxxx");
                    return -1;
            }
        }
    }

    

}
