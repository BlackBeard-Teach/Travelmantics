package com.blackbeard.travelmantics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertData extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    EditText textTitle;
    EditText textDescription;
    EditText textPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("traveldeals");
        textTitle = findViewById(R.id.travel_name);
        textDescription = findViewById(R.id.travel_description);
        textPrice = findViewById(R.id.travel_price);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.save_menu:
                saveDeal();
                Toast.makeText(this, "Deal saved", Toast.LENGTH_LONG).show();
                clean();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void clean() {
        textTitle.setText("");
        textPrice.setText("");
        textDescription.setText("");
        textTitle.requestFocus();
    }

    private void saveDeal() {
        String title = textTitle.getText().toString();
        String description = textDescription.getText().toString();
        String price = textPrice.getText().toString();
        TravelDeals deals = new TravelDeals(title, description, price, " ");
        databaseReference.push().setValue(deals);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu, menu);
        return true;
    }
}
