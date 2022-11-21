package bridge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class InputViewTest {
    private InputView inputView;

    @BeforeEach
    void init(){
        inputView = new InputView();
    }

    @DisplayName("숫자가 아닌 값을 입력하면 예외 발생")
    @Test
    void 잘못된_다리_입력(){
        String input = "a";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        assertThatThrownBy(() -> inputView.readBridgeSize())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("3-20 사이가 아닌 값을 입력하면 예외 발생")
    @Test
    void 잘못된_다리_사이즈_입력(){
        String input = "200";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        assertThatThrownBy(() -> inputView.readBridgeSize())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("정상 값을 입력하는 경우 정상 통과")
    @Test
    void 정상_다리_사이즈_입력(){
        String input = "10";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        assertDoesNotThrow(() -> inputView.readBridgeSize());
    }

    @DisplayName("U 또는 D 이외의 값을 입력하는 경우 예외 발생")
    @Test
    void 잘못된_이동_위치_입력(){
        String input = "A";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        assertThatThrownBy(() -> inputView.readMoving())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("정상 이동 위치 U를 입력하는 경우 정상 통과")
    @Test
    void 정상_이동_위치_U_입력(){
        final String input = "U";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        assertThat(inputView.readMoving()).isEqualTo(input);
    }

    @DisplayName("정상 이동 위치 D를 입력하는 경우 정상 통과")
    @Test
    void 정상_이동_위치_D_입력(){
        final String input = "D";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        assertThat(inputView.readMoving()).isEqualTo(input);
    }
}