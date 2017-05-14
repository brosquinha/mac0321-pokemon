package pokemon;

public class Map {

    private boolean[][] map = new boolean[][]{ // true = grama, false =  chão | mapa 7x7 fixo
            { true, true, true, false, false, true, false },
            { true, false, false, true, true, true, false },
            { false, true, false, false, true, false, false },
            { false, false, true, true, true, false, false },
            { true, true, true, false, false, false, false },
            { true, false, false, false, true, true, true },
            { true, true, false, false, false, true, true }
    };

    private int positions[][]; //positions[trainer] = { x, y }

    public Map(Trainer[] trainers) {
        for (Trainer trainer : trainers) {
            int x = (int)(Math.random()*8);
            int y = (int)(Math.random()*8);
            positions[trainer.id][0] = x;
            positions[trainer.id][1] = y;
        }
    }

    public boolean isGrass(int x, int y) {
        return this.map[x][y];
    }

    public void move(int trainerID) {

    }

    public static void main (String[] args) {

    }
}