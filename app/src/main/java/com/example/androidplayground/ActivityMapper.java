package com.example.androidplayground;

import android.app.Activity;

import java.util.HashMap;

public class ActivityMapper {
    private static ActivityMapper singleton;
    private HashMap<String, Class<? extends Activity>> classMap;

    public ActivityMapper() {
        defineExerciseMappings();
    }

    public static Class<? extends Activity> getExerciseClass(String exerciseId) {
        return getSingleton().classMap.get(exerciseId);
    }

    private static ActivityMapper getSingleton() {
        if (singleton == null) {
            singleton = new ActivityMapper();
        }
        return singleton;
    }

    private void defineExerciseMappings() {
        classMap = new HashMap<String, Class<? extends Activity>>();
        classMap.put("chap1ex1", BasicTextViewActivity.class);
        classMap.put("chap2ex1", BasicTextViewActivity.class);
    }
}
