package android.com.zlt;

import android.app.Application;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2018/6/20.
 */

public class MyApplication extends Application {
    private Map<String, String> mDeliveryCompanyTable = new HashMap<>();

    public String getDeliveryCompanyNo(String deliveryCompanyName) throws RuntimeException {

        if (mDeliveryCompanyTable.isEmpty()) {
            String[] names = getResources().getStringArray(R.array.delivery_company);
            String[] ids = getResources().getStringArray(R.array.delivery_company_id);

            if (names.length != ids.length) {
                throw new RuntimeException();
            }

            for (int i = 0; i < names.length; i++) {
                mDeliveryCompanyTable.put(names[i], ids[i]);
            }
        }

        return mDeliveryCompanyTable.get(deliveryCompanyName);
    }
}
