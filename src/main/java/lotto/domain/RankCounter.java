package lotto.domain;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class RankCounter {

    private final Map<Rank, Integer> rankCount;

    private RankCounter(Map<Rank, Integer> rankCount) {
        this.rankCount = rankCount;
    }

    public static RankCounter newInstance(Lottos lottos, WinningNumbers winningNumbers){
        Map<Rank, Integer> rankCount = new EnumMap<>(Rank.class);
        initRankCount(rankCount);
        calculateRank(rankCount, lottos, winningNumbers);
        return new RankCounter(rankCount);
    }

    private static void initRankCount(Map<Rank, Integer> rankCount) {
        Arrays.stream(Rank.values())
                .forEach(rank -> rankCount.put(rank, 0));
    }

    private static void calculateRank(Map<Rank, Integer> rankCount, Lottos lottos, WinningNumbers winningNumbers) {
        lottos.getLottos().stream()
                .map(lotto -> lotto.getRank(winningNumbers))
                .forEach(rank -> increaseCount(rankCount, rank));
    }

    private static void increaseCount(Map<Rank, Integer> rankCount, Rank rank) {
        rankCount.computeIfPresent(rank, (keyRank, count) -> count + 1);
    }

    public int getCountOfRank(Rank rank) {
        return rankCount.get(rank);
    }

    public long getTotalPrize() {
        return rankCount.entrySet().stream()
                .mapToLong(m -> m.getKey().multiplyPrizeBy(m.getValue()))
                .sum();
    }
}
