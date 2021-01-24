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
package com.android.settings.deviceinfo.firmwareversion;

import android.content.Context;
import android.os.SystemProperties;
import androidx.annotation.VisibleForTesting;

import com.android.settings.R;
import com.android.settings.core.BasePreferenceController;

public class KyuofoxVersionPreferenceController extends BasePreferenceController {

    @VisibleForTesting
    private static final String KYUOFOX_VERSION_PROPERTY = "ro.kyuofox.build.version";
    private static final String KYUOFOX_BUILD_TYPE = "ro.kyuofox.buildtype";

    public KyuofoxVersionPreferenceController(Context context, String preferenceKey) {
        super(context, preferenceKey);
    }

    @Override
    public int getAvailabilityStatus() {
        return AVAILABLE;
    }

    @Override
    public CharSequence getSummary() {
     String kyuofoxVer =  SystemProperties.get(KYUOFOX_VERSION_PROPERTY);
     String kyuofoxType = SystemProperties.get(KYUOFOX_BUILD_TYPE);

      if (!kyuofoxVer.isEmpty() && !kyuofoxType.isEmpty())
	    return kyuofoxVer + " /" + "/ " + kyuofoxType;
	else
            return mContext.getString(R.string.unknown);
    }
}