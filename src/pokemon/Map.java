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

    private int positions[][] = new int[2][2]; //positions[trainer] = { x, y }

    public Map(Trainer[] trainers) {
        for (Trainer trainer : trainers) {
            int x = (int)(Math.random()*7);
            int y = (int)(Math.random()*7);
            positions[trainer.id][0] = x;
            positions[trainer.id][1] = y;
        }
    }

    public boolean isOnGrass(Trainer trainer) {
        return this.map[positions[trainer.id][0]][positions[trainer.id][1]];
    }

    public void move(int trainerID) {
        System.out.println("posição atual: " + positions[trainerID][0] + " " + positions[trainerID][1]);
        for (int i=0; i<2; i++)
			if (Math.random() > 0.5)
				positions[trainerID][i] = (positions[trainerID][i] == 6) ? 6 : positions[trainerID][i]++;
			else
				positions[trainerID][i] = (positions[trainerID][i] == 0) ? 0 : positions[trainerID][i]--;
        System.out.println("posição nova: " + positions[trainerID][0] + " " + positions[trainerID][1]);

    }
}
