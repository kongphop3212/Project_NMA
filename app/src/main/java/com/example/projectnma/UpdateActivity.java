package com.example.projectnma;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projectnma.databinding.ActivityUpdate2Binding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.UUID;

public class UpdateActivity extends AppCompatActivity {
    private String id,title,description;
    ActivityUpdate2Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityUpdate2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent=getIntent();
        id=intent.getStringExtra("id");
        title=intent.getStringExtra("title");
        description=intent.getStringExtra("description");

        binding.title.setText(title);
        binding.description.setText(description);
        binding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog progressDialog=new ProgressDialog(view.getContext());
                progressDialog.setTitle("Deleting");
                FirebaseFirestore.getInstance()
                        .collection("notes")
                        .document(id)
                        .delete();
                finish();
            }
        });

        binding.updateNote.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                title = binding.title.getText().toString();
                description=binding.description.getText().toString();
                updateNote();
            }
        });
    }
    private void updateNote() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Updating");
        progressDialog.setMessage("Your Note");
        progressDialog.show();
        NotesModel notesModel = new NotesModel(id, title, description, firebaseAuth.getUid());
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("notes")
                .document(id)
                .update("title", title, "description", description)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(UpdateActivity.this, "Note Updated", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        finish(); // ปิดหน้าต่าง UpdateActivity หลังจากอัพเดตสำเร็จ
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UpdateActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss(); // เปลี่ยนจาก progressDialog.cancel() เป็น progressDialog.dismiss()
                    }
                });
    }
}