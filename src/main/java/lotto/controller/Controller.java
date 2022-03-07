package lotto.controller;

import lotto.domain.LottoCount;
import lotto.domain.Lottos;
import lotto.domain.LottoPurchaseMoney;
import lotto.domain.RankCounter;
import lotto.domain.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Controller {

    public void run() {
        LottoPurchaseMoney lottoPurchaseMoney = getLottoPurchaseMoney();
        LottoCount lottoCounter = getLottoCounter(lottoPurchaseMoney);
        Lottos lottos = getLottos(lottoCounter);
        OutputView.printLottos(lottoCounter, lottos);

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

    private LottoCount getLottoCounter(LottoPurchaseMoney lottoPurchaseMoney) {
        try {
            return new LottoCount(lottoPurchaseMoney.calculateTotalLottoCount(), InputView.inputManualLottoCount());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return getLottoCounter(lottoPurchaseMoney);
        }
    }

    private Lottos getLottos(LottoCount lottoCounter) {
        try {
            Lottos manualLottos = Lottos.newInstanceByManual(InputView.inputManualLottos(lottoCounter.getManualLottoCount()));
            Lottos autoLottos = Lottos.newInstanceByAuto(lottoCounter.getAutoLottoCount());
            return manualLottos.getCombinedLottos(autoLottos);
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return getLottos(lottoCounter);
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
