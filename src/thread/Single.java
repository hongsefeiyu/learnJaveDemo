package thread;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Single {


    //server更改 C3
    public static void main(String[] args) {
        try {
            String str = calcReckReserveLimit(-1020601.48,0.0,0.0,"20");
            String str2 = new DecimalFormat(",###,###.00").format(new BigDecimal(str));

            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String calcReckReserveLimit(double stockBuyAmt, double repoAmt, double bondStockBuyAmt, String day) {
        //stockBuyAmt = -1020601.48
        //现货买入中的非债券部分
        double otherStockBuyAmt = stockBuyAmt - bondStockBuyAmt;
        //债券部分
        String bondReckReserveLimit = getReckReserveLimit(bondStockBuyAmt, day, "0.1");
        //非债券部分：现货买入中的非债券部分+回购部分
        String otherReckReserveLimit = getReckReserveLimit(repoAmt + otherStockBuyAmt, day, "0.18");
        return new BigDecimal(bondReckReserveLimit).add(new BigDecimal(otherReckReserveLimit)).toPlainString();
    }

    //client C8

    private static String getReckReserveLimit(double reckoningAmt, String day, String percent) {
        return new BigDecimal(String.valueOf(reckoningAmt)).divide(new BigDecimal(day)).multiply(new BigDecimal(percent)).toPlainString();
    }




}
