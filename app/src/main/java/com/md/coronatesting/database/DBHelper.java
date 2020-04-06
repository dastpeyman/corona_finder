package com.md.coronatesting.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.md.coronatesting.model.Statistics;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Kusenko on 29.09.2016.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "corona.db";
    private static final String TABLE_STATISTICS = "statistics_covid19";
    private static final String TABLE_BOOKMARK_SERVERS = "bookmark_servers";
    private static final String TAG = "DBHelper";

    private static final String KEY_ID = "_id_statistics";
    private static final String KEY_COUNTRY = "country";
    private static final String KEY_CASES = "cases";
    private static final String KEY_TODAYCASES = "todayCases";
    private static final String KEY_DEATHS = "deaths";
    private static final String KEY_TODAYEDEATHS = "todayDeaths";
    private static final String KEY_RECOVERED = "recovered";
    private static final String KEY_ACTIVE = "active";
    private static final String KEY_CRITICAL = "critical";
    private static final String KEY_CASESPERONEMILLION = "casesPerOneMillion";
    private static final String KEY_DEATHSPERONEMILLION = "deathsPerOneMillion";
//    private static final String KEY_LAT = "lat";
//    private static final String KEY_LON = "lon";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db, TABLE_STATISTICS);
        createTable(db, TABLE_BOOKMARK_SERVERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_STATISTICS);
//        db.execSQL("drop table if exists " + TABLE_BOOKMARK_SERVERS);
        onCreate(db);
    }

    private void createTable(SQLiteDatabase db, String name) {
        db.execSQL("create table " + name + "("
                + KEY_ID + " integer primary key,"
                + KEY_COUNTRY + " text,"
                + KEY_CASES + " integer,"
                + KEY_TODAYCASES + " integer,"
                + KEY_DEATHS + " integer,"
                + KEY_TODAYEDEATHS + " integer,"
                + KEY_RECOVERED + " integer,"
                + KEY_ACTIVE + " integer,"
                + KEY_CRITICAL + " integer,"
                + KEY_CASESPERONEMILLION + " real,"
                + KEY_DEATHSPERONEMILLION + " real,"
//                + KEY_LAT + " real,"
//                + KEY_LON + " real,"
                + "UNIQUE ("
                + KEY_COUNTRY
                + ") ON CONFLICT IGNORE"
                + ")");
    }

//    public void setInactive(String ip) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(KEY_QUALITY, 0);
//        db.update(TABLE_SERVERS, values, KEY_IP + " = ?", new String[] {ip});
//
//        db.close();
//    }

//    public boolean setIpInfo(JSONArray response, List<Statistics> serverList) {
//        boolean result = false;
//        SQLiteDatabase db = this.getWritableDatabase();
//        for (int i = 0; i < response.length(); i++) {
//            try {
//                JSONObject ipInfo = new JSONObject(response.get(i).toString());
//                String country = ipInfo.get(KEY_COUNTRY).toString();
//
//                ContentValues values = new ContentValues();
//                values.put(KEY_COUNTRY, country);
//                values.put(KEY_REGION_NAME, ipInfo.get(KEY_REGION_NAME).toString());
//                values.put(KEY_LAT, ipInfo.getDouble(KEY_LAT));
//                values.put(KEY_LON, ipInfo.getDouble(KEY_LON));
//
//                db.update(TABLE_SERVERS, values, KEY_IP + " = ?", new String[] {ipInfo.get("query").toString()});
//
//                serverList.get(i).setCity(city);
//                result = true;
//            } catch (JSONException e) {
//                result = false;
//                e.printStackTrace();
//            }
//        }
//        db.close();
//
//        return result;
//    }

    public void clearTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STATISTICS, null, null);
        db.close();
    }

