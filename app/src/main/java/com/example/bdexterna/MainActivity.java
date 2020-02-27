package com.example.bdexterna;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText edtConsultar;
    private Button btnConsultar;
    private TextView txtEmail;
    private Helper bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bd = new Helper(this);

        edtConsultar = findViewById(R.id.edtConsulta);
        btnConsultar = findViewById(R.id.btnConsulta);
        txtEmail = findViewById(R.id.txtEmail);

        final SQLiteDatabase leerBD = bd.getReadableDatabase();

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = edtConsultar.getText().toString();
                String[] fields = {Helper.DATABASE_COLUMN_EMAIL};
                Cursor c;
                c = leerBD.query(Helper.DATABASE_TABLE_NAME,
                        fields,
                        Helper.DATABASE_COLUMN_NOMBRE + " LIKE '" + nombre + "%'",
                        null, null, null, null);
                List<String> emails = new ArrayList<>();
                if (c.moveToFirst()) {
                    do {
                        emails.add(c.getString(0) + "\n");
                    } while(c.moveToNext());
                    String escribirEmail = "";
                    for (String email: emails) {
                        escribirEmail = escribirEmail.concat(email);
                    }
                    txtEmail.setText(escribirEmail);
                    edtConsultar.setText("");
                } else {
                    Toast.makeText(MainActivity.this,
                            "El nombre introducido no se encuentra en la bd",
                            Toast.LENGTH_SHORT).show();
                    txtEmail.setText("email...");
                }
                c.close();
            }
        });

        edtConsultar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    btnConsultar.setEnabled(true);
                } else {
                    btnConsultar.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bd.close();
    }
}
