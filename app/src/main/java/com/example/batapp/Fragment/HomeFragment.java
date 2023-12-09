package com.example.batapp.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.batapp.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.intentfilter.androidpermissions.PermissionManager;
import com.intentfilter.androidpermissions.models.DeniedPermission;
import com.intentfilter.androidpermissions.models.DeniedPermissions;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singleton;


public class HomeFragment extends Fragment implements OnMapReadyCallback {

    //variables
    private SupportMapFragment mapFragment;
    private EditText search;
    private Button find;
    private GoogleMap Gmaps;


    //functions

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        this.search = v.findViewById(R.id.search);
        this.find = v.findViewById(R.id.find);
        return v;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);

        getPermission();
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchLocation(search);
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(getActivity(), "here maps", Toast.LENGTH_SHORT).show();
        Gmaps = googleMap;
        Gmaps.clear();
        LatLng marquerpos = new LatLng(48.864716, 2.349014);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(marquerpos);
        markerOptions.snippet("Paris France");
        markerOptions.title("Paris");
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marquer));

        Gmaps.addMarker(markerOptions);

        moveCameraPosition(48.864716, 2.349014);

    }

    //get permission to access
    private void getPermission() {

        PermissionManager permissionManager = PermissionManager.getInstance(getActivity());
        permissionManager.checkPermissions(singleton(Manifest.permission.ACCESS_FINE_LOCATION), new PermissionManager.PermissionRequestListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(getActivity(), "Permissions Granted", Toast.LENGTH_SHORT).show();
                if (isGpsEnabled()) {
                    getDeviceLocation();
                } else {
                    GpsDialog();
                }
            }

            @Override
            public void onPermissionDenied(DeniedPermissions deniedPermissions) {
                String deniedPermissionsText = "Denied: " + Arrays.toString(deniedPermissions.toArray());
                Toast.makeText(getActivity(), deniedPermissionsText, Toast.LENGTH_SHORT).show();

                for (DeniedPermission deniedPermission : deniedPermissions) {
                    if (deniedPermission.shouldShowRationale()) {
                        // Display a rationale about why this permission is required
                    }
                }
            }
        });

    }

    // check if GPS on

    public boolean isGpsEnabled() {
        LocationManager manager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    private void GpsDialog() {
        AlertDialog.Builder alert_gps = new AlertDialog.Builder(getActivity());
        alert_gps.setMessage("You Need To Activate GPS").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent callGPSSettingIntent = new Intent(
                                android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(callGPSSettingIntent);
                    }
                });
        alert_gps.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alert_gps.create();
        alert_gps.setTitle("GPS Activation");
        alert.show();

    }

    private void getDeviceLocation() {

        FusedLocationProviderClient mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());

//        try {
//            if (mLocationPermissionGranted) {
        Task locationResult = mFusedLocationProviderClient.getLastLocation();
        locationResult.addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()) {
                    Location currentLocation = (Location) task.getResult();
                    if (currentLocation != null) {
                        moveCameraPosition(currentLocation.getLatitude(), currentLocation.getLongitude());

                        Gmaps.setMyLocationEnabled(true);
                    }

                } else {
                    Toast.makeText(getActivity(), "Couldn't get Location", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void moveCameraPosition(double lat, double lng) {

        LatLng currentPos = new LatLng(lat, lng);
        Gmaps.moveCamera(CameraUpdateFactory.newLatLngZoom(currentPos, 16f));
    }

    public void searchLocation(View view) {
        EditText locationSearch = this.search;
        String location = locationSearch.getText().toString();
        List<Address> addressList = null;

        if (location != null || !location.equals("")) {
            Geocoder geocoder = new Geocoder(getActivity());
            try {
                addressList = geocoder.getFromLocationName(location, 1);

            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            Gmaps.addMarker(new MarkerOptions().position(latLng).title(location));
            Gmaps.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            Toast.makeText(getActivity(), address.getLatitude() + " " + address.getLongitude(), Toast.LENGTH_LONG).show();
        }
    }

}