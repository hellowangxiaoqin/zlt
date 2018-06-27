package android.com.zlt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import static android.widget.Toast.makeText;

public class DetailPlaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_place);
        getSupportActionBar().hide();
        TextView name=findViewById(R.id.pname);
        TextView detail=findViewById(R.id.pdetail);
        ImageView imageView=findViewById(R.id.pimage);
        Intent intent=getIntent();
        Place place= (Place) intent.getSerializableExtra("place");
        name.setText(place.getName());
        detail.setText("\u3000\u3000"+place.getDetail());
        Glide.with(this)
                .load(Constant.BASE_URL+place.getImage())
                .into(imageView);
//        imageView.setImageResource(place.getImage());
        TextView cll=findViewById(R.id.collec_id1);
        cll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast= makeText(DetailPlaceActivity.this, "添加成功", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

            }
        });

    }
}
