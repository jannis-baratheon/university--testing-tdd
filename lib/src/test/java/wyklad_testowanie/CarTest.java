package wyklad_testowanie;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;

class CarTest {
    private Car sut;
    private Infotainment infotainmentMock;

    @BeforeAll
    static void setupGlobal() {

    }

    @BeforeEach
    void setup() {
        infotainmentMock = mock(Infotainment.class);
        sut = Car.getInstance(infotainmentMock);
    }

    @Test
    void turningKeyOnceSetsIgnitionToStateI() {
        // given

        // when
        sut.turnKey(Direction.CLOCKWISE);

        // then
        assertThat(sut.getIgnitionState()).isEqualTo(IgnitionState.STATE_I);
    }

    @Test
    void turningKeyOnceTurnsOnRadio() {
        // given

        // when
        sut.turnKey(Direction.CLOCKWISE);

        // then
        verify(infotainmentMock, times(1)).turnRadioOn();
    }

    @Test
    void turningKeyTwiceTurnsCarOn() {
        // given
        sut.turnKey(Direction.CLOCKWISE);

        // when
        sut.turnKey(Direction.CLOCKWISE);

        // then
        assertThat(sut.getIgnitionState()).isEqualTo(IgnitionState.STATE_II);
    }

    @Test
    void turningKeyMoreThanOnceDoesNotWork() {
        // given
        sut.turnKey(Direction.CLOCKWISE);
        sut.turnKey(Direction.CLOCKWISE);

        // when
        Throwable thrownWhenKeyTurnedMoreThanOnce = catchThrowable(() -> sut.turnKey(Direction.CLOCKWISE));

        // then
        assertThat(thrownWhenKeyTurnedMoreThanOnce).isInstanceOf(IllegalStateException.class);
    }
}
