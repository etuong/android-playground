package com.example.androidplayground;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayContactActivity extends AppCompatActivity {
    private DatabaseHelper mydb;
    TextView name;
    TextView phone;
    TextView email;
    TextView street;
    TextView place;
    Button run;
    int id_To_Update = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contact);

        name = findViewById(R.id.editTextName);
        phone = findViewById(R.id.editTextPhone);
        email = findViewById(R.id.editTextStreet);
        street = findViewById(R.id.editTextEmail);
        place = findViewById(R.id.editTextCity);
        run = findViewById(R.id.button1);
        run.setOnClickListener(this::run);

        mydb = new DatabaseHelper(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int id = extras.getInt("id");

            if (id > 0) {
                Cursor rs = mydb.getData(id);
                id_To_Update = id;
                rs.moveToFirst();

                String name = rs.getString(rs.getColumnIndex(DatabaseHelper.CONTACTS_COLUMN_NAME));
                String phone = rs.getString(rs.getColumnIndex(DatabaseHelper.CONTACTS_COLUMN_PHONE));
                String email = rs.getString(rs.getColumnIndex(DatabaseHelper.CONTACTS_COLUMN_EMAIL));
                String street = rs.getString(rs.getColumnIndex(DatabaseHelper.CONTACTS_COLUMN_STREET));
                String place = rs.getString(rs.getColumnIndex(DatabaseHelper.CONTACTS_COLUMN_CITY));

                if (!rs.isClosed()) {
                    rs.close();
                }

                Button b = findViewById(R.id.button1);
                b.setVisibility(View.INVISIBLE);

                setReadonlyField(this.name, name);
                setReadonlyField(this.phone, phone);
                setReadonlyField(this.email, email);
                setReadonlyField(this.street, street);
                setReadonlyField(this.place, place);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            int id = extras.getInt("id");
            if (id > 0) {
                getMenuInflater().inflate(R.menu.menu_display_contact, menu);
            } else {
                getMenuInflater().inflate(R.menu.menu_create_contact, menu);
            }
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.edit_contact:
                Button b = (Button) findViewById(R.id.button1);
                b.setVisibility(View.VISIBLE);

                enableField(this.name);
                enableField(this.phone);
                enableField(this.email);
                enableField(this.street);
                enableField(this.place);
                return true;
            case R.id.delete_contact:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure?")
                        .setPositiveButton("Yes", (dialog, id) -> {
                            mydb.deleteContact(id_To_Update);
                            Toast.makeText(getApplicationContext(), "Deleted Successfully",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), DatabaseActivity.class);
                            startActivity(intent);
                        })
                        .setNegativeButton("No", (dialog, id) -> {
                            // User cancelled the dialog, do nothing
                        });

                AlertDialog d = builder.create();
                d.setTitle("Delete Contact");
                d.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void run(View view) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int id = extras.getInt("id");
            if (id > 0) {
                if (mydb.updateContact(id_To_Update, name.getText().toString(),
                        phone.getText().toString(), email.getText().toString(),
                        street.getText().toString(), place.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), DatabaseActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Not updated", Toast.LENGTH_SHORT).show();
                }
            } else {
                if (mydb.insertContact(name.getText().toString(), phone.getText().toString(),
                        email.getText().toString(), street.getText().toString(),
                        place.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Save",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Not saved",
                            Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(getApplicationContext(), DatabaseActivity.class);
                startActivity(intent);
            }
        }
    }

    private void setReadonlyField(TextView textview, String text) {
        textview.setText(text);
        textview.setFocusable(false);
        textview.setClickable(false);
    }

    private void enableField(TextView view) {
        view.setEnabled(true);
        view.setFocusableInTouchMode(true);
        view.setClickable(true);
    }
}
