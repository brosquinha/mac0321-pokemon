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

    public void move(Trainer trainer) {

        int trainerID = trainer.id;
        //System.out.println(trainerID + " posição atual: " + positions[trainerID][0] + " " + positions[trainerID][1]);
        int i;
        if (Math.random() > 0.5) i = 0; else i = 1;
        if (Math.random() > 0.5)
            positions[trainerID][i] = (positions[trainerID][i] == 6) ? 6 : positions[trainerID][i]+1;
        else
            positions[trainerID][i] = (positions[trainerID][i] == 0) ? 0 : positions[trainerID][i]-1;

        System.out.println("Treinador " + trainer.getName());
        for (int k = 0; k < 7; k ++) {
            for (int j = 0; j < 7; j++) {
                if (positions[trainerID][0] == k && positions[trainerID][1] == j)
                    System.out.print("🏃");
                else if (map[k][j])
                    System.out.print("🌿");
                else
                    System.out.print("⬛");
            }
            System.out.println();
        }

        System.out.println();
        //System.out.println(trainerID + " posição nova: " + positions[trainerID][0] + " " + positions[trainerID][1]);

    }
}