//    public void setBookmark(Server server) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//
//        contentValues.put(KEY_HOST_NAME, server.getHostName());
//        contentValues.put(KEY_IP, server.getIp());
//        contentValues.put(KEY_SCORE, server.getScore());
//        contentValues.put(KEY_PING, server.getPing());
//        contentValues.put(KEY_SPEED, server.getSpeed());
//        contentValues.put(KEY_COUNTRY_LONG, server.getCountryLong());
//        contentValues.put(KEY_COUNTRY_SHORT, server.getCountryShort());
//        contentValues.put(KEY_NUM_VPN_SESSIONS, server.getNumVpnSessions());
//        contentValues.put(KEY_UPTIME, server.getUptime());
//        contentValues.put(KEY_TOTAL_USERS, server.getTotalUsers());
//        contentValues.put(KEY_TOTAL_TRAFFIC, server.getTotalTraffic());
//        contentValues.put(KEY_LOG_TYPE, server.getLogType());
//        contentValues.put(KEY_OPERATOR, server.getOperator());
//        contentValues.put(KEY_MESSAGE, server.getMessage());
//        contentValues.put(KEY_CONFIG_DATA, server.getConfigData());
//        contentValues.put(KEY_TYPE, server.getType());
//        contentValues.put(KEY_QUALITY, server.getQuality());
//        contentValues.put(KEY_CITY, server.getCity());
//
//        db.insert(TABLE_BOOKMARK_SERVERS, null, contentValues);
//        db.close();
//    }

