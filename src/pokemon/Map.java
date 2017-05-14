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
        for (int i=0; i<2; i++)
			if (Math.random() > 0.5)
				positions[trainerID][i] = (positions[trainerID][i] == 6) ? 6 : positions[trainerID][i]++;
			else
				positions[trainerID][i] = (positions[trainerID][i] == 0) ? 0 : positions[trainerID][i]--;
    }

    public static void main (String[] args) {

    }
}
