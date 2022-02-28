package lotto.exception;

public class MoneyException extends IllegalArgumentException{

    public static final String MONEY_UNIT_ERROR_MESSAGE = "구입금액은 1000원 단위만 가능합니다.";

    public MoneyException(String message) {
        super(message);
    }
}
