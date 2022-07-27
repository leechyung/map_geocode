package com.example.maptest;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.OverlayImage;
import com.naver.maps.map.overlay.PathOverlay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity
        implements OnMapReadyCallback {

    int userSeq = 1;
    String packageName = "아";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Intent intent = getIntent();
//        intent.getIntExtra("userSeq");
//        String pack = intent.getStringExtra("packageName");

        FragmentManager fm = getSupportFragmentManager();
        MapFragment mapFragment = (MapFragment)fm.findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.map, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);
    }

    String APIKEY_ID = "hwdocwymq1";
    String APIKEY = "JtRWnSdOgAMQJKHsPpNsEzGleRQdfuWyNJosdQqa";

    //마포구 cj물류센터 좌표(고정?)
    Double start_longitude =126.84496853255905;
    Double start_latitude = 37.568215489066034;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://naveropenapi.apigw.ntruss.com/map-direction/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    Retrofit retrofit2 = new Retrofit.Builder()
            .baseUrl("http://172.30.1.3:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @UiThread
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        Request_CouryToAddress request = new Request_CouryToAddress(userSeq, packageName);

        RetrofitMap AddressApi = retrofit2.create(RetrofitMap.class);


        AddressApi.getCouryToAddress(request).enqueue(new Callback<Response_CouryToAddress>() {
            @Override
            public void onResponse(Call<Response_CouryToAddress> call, Response<Response_CouryToAddress> response) {
                Response_CouryToAddress result = response.body();
                Log.i("i", String.valueOf(response.body()));
                Log.i("i", String.valueOf(result.getRecvAddress()));

                String recvAddress = "response : " + result.getRecvAddress();

                //구글api 사용안하고 안드로이드 자체 geocode툴 사용(이게 결국 구글api)
                Geocoder geocoder = new Geocoder(getApplicationContext());
                List<Address> list = new ArrayList<Address>();

                try {
                    list = geocoder.getFromLocationName(recvAddress, 10);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //도착지 위도경도 구하기
                Double goal_latitude = list.get(0).getLatitude();
                Double goal_longitude = list.get(0).getLongitude();

                //driving 데이터 받아오기
                RetrofitMap api = retrofit.create(RetrofitMap.class);

                api.navigation(APIKEY_ID, APIKEY, Double.toString(start_longitude)+","+Double.toString(start_latitude), Double.toString(goal_longitude)+","+Double.toString(goal_latitude))
                    .enqueue(new Callback<ResultPath>() {
                        @Override
                        public void onResponse(Call<ResultPath> call, Response<ResultPath> response) {
                            ResultPath resultpath = response.body();
                            List<List<Double>> path_list = resultpath.getRoute().getOption().get(0).getPath();
                            Log.i("i", "result : " + String.valueOf(path_list.size()));

                            PathOverlay pathOverlay = new PathOverlay();
                            List<LatLng> cords = new ArrayList<LatLng>();

                            for(List<Double> path : path_list) {
                                double longitude = path.get(0);
                                double latitude = path.get(1);
                                cords.add(new LatLng(latitude, longitude));
                            }

                            pathOverlay.setColor(0xff029aff);
                            pathOverlay.setCoords(cords);
                            pathOverlay.setMap(naverMap);

                            //카메라 시작위치 설정 출발,도착지의 위도경도 나누기2가 가운데에 뜨게함
                            LatLng mlatlng = new LatLng((start_latitude+goal_latitude) / 2, (start_longitude + goal_longitude) / 2);
                            CameraPosition cameraPosition = new CameraPosition(mlatlng,11);
                            naverMap.setCameraPosition(cameraPosition);

                            //출발지 마커
                            Marker marker_start = new Marker();
                            marker_start.setPosition(new LatLng(start_latitude,start_longitude));
                            marker_start.setIcon(OverlayImage.fromResource(R.drawable.smallmap_start_marker));
                            marker_start.setWidth(100);
                            marker_start.setHeight(100);
                            marker_start.setMap(naverMap);

                            //도착지 마커
                            Marker marker_goal = new Marker();
                            marker_goal.setPosition(new LatLng(goal_latitude,goal_longitude));
                            marker_goal.setIcon(OverlayImage.fromResource(R.drawable.smallmap_end_marker));
                            marker_goal.setWidth(100);
                            marker_goal.setHeight(100);
                            marker_goal.setMap(naverMap);
                        }
                        @Override
                        public void onFailure(Call<ResultPath>call, Throwable t) {
                            t.printStackTrace();
                        }
                    });
            }

            @Override
            public void onFailure(Call<Response_CouryToAddress> call, Throwable t) {
                Log.i("i", "시발");
            }
        });
    }
}
