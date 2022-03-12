package lotto.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;

public class LottoGenerator {

    private static final int LOTTO_SIZE = 6;

    public static List<Lotto> generateLottosByManual(List<String> manualLottos) {
        List<Lotto> lottos = manualLottos.stream()
                .map(LottoGenerator::generateLottoByManual)
                .collect(Collectors.toList());
        return new ArrayList<>(lottos);
    }

    public static Lotto generateLottoByManual(String numbers) {
        return Lotto.newInstanceByString(numbers);
    }

    public static List<Lotto> generateLottosByAuto(int autoLottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < autoLottoCount; i++) {
            lottos.add(LottoGenerator.generateLottoByAuto());
        }
        return new ArrayList<>(lottos);
    }

    public static Lotto generateLottoByAuto() {
        return new Lotto(getRandomLottoNumbers());
    }

    private static List<LottoNumber> getRandomLottoNumbers() {
        List<LottoNumber> lottoNumbers = LottoNumber.getLottoNumbers();
        Collections.shuffle(lottoNumbers);
        return lottoNumbers.stream()
                .limit(LOTTO_SIZE)
                .sorted()
                .collect(Collectors.toList());
    }

    public static List<Lotto> getCombinedLottos(List<Lotto> lottos1, List<Lotto> lottos2) {
        lottos1.addAll(lottos2);
        return new ArrayList<>(lottos1);
    }
}
