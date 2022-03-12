package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lotto.exception.BonusNumberException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class WinningNumbersTest {

    private List<LottoNumber> lotto;

    @BeforeEach
    void setUp() {
        lotto = Stream.of(11, 12, 13, 14, 15, 16)
                .map(LottoNumber::from)
                .collect(Collectors.toList());
    }

    @ParameterizedTest(name = "당첨 번호와 중복되는 보너스볼이 입력되면 예외발생 - case : {0}")
    @ValueSource(strings = {"1", "45"})
    void checkDuplication(String bonusNumber) {
        Assertions.assertThatThrownBy(() -> new WinningNumbers("1, 4, 10, 20, 30, 45", bonusNumber))
                .isInstanceOf(BonusNumberException.class)
                .hasMessage("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }

    @ParameterizedTest(name = "당첨 번호와 일치하는 티켓의 번호 개수 반환 - case : count {1}")
    @CsvSource(value = {"11,12,13,14,15,16:6", "11,12,13,7,8,9:3", "1,2,3,4,5,6:0"}, delimiter = ':')
    void getWinningNumberMatchCount(String winningLottoInput, int expectedCount) {
        WinningNumbers winningNumbers = new WinningNumbers(winningLottoInput, "45");
        Assertions.assertThat(winningNumbers.getWinningLottoMatchCount(lotto))
                .isEqualTo(expectedCount);
    }

    @ParameterizedTest(name = "보너스 번호가 포함 되는지 판별 - case : {1}")
    @CsvSource(value = {"16,true", "45,false"})
    void isBonusNumberContainedAt(String bonusNumber, boolean expected) {
        WinningNumbers winningNumbers = new WinningNumbers("1, 2, 3, 4, 5, 6", bonusNumber);
        Assertions.assertThat(winningNumbers.isBonusNumberContainedAt(lotto))
                .isEqualTo(expected);
    }
}
