package com.example.parcial4janeth;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parcial4janeth.adapter.ClientAdapter;
import com.example.parcial4janeth.models.MdClientesModel;
import com.example.parcial4janeth.repository.MdClientesRepo;

import java.util.ArrayList;
import java.util.List;

public class ClienteActivity  extends AppCompatActivity {

    private ClientAdapter adapter;

    private ArrayList<MdClientesModel> arrayList;
    private RecyclerView rv;

    private EditText eNombre, eApellido, eDir, eCuidad;

    private MdClientesRepo repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cliente);

        rv = findViewById(R.id.lista);
        this.eNombre = findViewById(R.id.eNombre);
        this.eApellido = findViewById(R.id.eApellido);
        this.eDir = findViewById(R.id.eDir);
        this.eCuidad = findViewById(R.id.eCuidad);
        listar();
    }

    private void listar(){
        rv.setLayoutManager(new LinearLayoutManager(this));

        this.repo = new MdClientesRepo(this);

        adapter = new ClientAdapter((ArrayList<MdClientesModel>) this.repo.viewClients(0, true));
        rv.setAdapter(adapter);
    }

    public void guardarRegistro(View v){
        if(!eNombre.getText().toString().equals("")
                || !eApellido.getText().toString().equals("") || !eDir.getText().toString().equals("")
        || !eCuidad.getText().toString().equals("")) {

            MdClientesModel c = new MdClientesModel();
            c.setsNombreCliente(eNombre.getText().toString());
            c.setsApellidosCliente(eApellido.getText().toString());
            c.setsDireccionCliente(eDir.getText().toString());
            c.setsCuidadCliente(eCuidad.getText().toString());

            c = this.repo.insertClient(c);

            if (c.getIdCliente() > 0) {
                Toast.makeText(ClienteActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                limpiar();
                listar();

            } else {
                Toast.makeText(ClienteActivity.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(ClienteActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
        }
    }

    private void limpiar(){
        this.eNombre.setText("");
        this.eApellido.setText("");
        this.eDir.setText("");
        this.eCuidad.setText("");
    }
}
