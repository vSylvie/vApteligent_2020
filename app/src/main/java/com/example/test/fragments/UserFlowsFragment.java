package com.example.test.fragments;


import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import com.example.test.R;
import com.crittercism.app.Crittercism;

public class UserFlowsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_user_flows, container, false);


        ListView listView = (ListView) v.findViewById(R.id.user_flowsListView);
        final String labels[] = { "Login", "Confirm", "Cancel" };

        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(
                getActivity(),
                R.layout.user_flows_buttons,
                R.id.user_flowsName,
                labels)
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                String txnName = labels[position];

                Button begin = view.findViewById(R.id.beginButton);
                begin.setOnClickListener(new Begin_user_flowsButtonAction(txnName));

                Button end = view.findViewById(R.id.endButton);
                end.setOnClickListener(new End_user_flowsButtonAction(txnName));

                Button fail = view.findViewById(R.id.failButton);
                fail.setOnClickListener(new Fail_user_flowsButtonAction(txnName));

                //Button increment = view.findViewById(R.id.valueIncrementButton);
                //increment.setOnClickListener(new Increment_user_flowsButtonAction(txnName));

                Button cancel = view.findViewById(R.id.cancelButton);
                cancel.setOnClickListener(new Cancel_user_flowsButtonAction(txnName));

                return view;
            }
        };

        listView.setAdapter(arrayAdapter);

        return v;
    }



    public static void beginUserflow (String user_flowsName){
        Crittercism.beginUserflow(user_flowsName);
    }
    public static void endUserflow (String user_flowsName){
        Crittercism.endUserflow(user_flowsName);
    }
    public static void failUserflow (String user_flowsName){
        Crittercism.failUserflow(user_flowsName);
    }
    public static void cancelUserflow (String user_flowName){
        Crittercism.cancelUserflow(user_flowName);
    }

    private class Begin_user_flowsButtonAction extends user_flowsButtonAction {

        private Begin_user_flowsButtonAction(String user_flowsName) {
            super(user_flowsName);
        }

        @Override
        public void onClick(View v) {
            beginUserflow(this.user_flowsName);
            Toast.makeText(getActivity(), "The User flow started.", Toast.LENGTH_SHORT).show();
        }
    }

    private class End_user_flowsButtonAction extends user_flowsButtonAction {

        private End_user_flowsButtonAction(String user_flowsName) {
            super(user_flowsName);
        }

        @Override
        public void onClick(View v) {
            endUserflow(this.user_flowsName);
            Toast.makeText(getActivity(), "The User flow ended.", Toast.LENGTH_SHORT).show();
        }
    }

    private class Fail_user_flowsButtonAction extends user_flowsButtonAction {

        private Fail_user_flowsButtonAction(String user_flowsName) {
            super(user_flowsName);
        }

        @Override
        public void onClick(View v) {
            failUserflow(this.user_flowsName);
            Toast.makeText(getActivity(), "The User flow failed.", Toast.LENGTH_SHORT).show();
        }
    }

    private class Cancel_user_flowsButtonAction extends user_flowsButtonAction {

        private Cancel_user_flowsButtonAction(String user_flowsName){super (user_flowsName);}

        @Override
        public void onClick(View v){
            cancelUserflow(this.user_flowsName);
            Toast.makeText(getActivity(), "The User flow is canceled.", Toast.LENGTH_SHORT).show();
        }
    }

    /*private class Increment_user_flowsButtonAction extends user_flowsButtonAction {

        private Increment_user_flowsButtonAction(String user_flowsName) {
            super(user_flowsName);
        }

        @Override
        public void onClick(View v) {
            int value = Crittercism.getUserFlowValue(this.user_flowsName);
            if (value == -1) {
                // The value wasn't set
                value = 1;
            }

            Crittercism.setUserFlowValue(this.user_flowsName, value  + 1);
            Toast.makeText(getActivity(), "Value button selected", Toast.LENGTH_SHORT).show();
        }
    }*/

    private static abstract class user_flowsButtonAction implements View.OnClickListener {
        protected String user_flowsName;

        private user_flowsButtonAction(String user_flowsName) {
            this.user_flowsName = user_flowsName;
        }
    }

}
