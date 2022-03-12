package lotto.generator;

import lotto.exception.LottoException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoGeneratorTest {

    @Test
    @DisplayName("로또를 생성하여 LottoNumber 리스트로 반환")
    void getLottoToInteger() {
        Assertions.assertThat(LottoGenerator.generateLottoByManual("45, 1, 10, 4, 20, 30").getLottoToInteger())
                .containsExactly(1, 4, 10, 20, 30, 45);
    }

    @Test
    @DisplayName("숫자가 6개보다 적은 경우 예외 발생")
    void checkSmallSize() {
        Assertions.assertThatThrownBy(() -> LottoGenerator.generateLottoByManual("1, 2, 3, 4, 5"))
                .isInstanceOf(LottoException.class)
                .hasMessage("당첨 번호는 6개여야 합니다.");
    }

    @Test
    @DisplayName("숫자가 6개보다 많은 경우 예외 발생")
    void checkLargeSize() {
        Assertions.assertThatThrownBy(() -> LottoGenerator.generateLottoByManual("1, 2, 3, 4, 5, 6, 7"))
                .isInstanceOf(LottoException.class)
                .hasMessage("당첨 번호는 6개여야 합니다.");
    }

    @Test
    @DisplayName("숫자가 중복되는 경우 예외 발생")
    void checkDuplication() {
        Assertions.assertThatThrownBy(() -> LottoGenerator.generateLottoByManual("1, 2, 3, 4, 2, 5"))
                .isInstanceOf(LottoException.class)
                .hasMessage("당첨 번호는 중복될 수 없습니다.");
    }
}
