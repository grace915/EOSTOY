package com.example.eostoy.adapter;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eostoy.MainActivity;
import com.example.eostoy.data.IntentData;
import com.example.eostoy.data.Manager;
import com.example.eostoy.R;

import java.util.ArrayList;

import static androidx.core.app.ActivityCompat.requestPermissions;

public class IntentAdapter extends RecyclerView.Adapter<IntentAdapter.ViewHolder> {
    private ArrayList<IntentData> intentList = new ArrayList<>(); // RecyclerView에 표시할 data list
    private Context context;

    /**
     * @param context
     * @param intentList : data list 를 받아와서 셋팅
     */
    public IntentAdapter(Context context, ArrayList<IntentData> intentList) {
        this.context = context;
        this.intentList = intentList;
    }

    /**
     * ViewHolder 를 생성해서 반환하는 역할
     *
     * @param parent
     * @param viewType
     * @return 생성한 ViewHolder를 반환
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 각각의 item 을 표현하는 layout (intent_item.xml) 파일을 가져와서 View 로 만들어 준다! (LayoutInflater 이용)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.intent_item, parent, false);
        // 방금 만든 view (intent_item.xml 로 만든 거 ㅇㅇ) 를 제물로 바쳐 ViewHolder를 소환한다
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    /**
     * 각각의 item view에 데이터를 셋팅함
     *
     * @param holder   셋팅할 view 들을 잡고 있는 ViewHolder 인스턴스
     * @param position 현재 그릴 item의 position (보통 현재 그릴 data 의 list index 와 같은 값을 가집니다)
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // 그려줄 IntentData 를 셋팅
        final IntentData intent = intentList.get(position);

        if (intent != null) { // Error Handling
            // image setting
            holder.image.setImageDrawable(context.getResources().getDrawable(intent.getImageId()));
            // title setting
            holder.title.setText(intent.getTitle());

            // click event 처리
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (intent.getCode()) {
                        case Manager.INTENT_DIAL:
                            /** 원하는 전화번호가 입력된 상태의 dial 화면으로 넘겨주는 Intent **/
                            Intent dial = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:01053942915"));
                            context.startActivity(dial);
                            break;
                        case Manager.INTENT_CALL:
                            String number = "01053942915";
                            Uri numberU = Uri.parse("tel:" + number);
                            Intent call = new Intent(Intent.ACTION_CALL, Uri.parse("tel:01053942915"));
                            context.startActivity(call);
                            break;


                        case Manager.INTENT_MSG:
                            /** 원하는 전화번호, 메시지가 입력된 상태의 문자 화면으로 넘겨주는 Intent **/
                            Intent msg = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:01053942915"));
                            msg.putExtra("sms_body", "예제다!"); // 문자에 내용을 추가하고 싶을 때는 반드시 "sms_body" 라는 이름으로 값을 입력해야 한다. (두개의 스트링 중 앞은 변수의 key 값. 뒤는 변수의 값)
                            context.startActivity(msg);
                            break;
                        case Manager.INTENT_EMAIL:
                            /** 원하는 수신자가 입력된 상태의 메일 앱으로 넘겨주는 Intent **/
                            try{
                                Intent email = new Intent(Intent.ACTION_SEND);
                                email.putExtra(Intent.EXTRA_SUBJECT, "Email");
                                email.putExtra(Intent.EXTRA_EMAIL, "hyu.eos@gmail.com");
                                email.setType("message/rfc822");
                                context.startActivity(email);
                            }catch (Exception e){
                                e.printStackTrace();
                            }

                            break;
                        case Manager.INTENT_INTERNET:
                            /** 원하는 주소의 인터넷 창으로 넘겨주는 Intent **/
                            Intent internet = new Intent(Intent.ACTION_VIEW, Uri.parse("https://hyu-eos.tistory.com/category"));
                            context.startActivity(internet);
                            break;
                    }
                }
            });
        }
    }

    /**
     * @return data list 의 item 갯수를 반환
     */
    @Override
    public int getItemCount() {
        return intentList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image; // 내 item view 의 ImageView를 담을 인스턴스
        TextView title;// 내 item view 의 TextView를 담을 인스턴스

        public ViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.intent_image);
            title = (TextView) itemView.findViewById(R.id.intent_title);
        }
    }
}
