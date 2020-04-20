package com.ganda.nearbyhealth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap mapa;
    List<Marker> markerList;
    Marker punto01, punto02, punto03, punto04;

    int contador = 0;
    int rojo = 102, verde = 150, azul = 170;
    CircleOptions circleOptions;
    Circle circle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        markerList = new ArrayList<>();

        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-12.0965909, -77.0250205), 15));


        generarPuntos();
        pintarPoligono();


    }

    private void generarPuntos() {

        //Clinica Internacional
        punto01 = mapa.addMarker(new MarkerOptions().position(
                new LatLng(-12.0941312, -77.024191)).draggable(true)
                .title("Clinica Internacional").snippet("https://www.clinicainternacional.com.pe")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

        //javie prado
        punto02 = mapa.addMarker(new MarkerOptions().position(new LatLng(-12.0916539, -77.0284567)).draggable(true)
                .title("Clinica Javier Prado").snippet("https://www.javierprado.com.pe")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));

        punto03 = mapa.addMarker(new MarkerOptions().position(new LatLng(-12.094334, -77.022466)).draggable(true)
                .title("Medicentro ").snippet("https://www.javierprado.com.pe")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        punto04 = mapa.addMarker(new MarkerOptions().position(new LatLng(-12.089346, -77.0300265)).draggable(true)
                .title(" Unidad de Ozonoterapia Perú ").snippet("https://www.javierprado.com.pe")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));


        markerList.add(punto01);
        markerList.add(punto02);
        markerList.add(punto03);
        markerList.add(punto04);

    }

    private void pintarPoligono() {

        for (Marker marker : markerList) {


            circleOptions = new CircleOptions().center(
                    new LatLng(marker.getPosition().latitude, marker.getPosition().longitude))
                    .radius(10).fillColor(Color.argb(67, rojo, verde, azul))
                    .strokeColor(Color.argb(200, rojo, verde, azul));
            circle = mapa.addCircle(circleOptions);
            circle.setStrokeWidth(18);

            rojo *=2;
            verde++;
            azul*=6;
        }
    }


    public void centrar(View v) {
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(-12.0919477, -77.0267864), 16));
    }

    public void zoom(View v) {
        //for (int i = 0; i < markerList.size(); i++) {  }

        if (contador < 4) {
            mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(markerList.get(contador).getPosition().latitude,
                            markerList.get(contador).getPosition().longitude), 19));
            contador++;
        } else {
            contador = 0;
        }


    }

    public void verAll(View v) {
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(-12.0919477, -77.0267864), 14));
    }
}
