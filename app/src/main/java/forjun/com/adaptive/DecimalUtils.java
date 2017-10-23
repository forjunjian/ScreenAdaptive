package forjun.com.adaptive;

import android.text.TextUtils;

import java.text.DecimalFormat;

/**
 * Created by forjun on 2017/7/6.
 */

public class DecimalUtils {


    public static final String getMoneyFromFen(int money) {
        if (mDecimalFormatEndWith2 == null) {
            mDecimalFormatEndWith2 = new DecimalFormat("#0.00");
        }
        try {
            int oilActualAccount = money;
            if (oilActualAccount != 0) {
                double oilCount = Double.parseDouble(String.valueOf(oilActualAccount)) / 100;
                return "￥" + mDecimalFormatEndWith2.format(oilCount); //实付总价
            } else {
                return "￥0.00";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "￥0.00";
    }

    private static DecimalFormat mDecimalFormatEndWith2;

    /**
     * 保留两位小数
     *
     * @param qty
     * @return
     */
    public static final String getDecimalEndWith2(double qty) {
        if (mDecimalFormatEndWith2 == null) {
            mDecimalFormatEndWith2 = new DecimalFormat("#0.00");
        }
        try {
            if (qty != 0) {
                return mDecimalFormatEndWith2.format(qty); //实付总价
            } else {
                return "0.00";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0.00";
    }

    private static DecimalFormat mDecimalFormatEndWith0;

    /**
     * 保留0小数
     *
     * @param qty
     * @return
     */
    public static final String getDecimalEndWith0(double qty) {
        if (mDecimalFormatEndWith0 == null) {
            mDecimalFormatEndWith0 = new DecimalFormat("#0");
        }
        try {
            if (qty != 0) {
                return mDecimalFormatEndWith0.format(qty); //实付总价
            } else {
                return "0";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }

    private static DecimalFormat mDecimalFormatEndWith1;

    /**
     * 保留1小数
     *
     * @param qty
     * @return
     */
    public static final String getDecimalEndWith1(double qty) {
        if (mDecimalFormatEndWith1 == null) {
            mDecimalFormatEndWith1 = new DecimalFormat("#0.0");
        }
        try {
            if (qty != 0) {
                return mDecimalFormatEndWith1.format(qty); //实付总价
            } else {
                return "0.0";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0.0";
    }


    public static final String getDecimalEndWith1(String str) {
        double qty = 0;
        if (!TextUtils.isEmpty(str)) {
            try {
                qty = Double.parseDouble(str);
            } catch (Exception exception) {
            // TODO: 2017/9/26 接入友盟后把错误信息返回友盟后台
            }
        }
        return getDecimalEndWith1(qty);
    }

}
