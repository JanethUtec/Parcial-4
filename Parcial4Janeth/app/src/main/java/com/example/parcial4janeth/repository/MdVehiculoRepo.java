package com.example.parcial4janeth.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;


import com.example.parcial4janeth.models.MdVehiculosModel;
import com.example.parcial4janeth.utils.ConexionDb;
import com.example.parcial4janeth.utils.Constants;

import java.util.ArrayList;

public class MdVehiculoRepo extends ConexionDb {

    private Context context;

    public MdVehiculoRepo(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public MdVehiculosModel insertVehi(MdVehiculosModel model) {

        try {
            ConexionDb dbHelper = new ConexionDb(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("smarca", model.getsMarca());
            values.put("smodelo", model.getsModelo());
            int id = (int) db.insert(Constants.TABLE_NAME_VEHI, null, values);
            model.setIdVehiculo(id);
        } catch (Exception ex) {
            ex.toString();
            model.setIdVehiculo(0);
        }
        return model;
    }

    public boolean updateVehi(MdVehiculosModel c) {

        boolean isUpdate = false;

        ConexionDb dbHelper = new ConexionDb(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + Constants.TABLE_NAME_VEHI
                    + " SET smarca = '" + c.getsMarca()
                    + "', smodelo = '" + c.getsModelo()
                    + "' WHERE id_vehiculo='" + c.getIdVehiculo() + "' ");
            isUpdate = true;
        } catch (Exception ex) {
            ex.toString();
            isUpdate = false;
        } finally {
            db.close();
        }

        return isUpdate;
    }

    public boolean deleteVehi(int id) {

        boolean idDelete = false;

        ConexionDb dbHelper = new ConexionDb(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + Constants.TABLE_NAME_VEHI
                    + " WHERE id_vehiculo = '" + id + "'");
            idDelete = true;
        } catch (Exception ex) {
            ex.toString();
            idDelete = false;
        } finally {
            db.close();
        }
        return idDelete;
    }

    public Object viewVehi(int id) {

        ConexionDb dbHelper = new ConexionDb(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor;
        String subQuery ="";
        if(id!=0)
            subQuery = " where id_vehiculo='"+id+"'";

        cursor = db.rawQuery("SELECT * FROM " + Constants.TABLE_NAME_VEHI
                + subQuery+" ORDER BY smarca ASC", null);

        return this.recordToTable(cursor);
    }

    private Object recordToTable(Cursor cursor){
        ArrayList<MdVehiculosModel> lista = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                MdVehiculosModel model = new MdVehiculosModel();
                model.setIdVehiculo(cursor.getInt(0));
                model.setsMarca(cursor.getString(1));
                model.setsModelo(cursor.getString(2));
                lista.add(model);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return lista.size()>1 ? lista : lista.get(0);
    }
}
