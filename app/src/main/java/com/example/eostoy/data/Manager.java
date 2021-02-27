package com.example.eostoy.data;

import com.example.eostoy.R;

import java.util.ArrayList;

public class Manager {
    public static final int INTENT_DIAL = 0;
    public static final int INTENT_CALL = 1;
    public static final int INTENT_MSG = 2;
    public static final int INTENT_EMAIL = 3;
    public static final int INTENT_INTERNET = 4;

    private static ArrayList<IntentData> intentList = new ArrayList<>();

    /** intent list 를 반환. 만약 size 가 0 이라면 값을 셋팅한 후 반환 **/
    public static ArrayList<IntentData> getIntentList() {
        if (intentList.size() == 0) {
            //TODO add intent data
            intentList.add(new IntentData(R.drawable.img_dial, INTENT_DIAL, "전화 다이얼 화면으로 이동하기"));
            intentList.add(new IntentData(R.drawable.img_call, INTENT_CALL, "전화 걸기"));
            intentList.add(new IntentData(R.drawable.img_msg, INTENT_MSG, "메세지 보내기"));
            intentList.add(new IntentData(R.drawable.img_email, INTENT_EMAIL, "메일 보내기"));
            intentList.add(new IntentData(R.drawable.img_internet, INTENT_INTERNET, "인터넷 접속하기"));
        }

        return intentList;
    }
}
