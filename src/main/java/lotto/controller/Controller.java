package lotto.controller;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoCount;
import lotto.domain.LottoPurchaseMoney;
import lotto.domain.RankCounter;
import lotto.domain.WinningNumbers;
import lotto.generator.LottoGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Controller {

    public void run() {
        LottoPurchaseMoney lottoPurchaseMoney = getLottoPurchaseMoney();
        LottoCount lottoCount = getLottoCount(lottoPurchaseMoney);
        List<Lotto> lottos = getLottos(lottoCount);
        OutputView.printLottos(lottoCount, lottos);

        RankCounter rankCounter = RankCounter.newInstance(lottos, getWinningNumbers());
        OutputView.printWinningStatistic(lottoPurchaseMoney, rankCounter);
    }

    private LottoPurchaseMoney getLottoPurchaseMoney() {
        try {
            return LottoPurchaseMoney.of(InputView.inputLottoPurchaseMoney());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return getLottoPurchaseMoney();
        }
    }

    private LottoCount getLottoCount(LottoPurchaseMoney lottoPurchaseMoney) {
        try {
            return new LottoCount(lottoPurchaseMoney.calculateTotalLottoCount(), InputView.inputManualLottoCount());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return getLottoCount(lottoPurchaseMoney);
        }
    }

    private List<Lotto> getLottos(LottoCount lottoCount) {
        try {
            List<String> manualLottosInput = InputView.inputManualLottos(lottoCount.getManualLottoCount());
            List<Lotto> manualLottos = LottoGenerator.generateLottosByManual(manualLottosInput);
            List<Lotto> autoLottos = LottoGenerator.generateLottosByAuto(lottoCount.getAutoLottoCount());
            return LottoGenerator.getCombinedLottos(manualLottos, autoLottos);
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return getLottos(lottoCount);
        }
    }

    private WinningNumbers getWinningNumbers() {
        try {
            return new WinningNumbers(InputView.inputWinningLotto(), InputView.inputBonusNumber());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return getWinningNumbers();
        }
    }
}
