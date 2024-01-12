package com.weiren.zhang.library_common.storage;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tencent.mmkv.MMKV;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjp on 2020/5/26 10:27
 */
public class MmkvHelper {

    private static MMKV mmkv;
    private final Gson mGson = new Gson();

    private MmkvHelper() {
        mmkv = MMKV.defaultMMKV();
    }

    public static MmkvHelper getInstance() {
        return MmkvHolder.INSTANCE;
    }

    private static class MmkvHolder {
        private static final MmkvHelper INSTANCE = new MmkvHelper();
    }

    /**
     * 保存list
     */
    public <T> void saveList(String tag, List<T> dataList) {
        //if (null == dataList || dataList.size() <= 0)
        //return;
        //转换成json数据，再保存
        String strJson = mGson.toJson(dataList);
        mmkv.encode(tag, strJson);
    }

    /**
     * 获取带有泛型的List
     */
    public <T> List<T> getTList(String tag, Class<T> cls) {
        List<T> dataList = new ArrayList<>();
        String strJson = mmkv.decodeString(tag, null);
        if (null == strJson) {
            return dataList;
        }
        try {
            Gson gson = new Gson();
            JsonArray arry = new JsonParser().parse(strJson).getAsJsonArray();
            for (JsonElement jsonElement : arry) {
                dataList.add(gson.fromJson(jsonElement, cls));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }
}
