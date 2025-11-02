package lotto.model;

import java.util.List;

public record Lottos(List<Lotto> lottoList) {

    public static Lottos from(List<Lotto> lottoList) {
        return new Lottos(lottoList);
    }

    public int getLottoPrice() {
        return lottoList.size() * LottoProperties.LOTTO_PRICE;
    }
}
