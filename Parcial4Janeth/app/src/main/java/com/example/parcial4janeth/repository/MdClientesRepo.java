package com.example.parcial4janeth.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.parcial4janeth.models.MdClientesModel;
import com.example.parcial4janeth.utils.ConexionDb;
import com.example.parcial4janeth.utils.Constants;

import java.util.ArrayList;

public class MdClientesRepo extends ConexionDb {

    private Context context;

    public MdClientesRepo(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public MdClientesModel insertClient(MdClientesModel c) {

        try {
            ConexionDb dbHelper = new ConexionDb(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("snombre_cliente", c.getsNombreCliente());
            values.put("sapellidos_cliente", c.getsApellidosCliente());
            values.put("sdireccion_cliente", c.getsDireccionCliente());
            values.put("scuidad_cliente", c.getsCuidadCliente());
            int id = (int) db.insert(Constants.TABLE_NAME_CLI, null, values);
            c.setIdCliente(id);
        } catch (Exception ex) {
            ex.toString();
            c.setIdCliente(0);
        }
        return c;
    }

    public boolean updateClient(MdClientesModel c) {

        boolean isUpdate = false;

        ConexionDb dbHelper = new ConexionDb(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + Constants.TABLE_NAME_CLI
                    + " SET snombre_cliente = '" + c.getsNombreCliente()
                    + "', sapellidos_cliente = '" + c.getsApellidosCliente()
                    + "', sdireccion_cliente = '" + c.getsDireccionCliente()
                    + "', scuidad_cliente = '" + c.getsCuidadCliente()
                    + "' WHERE id_cliente='" + c.getIdCliente() + "' ");
            isUpdate = true;
        } catch (Exception ex) {
            ex.toString();
            isUpdate = false;
        } finally {
            db.close();
        }

        return isUpdate;
    }

    public boolean deleteClient(int id) {

        boolean idDelete = false;

        ConexionDb dbHelper = new ConexionDb(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + Constants.TABLE_NAME_CLI
                    + " WHERE id_cliente = '" + id + "'");
            idDelete = true;
        } catch (Exception ex) {
            ex.toString();
            idDelete = false;
        } finally {
            db.close();
        }
        return idDelete;
    }

    public Object viewClients(int id, boolean isList) {

        ConexionDb dbHelper = new ConexionDb(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor;
        String subQuery = "";

        if(id!=0)
            subQuery = " where id_cliente='"+id+"'";

        cursor = db.rawQuery("SELECT * FROM " + Constants.TABLE_NAME_CLI
                + subQuery+" ORDER BY snombre_cliente ASC", null);

        return this.recordToTable(cursor, isList);
    }

    private Object recordToTable(Cursor cursor, boolean isList){
        ArrayList<MdClientesModel> lista = new ArrayList<>();
        boolean isExists = false;
        if (cursor.moveToFirst()) {
            isExists =true;
            do {
                MdClientesModel model = new MdClientesModel();
                model.setIdCliente(cursor.getInt(0));
                model.setsNombreCliente(cursor.getString(1));
                model.setsApellidosCliente(cursor.getString(2));
                model.setsDireccionCliente(cursor.getString(3));
                model.setsCuidadCliente(cursor.getString(4));
                lista.add(model);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return lista.size()>1 ? lista : (isExists) ? (isList) ? lista : lista.get(0): lista;
    }
}
