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
    private static final String INPUT_NUMBERS_DELIMITER = ",";
    private static final Scanner scanner = new Scanner(System.in);

    public static String inputLottoPurchaseMoney() {
        System.out.println(INPUT_LOTTO_PURCHASE_MONEY_MESSAGE);
        return scanner.nextLine();
    }

    public static int inputManualLottoCount() {
        OutputView.printNewLine();
        System.out.println(INPUT_MANUAL_LOTTO_COUNT_MESSAGE);
        return InputConvertor.toInt(scanner.nextLine());
    }

    public static List<List<Integer>> inputManualLottos(int manualLottoCount) {
        if (manualLottoCount != 0) {
            OutputView.printNewLine();
            System.out.println(INPUT_MANUAL_LOTTO_MESSAGE);
        }
        return IntStream.range(0, manualLottoCount)
                .mapToObj(i -> InputConvertor.toInt(
                        InputConvertor.splitInput(scanner.nextLine(), INPUT_NUMBERS_DELIMITER)))
                .collect(Collectors.toList());
    }

    public static List<Integer> inputWinningLotto() {
        OutputView.printNewLine();
        System.out.println(INPUT_WINNING_LOTTO_MESSAGE);
        return InputConvertor.toInt(
                InputConvertor.splitInput(scanner.nextLine(), INPUT_NUMBERS_DELIMITER));
    }

    public static int inputBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER_MESSAGE);
        return InputConvertor.toInt(scanner.nextLine());
    }
}
