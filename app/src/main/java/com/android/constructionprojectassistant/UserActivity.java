package com.android.constructionprojectassistant;

import android.support.v4.app.Fragment;

/**
 * Created by Dymyll on 5/3/2018.
 */

public class UserActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new LoginFragment();
    }
}
