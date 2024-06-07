package blackjack;

public class Card {
    private String value;
    private String type;

    public Card(String value, String type) {
        this.value = value;
        this.type = type;
    }

    public int getValue() {
        String valueReturnS = value;
        int valueReturn;
        if ("AJQK".contains(valueReturnS)) {
            valueReturn = valueReturnS == "A" ? 11 : 10;
        } else {
            valueReturn = Integer.parseInt(value);
        }
        return valueReturn;
    }


    public boolean isAce () {
        return value == "A";
    }

    @Override
    public String toString () {
        return value + "-" + type;
    }

    public String getImagePath() {
        return "./cards/" + toString() + ".png";
    }
}