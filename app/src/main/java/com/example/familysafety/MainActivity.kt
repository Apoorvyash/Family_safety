package com.example.familysafety

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    val permissionCode=101;
    val permissions = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.CAMERA, android.Manifest.permission.READ_CONTACTS);


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         askForPermission()
        val bottomBar = findViewById<BottomNavigationView>(R.id.bottombar)
        bottomBar.setOnItemSelectedListener() {
            if(it.itemId==R.id.nav_guard){
                inflateFragment(GuardFragment.newInstance())
            }
            else if (it.itemId==R.id.nav_home) {
                inflateFragment(HomeFragment.newInstance())
            }
            else if (it.itemId==R.id.dashboard) {
                inflateFragment(MapsFragment2())
            }else if (it.itemId==R.id.nav_profile) {
                inflateFragment(ProfileFragment.newInstance())
            }

            true
        }


    }

    private fun askForPermission() {
        ActivityCompat.requestPermissions(this, permissions, permissionCode);
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == permissionCode) {
                if(allPermissionGranted()){
                    openCamera()
                }
            else{

                }
        }

    }

    private fun openCamera() {
        val intent= Intent("android.media.action.IMAGE_CAPTURE")
        startActivity(intent)
    }

    private fun allPermissionGranted() :Boolean{
        for(item in permissions){
            if(ContextCompat.checkSelfPermission(this, item)==PackageManager.PERMISSION_GRANTED){
                return false
            }
        }

    return true
    }
    private fun inflateFragment(newInstance: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, newInstance)
        transaction.commit()

    }



}