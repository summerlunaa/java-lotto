package lotto.view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.util.InputConvertor;

public class InputView {

    private static final String INPUT_LOTTO_PURCHASE_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_MANUAL_LOTTO_COUNT_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String INPUT_MANUAL_LOTTO_MESSAGE = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String INPUT_WINNING_LOTTO_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final Scanner scanner = new Scanner(System.in);

    public static String inputLottoPurchaseMoney() {
        System.out.println(INPUT_LOTTO_PURCHASE_MONEY_MESSAGE);
        return scanner.nextLine();
    }

    public static String inputManualLottoCount() {
        OutputView.printNewLine();
        System.out.println(INPUT_MANUAL_LOTTO_COUNT_MESSAGE);
        return scanner.nextLine();
    }

    public static List<String> inputManualLottos(int manualLottoCount) {
        if (manualLottoCount != 0) {
            OutputView.printNewLine();
            System.out.println(INPUT_MANUAL_LOTTO_MESSAGE);
        }
        return IntStream.range(0, manualLottoCount)
                .mapToObj(i -> scanner.nextLine())
                .collect(Collectors.toList());
    }

    public static String inputWinningLotto() {
        OutputView.printNewLine();
        System.out.println(INPUT_WINNING_LOTTO_MESSAGE);
        return scanner.nextLine();
    }

    public static String inputBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER_MESSAGE);
        return scanner.nextLine();
    }
}
