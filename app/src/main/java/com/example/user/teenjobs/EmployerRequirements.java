package com.example.user.teenjobs;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class EmployerRequirements extends AppCompatActivity {

    String[] jobs = new String[]{"ברמן", "מלצר", "טבח", "מוכר", "קופאי"};
    boolean[] checked = new boolean[]{false, false, false, false, false};

    EditText[] ed_times = new EditText[jobs.length];
    LinearLayout linearLayout;

    AutoCompleteTextView enterJobTitle;
    Intent gi;
    FireBaseDB fireBaseDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_resume);

        fireBaseDB = new FireBaseDB(this);
        gi = getIntent();
        fillAutoCompleteJobs();
        linearLayout = findViewById(R.id.linearLayout);
        for (int i = 0; i < ed_times.length; i++) {
            ed_times[i] = new EditText(this);
            ed_times[i].setHint("how much time you worked in " + jobs[i]);
            ed_times[i].setVisibility(View.GONE);
            ed_times[i].setEms(10);
            linearLayout.addView(ed_times[i]);
        }
    }

    private void fillAutoCompleteJobs() {
        enterJobTitle = findViewById(R.id.enterJ);


        final ArrayList<Job> jobs = new ArrayList<>();
        jobs.addAll(Arrays.<Job>asList(new Job("טבח", "1")));

        final ArrayAdapter<Job> adapter = new ArrayAdapter<Job>(this,
                android.R.layout.simple_dropdown_item_1line, jobs);


        enterJobTitle.setAdapter(adapter);

        fireBaseDB.getAllJobs(new OnNewJobListener() {
            @Override
            public void onNewJob(Job job) {
                jobs.add(job);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void select(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("select works you been");
        builder.setMultiChoiceItems(jobs, checked, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                checked[i] = b;
            }
        });

        builder.setPositiveButton("next", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                dialogInterface.cancel();
            }
        });

        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                for (int i = 0; i < ed_times.length; i++) {
                    if (checked[i])
                        ed_times[i].setVisibility(View.VISIBLE);
                    else
                        ed_times[i].setVisibility(View.GONE);
                }
            }
        });

        builder.show();
    }

    public void end(View view) {
        String cv = "";
        boolean flag = true;
        int i = 0;
        while (i < ed_times.length && flag) {
            if (checked[i]) {
                String ed_jobTime = ed_times[i].getText().toString();
                if (ed_jobTime.isEmpty()) {
                    Toast.makeText(this, "fill all fields", Toast.LENGTH_SHORT).show();
                    flag = false;
                } else
                    cv += jobs[i] + " " + ed_jobTime + "\n";
            }
            i++;
        }
        //TODO:fixf
        ArrayList<String> requirements = new ArrayList<>();
        Employer employer = new Employer(
                gi.getStringExtra("Fname"),
                gi.getStringExtra("Lname"),
                gi.getStringExtra("email"),
                gi.getStringExtra("password"),
                gi.getStringExtra("Pnum"),

                gi.getStringExtra("city"),
                gi.getStringExtra("district"),
                gi.getStringExtra("Bname"),
                gi.getStringExtra("Bnum"),
                requirements
        );


        final VariableListener varDone = new VariableListener();
        varDone.setListener(new VariableListener.ChangeListener() {
            @Override
            public void onChange() {
                String message = (String) varDone.getVar();
                if (message == null)
                    startActivity(new Intent(EmployerRequirements.this, MainActivity.class));
                else
                    Toast.makeText(EmployerRequirements.this, message, Toast.LENGTH_SHORT).show();

            }
        });

        fireBaseDB.CreateNewEmployer(employer, varDone);

    }
}

