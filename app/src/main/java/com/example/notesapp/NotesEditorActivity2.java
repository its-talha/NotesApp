package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import java.util.HashSet;

public class NotesEditorActivity2 extends AppCompatActivity {

   EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_editor2);


        editText =findViewById(R.id.editText);
        Intent intent = getIntent();
        final int editnoteId = intent.getIntExtra("noteId",-1);

       if(editnoteId != -1){
            editText.setText(MainActivity.notes.get(editnoteId));
            Log.i("noteId", String.valueOf(editnoteId));
        }

       editText.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {

               MainActivity.notes.set(editnoteId,String.valueOf(s));
               MainActivity.arrayAdapter.notifyDataSetChanged();

               SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.notesapp", Context.MODE_PRIVATE);
               HashSet<String> set=new HashSet<String>(MainActivity.notes);
               sharedPreferences.edit().putStringSet("Notes",set).apply();


           }

           @Override
           public void afterTextChanged(Editable s) {

           }
       });
    }
}