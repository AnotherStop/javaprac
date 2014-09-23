import java.util.*;
enum Currency{
    PENNY(1), NICKLE(5), DIME(10), QUARTER(25);
    private int value;

    private Currency(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

}

public class EnumDemo {
    public static void main(String[] args){
        Currency coin1 = Currency.QUARTER;
        Currency coin2 = Currency.NICKLE;

        System.out.println(coin1);
        System.out.println(coin2);

        for(Currency coin : Currency.values()){
            System.out.println("coin: " + coin.getValue());
        }
    }
}