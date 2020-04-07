package com.mohsen.coronafinder.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mohsen.coronafinder.ApiServiceInterface;
import com.mohsen.coronafinder.R;
import com.mohsen.coronafinder.database.DBHelper;
import com.mohsen.coronafinder.model.Country;
import com.mohsen.coronafinder.model.Statistics;
import com.mohsen.coronafinder.util.BitmapGenerator;
import com.mohsen.coronafinder.util.ConnectionQuality;
import com.mohsen.coronafinder.util.LoadData;
import com.mohsen.coronafinder.util.map.MapCreator;
import com.mohsen.coronafinder.util.map.MyMarker;

import org.mapsforge.core.graphics.Bitmap;
import org.mapsforge.core.model.LatLong;
import org.mapsforge.core.model.Point;
import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.mapsforge.map.android.view.MapView;
import org.mapsforge.map.layer.Layers;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MapActivity extends AppCompatActivity {

    private MapView mapView;
    DBHelper dbHelper;
    private RelativeLayout parentLayout;
    private View relProgress;

    private PopupWindow popupWindow;

    private List<Statistics> countryList;
    private final String COUNTRY_FILE_NAME = "countries.json";

    private List<Country> countryLatLonList = null;
    private Layers layers;
    private AutoCompleteTextView country_autoComplete;
    List<String> countryListSearch = new ArrayList<>();
    private RelativeLayout relHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        parentLayout = (RelativeLayout) findViewById(R.id.homeContextRL);
        dbHelper = new DBHelper(this);
        dbHelper.clearTable();
        relProgress = (View) findViewById(R.id.relProgress);
        relProgress.setVisibility(View.VISIBLE);
        relHeader = (RelativeLayout) findViewById(R.id.relHeader);
        country_autoComplete = (AutoCompleteTextView) findViewById(R.id.country_autoComplete);

        getStatistics();


    }

    @Override
    protected void onResume() {
        super.onResume();
        invalidateOptionsMenu();
    }

    @Override
    protected void onDestroy() {
        if (mapView != null) {
            mapView.destroyAll();
            AndroidGraphicFactory.clearResourceMemoryCache();
        }
        super.onDestroy();
    }

    private void initMap() {
        AndroidGraphicFactory.createInstance(getApplication());
        mapView = new MapView(this);
        LatLong latLong = new LatLong(35.7, 51.416667);

        mapView.setClickable(true);
        mapView.getMapScaleBar().setVisible(false);
        mapView.setBuiltInZoomControls(true);
        mapView.setZoomLevelMin((byte) 3);
        mapView.setZoomLevelMax((byte) 10);
        mapView.setCenter(latLong);
        mapView.setZoomLevel((byte) 3);
        mapView.getModel().displayModel.setBackgroundColor(ContextCompat.getColor(this, R.color.mapBackground));

        layers = mapView.getLayerManager().getLayers();

        MapCreator mapCreator = new MapCreator(this, layers);
        mapCreator.parseGeoJson("world_map.geo.json");

        initServerOnMap(layers);

        LinearLayout map = (LinearLayout) findViewById(R.id.map);
        map.addView(mapView);

        for (int i = 0; i < countryList.size(); i++) {
            countryListSearch.add(countryList.get(i).getCountry());
        }

        ArrayAdapter<String> adapterCountry =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countryListSearch);
        country_autoComplete.setAdapter(adapterCountry);

        country_autoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                hideKeyboard(MapActivity.this);
                for (int i = 0; i < countryList.size(); i++) {
                    if (arg0.getItemAtPosition(position).equals(countryList.get(i).getCountry())) {
                        onSelectCountry(countryList.get(i));
                        break;
                    }
                }
            }
        });

        relHeader.setVisibility(View.VISIBLE);
    }

    @SuppressLint("SetTextI18n")
    private void onSelectCountry(Statistics server) {

        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.pop_up_detail_corona, null);

        popupWindow = new PopupWindow(
                view,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        ((Button) view.findViewById(R.id.successPopUpBtnClose)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                sendTouchButton("successPopUpBtnClose");
                popupWindow.dismiss();
            }
        });
        ImageView imgFlag = view.findViewById(R.id.imgFlag);

        Type listType = new TypeToken<ArrayList<Country>>() {
        }.getType();
        countryLatLonList = new Gson().fromJson(LoadData.fromFile(COUNTRY_FILE_NAME, this), listType);
        for (Statistics statistics : countryList) {
            for (Country country : countryLatLonList) {
                if (server.getCountry().equals(country.getCountryName())) {
                    String oo = country.getCountryCode().toLowerCase();
                    int identifier = getBaseContext().getResources().getIdentifier(oo, "drawable", "com.md.coronatesting");
                    imgFlag.setImageResource(identifier);
                }
            }
        }

        ((TextView) view.findViewById(R.id.txtCountry)).setText("  " + server.getCountry());
        ((TextView) view.findViewById(R.id.txtCases)).setText("  " + server.getCases() + " نفر ");
        ((TextView) view.findViewById(R.id.txtRecover)).setText("  " + server.getRecovered() + " نفر ");
        ((TextView) view.findViewById(R.id.txtDeaths)).setText("  " + server.getDeaths() + " نفر ");
        ((TextView) view.findViewById(R.id.txtActive)).setText("  " + server.getActive() + " نفر ");
        ((TextView) view.findViewById(R.id.txtCritical)).setText("  " + server.getCritical() + " نفر ");
        ((TextView) view.findViewById(R.id.txtCasesOneMillion)).setText("  " + server.getCasesPerOneMillion() + " نفر ");
        ((TextView) view.findViewById(R.id.txtDeathsOneMillion)).setText("  " + server.getDeathsPerOneMillion() + " نفر ");
        ((TextView) view.findViewById(R.id.txtCasesToday)).setText("  " + server.getTodayCases() + " نفر ");
        ((TextView) view.findViewById(R.id.txtDeathsToday)).setText("  " + server.getTodayDeaths() + " نفر ");
        popupWindow.showAtLocation(parentLayout, Gravity.CENTER, 0, 0);

    }

    private void initServerOnMap(Layers layers) {
        Type listType = new TypeToken<ArrayList<Country>>() {
        }.getType();
        countryLatLonList = new Gson().fromJson(LoadData.fromFile(COUNTRY_FILE_NAME, this), listType);

        for (Statistics server : countryList) {
            for (Country country : countryLatLonList) {
                if (server.getCountry().equals(country.getCountryName())) {
                    LatLong position = new LatLong(country.getCapitalLatitude(), country.getCapitalLongitude());
                    Bitmap bitmap = AndroidGraphicFactory.convertToBitmap(Objects.requireNonNull(ContextCompat.getDrawable(this,
                            getResources().getIdentifier(ConnectionQuality.getPointIcon(server.getCases()),
                                    "drawable",
                                    getPackageName()))));

                    MyMarker countryMarker = new MyMarker(position, bitmap, 0, 0, server) {
                        @Override
                        public boolean onTap(LatLong geoPoint, Point viewPosition,
                                             Point tapPoint) {

                            if (contains(viewPosition, tapPoint)) {
                                onSelectCountry((Statistics) getRelationObject());

                                return true;
                            }
                            return false;
                        }
                    };

                    layers.add(countryMarker);


                    String localeCountryName = country.getCountryName();

                    Drawable drawable = new BitmapDrawable(getResources(), BitmapGenerator.getTextAsBitmap(localeCountryName, 20, ContextCompat.getColor(this, R.color.mapNameCountry)));
                    Bitmap bitmapName = AndroidGraphicFactory.convertToBitmap(drawable);

                    // Marker countryNameMarker = new Marker(position, bitmapName, 0, bitmap.getHeight() / 4);

                    MyMarker countryNameMarker = new MyMarker(position, bitmapName, 0, 0, server) {
                        @Override
                        public boolean onTap(LatLong geoPoint, Point viewPosition,
                                             Point tapPoint) {

                            if (contains(viewPosition, tapPoint)) {
                                onSelectCountry((Statistics) getRelationObject());

                                return true;
                            }
                            return false;
                        }
                    };
                    layers.add(countryNameMarker);
                }
            }
        }
    }

    private void getStatistics() {

        ApiServiceInterface.Factory.create().getStatistics()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Statistics>>() {

                    @Override
                    public void onError(Throwable e) {
                        relProgress.setVisibility(View.GONE);
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Statistics> statistics) {
                        for (int i = 0; i < statistics.size(); i++)
                            dbHelper.putLine(statistics.get(i));
                        countryList = dbHelper.getUniqueCountries();

                        relProgress.setVisibility(View.GONE);
                        initMap();
                    }

                });
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


}
