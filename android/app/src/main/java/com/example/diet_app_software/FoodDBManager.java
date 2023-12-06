import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FoodDBManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Food.db";
    private static final String TABLE_NAME = "Food";
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (Name TEXT PRIMARY KEY, Calory INTEGER, Cost DECIMAL(5,2))";

    public FoodDBManager(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertFood(String name, int calory, double cost) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", name);
        contentValues.put("Calory", calory);
        contentValues.put("Cost", cost);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public Cursor getAllFood() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public boolean updateFood(String name, int calory, double cost) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Calory", calory);
        contentValues.put("Cost", cost);
        db.update(TABLE_NAME, contentValues, "Name = ?", new String[] { name });
        return true;
    }

    public Integer deleteFood(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "Name = ?", new String[] { name });
    }
}