//    public void delBookmark(Server server) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_BOOKMARK_SERVERS, KEY_IP + " = ?", new String[] {server.getIp()});
//        db.close();
//    }
//
//    public List<Server> getBookmarks() {
//        List<Server> serverList = new ArrayList<Server>();
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.query(TABLE_BOOKMARK_SERVERS, null, null, null, null, null, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                serverList.add(parseServer(cursor));
//            } while (cursor.moveToNext());
//        } else {
//            Log.d(TAG ,"0 rows");
//        }
//
//        cursor.close();
//        db.close();
//
//        return serverList;
//    }
//
//    public boolean checkBookmark(Server server) {
//        boolean result = false;
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.query(TABLE_BOOKMARK_SERVERS,
//                null,
//                KEY_IP + "=?",
//                new String[]{server.getIp()},
//                null,
//                null,
//                null);
//
//        if (cursor.moveToFirst()) {
//            result = true;
//        } else {
//            Log.d(TAG ,"0 rows");
//        }
//
//        cursor.close();
//        db.close();
//
//        return result;
//    }

    public void putLine(Statistics statistics) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();

            contentValues.put(KEY_COUNTRY, statistics.getCountry());
            contentValues.put(KEY_DEATHS, statistics.getDeaths());
            contentValues.put(KEY_CASES, statistics.getCases());
            contentValues.put(KEY_TODAYCASES, statistics.getTodayCases());
            contentValues.put(KEY_DEATHS, statistics.getDeaths());
            contentValues.put(KEY_TODAYEDEATHS, statistics.getTodayDeaths());
            contentValues.put(KEY_RECOVERED, statistics.getRecovered());
            contentValues.put(KEY_ACTIVE, statistics.getActive());
            contentValues.put(KEY_CRITICAL, statistics.getCritical());
            contentValues.put(KEY_CASESPERONEMILLION, statistics.getCasesPerOneMillion());
            contentValues.put(KEY_DEATHSPERONEMILLION, statistics.getDeathsPerOneMillion());

            db.insert(TABLE_STATISTICS, null, contentValues);
            db.close();
        }

//    public long getCount() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        SQLiteStatement statement = db.compileStatement("SELECT COUNT(*) FROM " + TABLE_SERVERS);
//        long count = statement.simpleQueryForLong();
//        db.close();
//        return count;
//    }
//
//    public long getCountBasic() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        SQLiteStatement statement = db.compileStatement("SELECT COUNT(*) FROM "
//                + TABLE_SERVERS
//                + " WHERE "
//                + KEY_TYPE
//                + " = 0");
//        long count = statement.simpleQueryForLong();
//        db.close();
//        return count;
//    }

//    public long getCountAdditional() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        SQLiteStatement statement = db.compileStatement("SELECT COUNT(*) FROM "
//                + TABLE_SERVERS
//                + " WHERE "
//                + KEY_TYPE
//                + " = 1");
//        long count = statement.simpleQueryForLong();
//        db.close();
//        return count;
//    }

//    public List<Statistics> getUniqueCountries() {
//        List<Statistics> countryList = new ArrayList<Statistics>();
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.query(TABLE_STATISTICS,
//                null,
//                null,
//                null,
//                KEY_COUNTRY,
//                "MAX(" + KEY_QUALITY + ")",
//                null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                countryList.add(parseServer(cursor));
//            } while (cursor.moveToNext());
//        } else {
//            Log.d(TAG ,"0 rows");
//        }
//
//        cursor.close();
//        db.close();
//
//        return countryList;
//    }

    public List<Statistics> getUniqueCountries() {
        List<Statistics> serverList = new ArrayList<Statistics>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "
                + TABLE_STATISTICS
                + " WHERE "
                + KEY_CASES
                + " <> 0", null);

        if (cursor.moveToFirst()) {
            do {
                serverList.add(parseServer(cursor));
            } while (cursor.moveToNext());
        } else {
            Log.d(TAG ,"0 rows");
        }

        cursor.close();
        db.close();

        return serverList;
    }

//    public List<Server> getServersByCountryCode(String country) {
//        List<Server> serverList = new ArrayList<Server>();
//        if (country != null) {
//            SQLiteDatabase db = this.getWritableDatabase();
//            Cursor cursor = db.query(TABLE_SERVERS,
//                    null,
//                    KEY_COUNTRY_SHORT + "=?",
//                    new String[]{country},
//                    null,
//                    null,
//                    KEY_QUALITY + " DESC");
//
//            if (cursor.moveToFirst()) {
//                do {
//                    serverList.add(parseServer(cursor));
//                } while (cursor.moveToNext());
//            } else {
//                Log.d(TAG ,"0 rows");
//            }
//
//            cursor.close();
//            db.close();
//        }

//        return serverList;
//    }

//    private Server parseGoodRandomServer(Cursor cursor, SQLiteDatabase db) {
//        List<Server> serverListExcellent = new ArrayList<Server>();
//        List<Server> serverListGood = new ArrayList<Server>();
//        List<Server> serverListBad = new ArrayList<Server>();
//
//        if (cursor.moveToFirst()) {
//            do {
//                switch (cursor.getInt(16)) {
//                    case 1:
//                        serverListBad.add(parseServer(cursor));
//                        break;
//                    case 2:
//                        serverListGood.add(parseServer(cursor));
//                        break;
//                    case 3:
//                        serverListExcellent.add(parseServer(cursor));
//                        break;
//                }
//
//            } while (cursor.moveToNext());
//        } else {
//            Log.d(TAG ,"0 rows");
//        }
//
//        cursor.close();
//        db.close();
//
//        Random random = new Random();
//        if (serverListExcellent.size() > 0) {
//            return serverListExcellent.get(random.nextInt(serverListExcellent.size()));
//        } else if (serverListGood.size() > 0) {
//            return serverListGood.get(random.nextInt(serverListGood.size()));
//        } else if (serverListBad.size() > 0) {
//            return serverListBad.get(random.nextInt(serverListBad.size()));
//        }
//
//        return null;
//    }

//    public Server getSimilarServer(String country, String ip) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery("SELECT * FROM "
//                + TABLE_SERVERS
//                + " WHERE "
//                + KEY_QUALITY
//                + " <> 1 AND "
//                + KEY_COUNTRY_LONG
//                + " = ? AND "
//                + KEY_IP
//                + " <> ?", new String[] {country, ip});
//
//
//        return parseGoodRandomServer(cursor, db);
//    }

//    public Server getGoodRandomServer(String country) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor;
//        if (country != null) {
//            cursor = db.rawQuery("SELECT * FROM "
//                    + TABLE_SERVERS
//                    + " WHERE "
//                    + KEY_QUALITY
//                    + " <> 0 AND "
//                    + KEY_COUNTRY_LONG
//                    + " = ?", new String[] {country});
//        } else {
//            cursor = db.rawQuery("SELECT * FROM "
//                    + TABLE_SERVERS
//                    + " WHERE "
//                    + KEY_QUALITY
//                    + " <> 0", null);
//        }
//
//        return parseGoodRandomServer(cursor, db);
//    }

    private Statistics parseServer(Cursor cursor) {
        return new Statistics(
                cursor.getString(1),
                cursor.getInt(2),
                cursor.getInt(3),
                cursor.getInt(4),
                cursor.getInt(5),
                cursor.getInt(6),
                cursor.getInt(7),
                cursor.getInt(8),
                cursor.getDouble(9),
                cursor.getDouble(10)
        );
    }
}

