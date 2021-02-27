package com.example.eostoy;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton intentBtn, galleryBtn;

    private FragmentManager fm;

    private final int FRAGMENT_INTENT = 0; // IntentFragment index
    private final int FRAGMENT_GALLERY = 1; // GalleryFragment index

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // FragmentManager 객체 초기화
        fm = getSupportFragmentManager();

        /** initialize view **/
        intentBtn = (ImageButton)findViewById(R.id.tab_intent_btn);
        galleryBtn = (ImageButton)findViewById(R.id.tab_gallery_btn);

        /** set OnClickListener (버튼들이 클릭을 입력 받는 것을 인지할 수 있도록 Listener 셋팅) **/
        intentBtn.setOnClickListener(this);
        galleryBtn.setOnClickListener(this);

        // 초기 fragment 를 셋팅해준다. 현재 앱을 켜면 나와야 하는 화면이 IntentFragment라 이렇게 셋팅
        replaceFragment(FRAGMENT_INTENT);


        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(MainActivity.this, "권한 허가", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                Toast.makeText(MainActivity.this, "권한 거부\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }

        };

        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setRationaleMessage("앱 사용을 위해 권한이 필요합니다. ")
                .setDeniedMessage("왜 거부하셨어요...\n하지만 [설정] > [권한] 에서 권한을 허용할 수 있어요.")
                .setPermissions(new String[]{Manifest.permission.CALL_PHONE,
                        Manifest.permission.INTERNET})
                .check();



    }

    private void replaceFragment(int position) {
        FragmentTransaction ft = fm.beginTransaction();

        if (position == FRAGMENT_INTENT) {
            /** 각 버튼의 Selected state 를 업데이트 **/
            intentBtn.setSelected(true);
            galleryBtn.setSelected(false);
            // Fragment switching
            ft.replace(R.id.tab_fragment, new IntentFragment());
        } else {
            /** 각 버튼의 Selected state 를 업데이트 **/
            intentBtn.setSelected(false);
            galleryBtn.setSelected(true);
            // Fragment switching
            ft.replace(R.id.tab_fragment, new GalleryFragment());
        }

        // 변경사항 적용
        ft.commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tab_intent_btn:
                // intent button 이 눌리면 IntentFragment 로
                replaceFragment(FRAGMENT_INTENT);
                break;
            case R.id.tab_gallery_btn:
                // gallery button 이 눌리면 GalleryFragment 로
                replaceFragment(FRAGMENT_GALLERY);
                break;
        }
    }
}