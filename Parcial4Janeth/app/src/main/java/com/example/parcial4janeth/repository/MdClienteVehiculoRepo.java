package com.example.parcial4janeth.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.parcial4janeth.models.MdClienteVehiculoModel;
import com.example.parcial4janeth.utils.ConexionDb;
import com.example.parcial4janeth.utils.Constants;

import java.util.ArrayList;

public class MdClienteVehiculoRepo extends ConexionDb{

    private Context context;

    public MdClienteVehiculoRepo(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public boolean insertCliVehi(MdClienteVehiculoModel model) {

        boolean isCreate = false;
        try {
            ConexionDb dbHelper = new ConexionDb(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("id_cliente", model.getIdCliente());
            values.put("id_vehiculo", model.getIdVehiculo());
            values.put("smatricula", model.getsMatricula());
            values.put("ikilometro", model.getiKilometros());
            db.insert(Constants.TABLE_NAME_CLI_VEHI, null, values);
            isCreate =true;
        } catch (Exception ex) {
            ex.toString();
        }
        return isCreate;
    }

    public boolean updateCliVehi(MdClienteVehiculoModel c) {

        boolean isUpdate = false;

        ConexionDb dbHelper = new ConexionDb(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + Constants.TABLE_NAME_CLI_VEHI
                    + " SET smatricula = '" + c.getsMatricula()
                    + "', ikilometro = '" + c.getiKilometros()
                    + "' WHERE id_cliente='" + c.getIdCliente()
                    + "' and id_vehiculo='"+c.getIdVehiculo()+"'");
            isUpdate = true;
        } catch (Exception ex) {
            ex.toString();
            isUpdate = false;
        } finally {
            db.close();
        }

        return isUpdate;
    }

    public boolean deleteCliVehi(int id, int id2) {

        boolean idDelete = false;

        ConexionDb dbHelper = new ConexionDb(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + Constants.TABLE_NAME_CLI_VEHI
                    + " WHERE id_cliente = '" + id + "' and id_vehiculo='"+id2+"'");
            idDelete = true;
        } catch (Exception ex) {
            ex.toString();
            idDelete = false;
        } finally {
            db.close();
        }
        return idDelete;
    }

    public Object viewCliVehi(int id, int id2) {

        ConexionDb dbHelper = new ConexionDb(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor;
        String subQuery ="";
        if(id!=0 && id2!=0)
            subQuery = " where id_cliente='"+id+ "' and id_vehiculo='"+id2+"'";

        cursor = db.rawQuery("SELECT * FROM " + Constants.TABLE_NAME_CLI_VEHI
                + subQuery+" ORDER BY smatricula ASC", null);

        return this.recordToTable(cursor);
    }

    private Object recordToTable(Cursor cursor){
        ArrayList<MdClienteVehiculoModel> lista = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                MdClienteVehiculoModel model = new MdClienteVehiculoModel();
                model.setIdCliente(cursor.getInt(0));
                model.setIdVehiculo(cursor.getInt(1));
                model.setsMatricula(cursor.getString(2));
                model.setiKilometros(cursor.getString(3));
                lista.add(model);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return lista.size()>1 ? lista : lista.get(0);
    }
}
