package com.example.gestionsimpledestudiants;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;

import com.example.gestionsimpledestudiants.classes.Etudiant;
import com.example.gestionsimpledestudiants.service.EtudiantService;

public class MainActivity extends AppCompatActivity {

    EditText nom, prenom, id;
    TextView result;
    Button add, find, delete;

    EtudiantService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // 🔥 IMPORTANT

        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        id = findViewById(R.id.id);
        result = findViewById(R.id.result);

        add = findViewById(R.id.btnAdd);
        find = findViewById(R.id.btnFind);
        delete = findViewById(R.id.btnDelete);

        service = new EtudiantService(this);

        add.setOnClickListener(v -> {
            service.create(new Etudiant(
                    nom.getText().toString(),
                    prenom.getText().toString()
            ));
            Toast.makeText(this, "Ajouté", Toast.LENGTH_SHORT).show();
        });

        find.setOnClickListener(v -> {
            Etudiant e = service.findById(
                    Integer.parseInt(id.getText().toString())
            );

            if (e != null)
                result.setText(e.getNom() + " " + e.getPrenom());
            else
                result.setText("Introuvable");
        });

        delete.setOnClickListener(v -> {
            service.delete(Integer.parseInt(id.getText().toString()));
            Toast.makeText(this, "Supprimé", Toast.LENGTH_SHORT).show();
        });
    }
}