import java.util.Random;
public class Forest {

	// prints entire array of animals
	public static void printAll(Animal[] animals) {
		for (int i = 0; i < 50; ++i) {
			if (animals[i] != null)
				animals[i].printAnimal();
			else
				System.out.println("empty");
		}
	}

	// determines if animals are of the same type (two empty spots returns false)
	public static int isSame(Animal[] animals, int i, int j) {
		if (animals[i] != null && animals[j] != null) {
			if (animals[i].getType() == animals[j].getType())
				return 1; 		// if same return true
			else
				return 0; 		// if not same return false
		}
		else
			return 2; 			// if null return 2
	}

	// main
	public static void main (String[] args) {	

		// create animals array
		Animal[] animals = new Animal[50];
		
		// set up random
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		
      int fish=0;
      int bear=0;
		for (int i = 0; i < 50; ++i) {				// initialize 50 animals
			int tmp = rand.nextInt(3); 				// random number between (0, 3]
			switch (tmp) {
				case 0: animals[i] = null;				// make empty
						  break;
				case 1: animals[i] = new Bear();		// make bear
                    ++bear;
						  break;
				case 2: animals[i] = new Fish();		// make fish
                    ++fish;
						  break;
				default: break;
			}
		}
         System.out.println("FishNum: " + fish);
         System.out.println("BearNum: " + bear);
		for (int i = 0; i < 10; ++i) {									// move each animal 10 times
			for (int j = 0; j < 50; ++j) {								// move each animal
				int move = (rand.nextInt(3)) - 1;						// decide where to move (-1, 1)
				if ((j == 49) && (move == 1)) {							// if end of array, wrap
					move = -49;
				}
				if ((j == 0) && (move == -1)) {							// if start of array, wrap
					move = 49;
				}
				int same = isSame(animals, j, (j+move));				// find out animal types
				if (same == 2) {
					System.out.print("");
			   	animals[j+move] = animals[j];							// set mext location to current animal
			   	animals[j] = null;
            }
				else if (same == 1) {										// if animals are the same (true)
					System.out.println("Collision!");					// ouput collision
               Animal tmp = animals[j+move];
			   	animals[j+move] = animals[j];							// set mext location to current animal
               animals[j] = tmp;
            }
				else if (same == 0) {										// else if animals differ (false)
					System.out.println("Collision, bear ate fish");	// ouptput collision and blah
			   	animals[j+move] = animals[j];							// set mext location to current animal
			   	animals[j] = null;
               --fish;
            }
			}	
         System.out.println("FishNum: " + fish);
         System.out.println("BearNum: " + bear);
		}
	}
}
