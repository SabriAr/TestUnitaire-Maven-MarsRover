import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.assertThat;

public class MarsRoverTest {

    private MarsRover marsRover;

@Test
    public void shouldHaveInitialPosition() {
        // GIVEN
        MarsRover rover = new MarsRover();

        // WHEN
        String initialPosition = rover.move("");

        // THEN
        assertThat(initialPosition).isEqualTo("0,0,N");

    }

@Test
	public void shouldTurnRight() {
	// GIVEN
		MarsRover rover = new MarsRover();
	//WHEN
		String position1 = rover.move("R"); 
		String position2 = rover.move("RR"); 
		String position3 = rover.move("RRR"); 
		String position4 = rover.move("RRRR");
	//THEN
		assertThat(position1).isEqualTo("0,0,E");
		assertThat(position2).isEqualTo("0,0,S");
		assertThat(position3).isEqualTo("0,0,W");
		assertThat(position4).isEqualTo("0,0,N");
   }

@Test
	public void shouldTurnLeft() {
	// GIVEN
		MarsRover rover = new MarsRover();
	//WHEN
		String position1 = rover.move("L");
		String position2 = rover.move("LL");
		String position3 = rover.move("LLL");
		String position4 = rover.move("LLLL");
	//THEN
		assertThat(position1).isEqualTo("0,0,W");
		assertThat(position2).isEqualTo("0,0,S");
		assertThat(position3).isEqualTo("0,0,E");
		assertThat(position4).isEqualTo("0,0,N");
	}

@Test
	public void shouldMoveForward() {
	// GIVEN
		MarsRover rover = new MarsRover();
	//WHEN
		String position1 = rover.move("M");
		String position2 = rover.move("MMMMM"); 
		String position3 = rover.move("RMM");
	//THEN
		assertThat(position1).isEqualTo("0,1,N");
		assertThat(position2).isEqualTo("0,5,N");
		assertThat(position3).isEqualTo("2,0,E");
	}

@Test
	public void shouldWrapAroundSphere() {
	// GIVEN
		MarsRover rover = new MarsRover();
	//WHEN
		String position1 = rover.move("MMMMMMMMMM"); 
		String position2 = rover.move("RMMMMMMMMMM"); 
		String position3 = rover.move("RMMMMMMMMMM"); 
		String position4 = rover.move("RMMMMMMMMMM"); 
	//THEN
		assertThat(position1).isEqualTo("0,0,N"); 
		assertThat(position2).isEqualTo("0,0,E"); 
		assertThat(position3).isEqualTo("0,0,S"); 
		assertThat(position4).isEqualTo("0,0,W"); 
	}

@Test
	public void shouldStopAtObstacle() {
	// GIVEN
		MarsRover rover = new MarsRover();
		rover.addObstacle(0, 2);
		// WHEN
		String finalPosition = rover.move("MMMM");
		// THEN
		assertThat(finalPosition).isEqualTo("0,1,N");
		assertThat(rover.obstacle()).isEqualTo("Obstacle at position (0,2)");
	}
}