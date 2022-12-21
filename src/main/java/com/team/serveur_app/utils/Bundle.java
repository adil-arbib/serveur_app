package com.team.serveur_app.utils;

import java.util.HashMap;

public final class Bundle {
    private Bundle(){
    }
    private static final Bundle bundle = new Bundle();
    private final HashMap<String,Object> map = new HashMap<>();
    public static Bundle getInstance(){
        return bundle;
    }
    public void put(String key, Object value){
        map.put(key, value);
    }
    public Object get(String key){
        return map.get(key);
    }
}
