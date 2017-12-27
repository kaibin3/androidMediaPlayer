package com.example.wenjie.mediaplayerdm;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

public class Mp4Activity extends Activity {


    MediaPlayFragment mediaPlayFragment = new MediaPlayFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("PlayActivity.onCreate ", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media_play_activity);



        addFragment(mediaPlayFragment);
    }


    void addFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.play_fragment,fragment);
        fragmentTransaction.commit();
    }




}
