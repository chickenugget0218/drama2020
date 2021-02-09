package com.example.nadri2020;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapFragment extends Fragment
        implements OnMapReadyCallback   {
    private MapView mapView = null;





    public MapFragment()
    {
        // required
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_map, container, false);

        mapView = (MapView)layout.findViewById(R.id.map);
        mapView.getMapAsync(this);

        return layout;
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onLowMemory();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //액티비티가 처음 생성될 때 실행되는 함수

        if(mapView != null)
        {
            mapView.onCreate(savedInstanceState);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng SEOUL = new LatLng(37.57003721379956, 126.9730044203853);
        LatLng SEOUL2 = new LatLng(37.567538897057624, 126.9732811777274);
        LatLng SEOUL3 = new LatLng(37.554456669527134, 126.98141945993632);
        LatLng SEOUL4 = new LatLng(37.543969828145165, 126.97086899051055);
        LatLng SEOUL5 = new LatLng(37.522244745948214, 126.98384849722369);
        LatLng SEOUL6 = new LatLng(37.5277029366368, 126.96878248857927);
        LatLng SEOUL7 = new LatLng(37.56432693165305, 126.97384049229294);
        LatLng SEOUL8 = new LatLng(37.57621454431908, 126.98698319537509);
        LatLng SEOUL9 = new LatLng(37.5778451683546, 126.98831028909697);
        LatLng SEOUL10 = new LatLng(37.58652850520774, 127.0014842682367);


        googleMap.addMarker(new MarkerOptions().position(SEOUL2).title("드라마 [도깨비] 촬영지").snippet("서울 중구 정동"));
        googleMap.addMarker(new MarkerOptions().position(SEOUL3).title("드라마 [내 이름은 김삼순] 촬영지").snippet("서울 중구 회현동1가"));
        googleMap.addMarker(new MarkerOptions().position(SEOUL4).title("드라마 [광고천재 이태백] 촬영지").snippet("서울 용산구 청파동3가"));
        googleMap.addMarker(new MarkerOptions().position(SEOUL5).title("드라마 [로망스] 촬영지").snippet("서울 용산구 용산동6가"));
        googleMap.addMarker(new MarkerOptions().position(SEOUL6).title("드라마 [드라마의 제왕] 촬영지").snippet("서울 용산구 용산동5가"));
        googleMap.addMarker(new MarkerOptions().position(SEOUL7).title("드라마 [내 연애의 모든 것] 촬영지").snippet("서울 중구 서소문동"));
        googleMap.addMarker(new MarkerOptions().position(SEOUL8).title("드라마 [도깨비] 촬영지").snippet("서울 종로구 안국동"));
        googleMap.addMarker(new MarkerOptions().position(SEOUL9).title("드라마 [신사의 품격] 촬영지").snippet("서울 종로구 원서동"));
        googleMap.addMarker(new MarkerOptions().position(SEOUL10).title("드라마 [응답하라1988] 촬영지").snippet("서울 종로구 혜화동"));

        MarkerOptions markerOptions = new MarkerOptions();

        markerOptions.position(SEOUL);

        markerOptions.title("드라마 [직장의 신] 촬영지");
        markerOptions.snippet("서울 종로구 신문로1가");

        googleMap.addMarker(markerOptions);

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));

        googleMap.animateCamera(CameraUpdateFactory.zoomTo(13));
    }

}
