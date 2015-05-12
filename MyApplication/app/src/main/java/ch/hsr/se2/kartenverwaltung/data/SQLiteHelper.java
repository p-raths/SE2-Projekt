package ch.hsr.se2.kartenverwaltung.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

/**
 * Created by Fehr on 16.04.2015.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String CARDTYPE_TABLE = "TCardType";
    public static final String CARD_TABLE  = "TCard";
    public static final String SETTINGCATEGORY_TABLE  = "TSettingCategory";
    public static final String SETTING_TABLE  = "TSetting";
    public static final String DEVICEDESCRIPTION_TABLE  = "TDeviceDescription";
    public static final String DEVICE_TABLE  = "TDevice";
    public static final String ATTRIBUTETYPE_TABLE  = "TAttributeType";
    public static final String ATTRIBUTE_TABLE  = "TAttribute";
    public static final String USER_TABLE  = "TUser";
    public static final String LOCATION_TABLE  = "TLocation";

    // cardtype columns
    public static final String CARDTYPE_COLUMN_CATID = "CatId";
    public static final String CARDTYPE_COLUMN_CATNAME = "CatName";
    public static final String CARDTYPE_COLUMN_CATDESCRIPTION = "CatDescription";
    public static final String CARDTYPE_COLUMN_CATDEFAULTATTRIBUTES = "CatDefaultAttributes";

    // card columns
    public static final String CARD_COLUMN__ID = "_id";
    public static final String CARD_COLUMN_CARDID = "CardId";
    public static final String CARD_COLUMN_CARDNAME = "CardName";
    public static final String CARD_COLUMN_CARDDESCRIPTION = "CardDescription";
    public static final String CARD_COLUMN_CARDCREATEDON = "CardCreatedOn";
    public static final String CARD_COLUMN_CATID = "CatId";
    public static final String CARD_COLUMN_USRID = "UsrId";
    public static final String CARD_COLUMN_CARDREVISION = "CardRevision";

    // settingcategory columns
    public static final String SETTINGCATEGORY_COLUMN_SECID = "SecId";
    public static final String SETTINGCATEGORY_SECNAME = "SecName";

    // setting columns
    public static final String SETTING_COLUMN_SETID = "SetId";
    public static final String SETTING_COLUMN_SETNAME = "SetName";
    public static final String SETTING_COLUMN_SETVALUE = "SetValue";
    public static final String SETTING_COLUMN_SECID = "SecId";
    public static final String SETTING_COLUMN_USRID = "UsrId";

    // devicedescription columns
    public static final String DEVICEDESCRIPTION_COLUMN_DEDID = "DedId";
    public static final String DEVICEDESCRIPTION_COLUMN_DEDNAME = "DedName";

    // device columns
    public static final String DEVICE_COLUMN_DEVID = "DevId";
    public static final String DEVICE_COLUMN_DEVNAME = "DevName";
    public static final String DEVICE_COLUMN_DEVISSYNCHRONIZED = "DevIsSynchronized";
    public static final String DEVICE_COLUMN_DEDID = "DedId";

    // attributetype columns
    public static final String ATTRIBUTETYPE_COLUMN_ATTID = "AttId";
    public static final String ATTRIBUTETYPE_COLUMN_ATTNAME = "AttName";
    public static final String ATTRIBUTETYPE_COLUMN_ATTMAXLENGTH = "AttMaxLength";
    public static final String ATTRIBUTETYPE_COLUMN_ATTMAXPRECISION = "AttMaxPrecision";

    // attribute columns
    public static final String ATTRIBUTE_COLUMN_ATRID = "AtrId";
    public static final String ATTRIBUTE_COLUMN_ATRNAME = "AtrName";
    public static final String ATTRIBUTE_COLUMN_ATRVALUE = "AtrValue";
    public static final String ATTRIBUTE_COLUMN_ATTID = "AttId";
    public static final String ATTRIBUTE_COLUMN_CARDID = "CardId";

    // user columns
    public static final String USER_COLUMN_USRID = "UsrId";
    public static final String USER_COLUMN_USRNAME = "UsrName";
    public static final String USER_COLUMN_USRPASSWORD = "UsrPassword";

    // location columns
    public static final String LOCATION_COLUMN_LOCID = "LocId";
    public static final String LOCATION_COLUMN_LOCPOSITION = "LocPosition";
    public static final String LOCATION_COLUMN_LOCNUMBEROFVISITS = "LocNumberOfVisits";
    public static final String LOCATION_COLUMN_CARDID = "CardId";


    private static final String DATABASE_NAME = "kartenverwaltung.db";
    private static final int DATABASE_VERSION = 3;


    private static final String CREATE_TABLE_CARDTYPE = "CREATE TABLE "
            + CARDTYPE_TABLE + " ( " + CARDTYPE_COLUMN_CATID + " integer primary key autoincrement, "
            + CARDTYPE_COLUMN_CATNAME + " text, "
            + CARDTYPE_COLUMN_CATDESCRIPTION + " text, "
            + CARDTYPE_COLUMN_CATDEFAULTATTRIBUTES + " text);";

    private static final String CREATE_TABLE_CARD = "CREATE TABLE "
            + CARD_TABLE + "(" + CARD_COLUMN__ID + " integer primary key autoincrement, "
            + CARD_COLUMN_CARDID + " integer not null autoincrement, "
            + CARD_COLUMN_CARDNAME + " text not null, "
            + CARD_COLUMN_CARDDESCRIPTION + " text, "
            + CARD_COLUMN_CARDCREATEDON + " text, "
            + CARD_COLUMN_CATID + " integer not null, "
            + CARD_COLUMN_USRID + " integer not null, "
            + CARD_COLUMN_CARDREVISION + " integer not null);";
//            + " FOREIGN KEY FK_CardCat(CatId) REFERENCES TCardType(CatId) "
//            + " ON UPDATE CASCADE ON DELETE RESTRICT, "
//            + " FOREIGN KEY FK_CardUsr(UsrId) REFERENCES TUser(UsrId) "
//            + " ON UPDATE CASCADE ON DELETE CASCADE);";

    private static final String CREATE_TABLE_SETTINGCATEGORY = "CREATE TABLE "
            + SETTINGCATEGORY_TABLE + "(" + SETTINGCATEGORY_COLUMN_SECID + " integer primary key autoincrement, "
            + SETTINGCATEGORY_SECNAME + "text not null);";

    private static final String CREATE_TABLE_SETTING = "CREATE TABLE "
            + SETTING_TABLE + "(" + SETTING_COLUMN_SETID + " integer primary key autoincrement, "
            + SETTING_COLUMN_SETNAME + " text not null, "
            + SETTING_COLUMN_SETVALUE + " text, "
            + SETTING_COLUMN_SECID + " integer not null, "
            + SETTING_COLUMN_USRID + " integer not null);";
//            + " FOREIGN KEY FK_SetUsr(UsrId) REFERENCES TUser(UsrId) "
//            + " ON UPDATE CASCADE ON DELETE CASCADE);";

    private static final String CREATE_TABLE_DEVICEDESCRIPTION = "CREATE TABLE "
            + DEVICEDESCRIPTION_TABLE + "(" + DEVICEDESCRIPTION_COLUMN_DEDID + " integer primary key autoincrement, "
            + DEVICEDESCRIPTION_COLUMN_DEDNAME + " text not null);";

    private static final String CREATE_TABLE_DEVICE = "CREATE TABLE "
            + DEVICE_TABLE + "(" + DEVICE_COLUMN_DEVID + " integer primary key autoincrement, "
            + DEVICE_COLUMN_DEVNAME + " text not null, "
            + DEVICE_COLUMN_DEVISSYNCHRONIZED + " integer, "
            + DEVICE_COLUMN_DEDID + " integer not null);";
//            + " FOREIGN KEY FK_DevDed(DedId) REFERENCES TDeviceDescription(DedId) "
//            + " ON UPDATE CASCADE ON DELETE RESTRICT);";

    private static final String CREATE_TABLE_ATTRIBUTETYPE = "CREATE TABLE "
            + ATTRIBUTETYPE_TABLE + "(" + ATTRIBUTETYPE_COLUMN_ATTID + " integer primary key autoincrement, "
            + ATTRIBUTETYPE_COLUMN_ATTNAME + " text not null, "
            + ATTRIBUTETYPE_COLUMN_ATTMAXLENGTH + " integer, "
            + ATTRIBUTETYPE_COLUMN_ATTMAXPRECISION + " integer);";

    private static final String CREATE_TABLE_ATTRIBUTE = "CREATE TABLE "
            + ATTRIBUTE_TABLE + "(" + ATTRIBUTE_COLUMN_ATRID + " integer primary key autoincrement, "
            + ATTRIBUTE_COLUMN_ATRNAME + " text not null, "
            + ATTRIBUTE_COLUMN_ATRVALUE + " text, "
            + ATTRIBUTE_COLUMN_ATTID + " integer not null, "
            + ATTRIBUTE_COLUMN_CARDID + " integer not null);";
//            + " FOREIGN KEY FK_AtrAtt(AttId) REFERENCES TAttributeType(AttId) "
//            + " ON UPDATE CASCADE ON DELETE RESTRICT);";

    private static final String CREATE_TABLE_USER = "CREATE TABLE "
            + USER_TABLE + "(" + USER_COLUMN_USRID + " integer primary key autoincrement, "
            + USER_COLUMN_USRNAME + " text not null, "
            + USER_COLUMN_USRPASSWORD + " text not null);";

    private static final String CREATE_TABLE_LOCATION = "CREATE TABLE "
            + LOCATION_TABLE + "(" + LOCATION_COLUMN_LOCID + " integer primary key autoincrement, "
            + LOCATION_COLUMN_LOCPOSITION + " text not null, "
            + LOCATION_COLUMN_LOCNUMBEROFVISITS + " integer, "
            + LOCATION_COLUMN_CARDID + " integer not null);";
//            + " FOREIGN KEY FK_LocCard(CardId) REFERENCES TCard(CardId)"
//            + " ON UPDATE CASCADE ON DELETE CASCADE);";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        database.execSQL(CREATE_TABLE_CARDTYPE);
        database.execSQL(CREATE_TABLE_SETTINGCATEGORY);
        database.execSQL(CREATE_TABLE_DEVICEDESCRIPTION);
        database.execSQL(CREATE_TABLE_ATTRIBUTETYPE);
        database.execSQL(CREATE_TABLE_USER);
        database.execSQL(CREATE_TABLE_SETTING);
        database.execSQL(CREATE_TABLE_DEVICE);
        database.execSQL(CREATE_TABLE_CARD);
        database.execSQL(CREATE_TABLE_LOCATION);
        database.execSQL(CREATE_TABLE_ATTRIBUTE);
  //      if(!checkDataBaseExists()){

  //      }
    }
/*
    @Override
    public void onConfigure(SQLiteDatabase db){

        db.setForeignKeyConstraintsEnabled(true);

    }
*/
    private boolean checkDataBaseExists() {
        SQLiteDatabase checkDB = null;
        try {
            checkDB = SQLiteDatabase.openDatabase( Environment.getDataDirectory() + "/data/Kartenverwaltung/databases/" + DATABASE_NAME, null,
                    SQLiteDatabase.OPEN_READONLY);
            checkDB.close();
        } catch (SQLiteException e) {
            // database doesn't exist yet.
        }
        return checkDB != null ? true : false;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + CARDTYPE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CARD_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SETTINGCATEGORY_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SETTING_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DEVICEDESCRIPTION_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DEVICE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ATTRIBUTETYPE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ATTRIBUTE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + LOCATION_TABLE);
        onCreate(db);
    }
}
