/*
 * Copyright 2019 VMware
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.test.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.test.R;
import com.example.test.errors.CppNativeCustomError;
import com.example.test.errors.CustomError;
import com.example.test.errors.IllegalArgumentCustomError;
import com.example.test.errors.IndexOutOfBoundsCustomError;
import com.example.test.errors.JsonCustomError;
import com.example.test.errors.NullPointerCustomError;
import com.example.test.errors.RecursiveNativeCustomError;
import com.example.test.errors.StrdupNativeCustomError;

public class ErrorFragment extends Fragment {

    private View v;
    private CustomError baseError = new CustomError();

    /**
     * 2 types of ErrorType
     */
    private enum ErrorType {
        CRASH,
        EXCEPTION
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        this.v = inflater.inflate(R.layout.fragment_error, container, false);

        setErrorAction(R.id.nullPointerCrashButton, NullPointerCustomError.class, ErrorType.CRASH);
        setErrorAction(R.id.indexOutOfBoundsCrashButton, IndexOutOfBoundsCustomError.class, ErrorType.CRASH);
        setErrorAction(R.id.recursiveNativeCrashButton, RecursiveNativeCustomError.class, ErrorType.CRASH);
        setErrorAction(R.id.strdupNativeCrashButton, StrdupNativeCustomError.class, ErrorType.CRASH);
        setErrorAction(R.id.cppNativeCrashButton, CppNativeCustomError.class, ErrorType.CRASH);
        return v;
    }

    /**
     * This helper function makes button setup slightly less ugly
     * @param buttonId The ID of the button to set an action for
     * @param errorClass A CustomError describing how to trigger a crash
     * @param type Either a CRASH or EXCEPTION enum
     */
    private void setErrorAction(int buttonId, final Class<? extends CustomError> errorClass, final ErrorType type) {
        Button b = v.findViewById(buttonId);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    CustomError error = errorClass.newInstance();
                    error.copyFrames(ErrorFragment.this.baseError);

                    if (type == ErrorType.CRASH) {
                        error.crash();
                    } else if (type == ErrorType.EXCEPTION) {
                        error.throwException();
                    } else {
                        throw new AssertionError("Unrecognized error type: " + type.ordinal());
                    }
                } catch (java.lang.InstantiationException e) {
                    throw new AssertionError(e);
                } catch (IllegalAccessException e) {
                    throw new AssertionError(e);
                }
            }
        });
    }
}
