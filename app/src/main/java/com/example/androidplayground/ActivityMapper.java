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
        classMap.put("topic1", BasicComponentActivity.class);
        classMap.put("topic2", FragmentViewActivity.class);
        classMap.put("topic3", IntentsActivity.class);
        classMap.put("topic4", ImageActivity.class);
        classMap.put("topic5", AdvancedComponentActivity.class);
    }
}
