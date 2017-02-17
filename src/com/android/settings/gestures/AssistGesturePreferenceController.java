/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.gestures;

import android.content.Context;
import android.provider.Settings;
import android.support.v7.preference.Preference;
import android.util.ArrayMap;

import com.android.settings.core.lifecycle.Lifecycle;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.search2.InlineSwitchPayload;
import com.android.settings.search2.ResultPayload;

public class AssistGesturePreferenceController extends GesturePreferenceController {

    private static final String PREF_KEY_VIDEO = "gesture_assist_video";
    private static final String PREF_KEY_ASSIST_GESTURE = "gesture_assist";

    public AssistGesturePreferenceController(Context context, Lifecycle lifecycle) {
        super(context, lifecycle);
    }

    @Override
    public boolean isAvailable() {
        AssistGestureFeatureProvider provider =
                FeatureFactory.getFactory(mContext).getAssistGestureFeatureProvider();
        return provider.isSupported(mContext);
    }

    @Override
    protected String getVideoPrefKey() {
        return PREF_KEY_VIDEO;
    }

    @Override
    public String getPreferenceKey() {
        return PREF_KEY_ASSIST_GESTURE;
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        final boolean enabled = (boolean) newValue;
        Settings.Secure.putInt(mContext.getContentResolver(),
                Settings.Secure.ASSIST_GESTURE_ENABLED, enabled ? 1 : 0);
        return true;
    }

    @Override
    protected boolean isSwitchPrefEnabled() {
        final int assistGestureEnabled = Settings.Secure.getInt(mContext.getContentResolver(),
                Settings.Secure.ASSIST_GESTURE_ENABLED, 1);
        return assistGestureEnabled != 0;
    }
}
