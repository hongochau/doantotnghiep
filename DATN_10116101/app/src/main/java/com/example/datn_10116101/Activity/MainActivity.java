package com.example.datn_10116101.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import com.example.datn_10116101.Adapter.ProductAdapter;
import com.example.datn_10116101.Adapter.ProductTypeAdapter;
import com.example.datn_10116101.BaseResponse.ProductBaseResponse;
import com.example.datn_10116101.Fragment.CaNhan.CaNhanActivity;
import com.example.datn_10116101.ItemClickSupport;
import com.example.datn_10116101.MenuToobar.TimKiemActivity;
import com.example.datn_10116101.model.Quanlysanphamdaxem;
import com.example.datn_10116101.model.product_types;
import com.example.datn_10116101.model.products;
import com.example.datn_10116101.model.user1s;
import com.example.datn_10116101.R;
import com.example.datn_10116101.ViewModel.BannerViewModel;
import com.example.datn_10116101.ViewModel.ProductTypeViewModel;
import com.example.datn_10116101.config.Database;
import com.example.datn_10116101.config.Progressdialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.messaging.FirebaseMessaging;
import com.squareup.picasso.Picasso;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.ArrayList;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;




public class MainActivity extends BaseActivity {
    DrawerLayout mdrawerLayout;
    NavigationView navigationView;
    ViewFlipper viewflipper ;
    ImageView imgMenu  , imageview_giohang;
    SearchView imageview_timkiem;
    Button button_loadmore ;
    TextView textview_tencuahang;
    BannerViewModel bannerViewModel; // khai báo viewmodel
    ProductTypeViewModel productTypeViewModel;
    CircleImageView profile_image;
    TextView textview_name;
    View headerview;
    ArrayList<product_types> listproducttype = new ArrayList<>();
    ArrayList<products> listproducmoinhat = new ArrayList<>();
    RecyclerView recyclerView2;
    ProductTypeAdapter productTypeAdapter;
    ProductAdapter productAdapter;
    LinearLayoutManager layoutManager;
    StaggeredGridLayoutManager staggeredGridLayoutManager;
    Progressdialog progressdialog;
    RecyclerView recyclerview_sanphammoinhat;
    public SQLiteDatabase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = Database.initDatabase(this, "huhu.db");
        Anhxa();

        ActionViewFlipper();
        progressdialog=new Progressdialog();
       
        RegNotifi("hongochau");
        bannerViewModel = ViewModelProviders.of(this).get(BannerViewModel.class); // khởi tạo viewmodel
        productTypeViewModel = ViewModelProviders.of(this).get(ProductTypeViewModel.class); // phải có
        progressdialog.showDialogTitle("Thông báo ","Đang load sản phẩm, vui lòng đợi trong giây lát",MainActivity.this);
        productTypeViewModel.getProducttype().observe(MainActivity.this, new Observer<List<product_types>>() {
            @Override
            public void onChanged(List<product_types> producttype) {
                // populate data
                recyclerView2.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                recyclerView2.setLayoutManager(layoutManager);
                listproducttype.addAll(producttype);
                productTypeAdapter = new ProductTypeAdapter(listproducttype, MainActivity.this);
                recyclerView2.setAdapter(productTypeAdapter);
                progressdialog.dismissDialog();
            }
        });


        // lấy sản phẩm mới nhất
        productTypeViewModel.getnewpro().observe(this, new Observer<ProductBaseResponse>() {
            @Override
            public void onChanged(ProductBaseResponse productBaseResponse) {
                ArrayList<products> list= (ArrayList<products>) productBaseResponse.getData();
                // populate data
                recyclerview_sanphammoinhat.setHasFixedSize(false);
                staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                recyclerview_sanphammoinhat.setLayoutManager(staggeredGridLayoutManager);
                listproducmoinhat.addAll(list);
                productAdapter = new ProductAdapter(listproducmoinhat, MainActivity.this);
                recyclerview_sanphammoinhat.setAdapter(productAdapter);
            }
        });

        ItemClickSupport.addTo(recyclerView2).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                product_types pro=listproducttype.get(position);
                EventBus.getDefault().postSticky(pro);
                startActivity(new Intent(MainActivity.this, LoadProductTypeActivity.class));
            }
        });

        ItemClickSupport.addTo(recyclerview_sanphammoinhat).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                products pro=listproducmoinhat.get(position);
                EventBus.getDefault().postSticky(pro);
                // thêm vào trong sqlite
                Quanlysanphamdaxem ql=new Quanlysanphamdaxem(pro.getId(),pro.getName(),pro.getImage(),pro.getDescribe(),pro.getIdProductType());
                Themgsanphamdaxem(ql);
                startActivity(new Intent(MainActivity.this, ProductDetailActivity.class));
            }
        });
    }

    public  void Themgsanphamdaxem (Quanlysanphamdaxem pro){

        ContentValues contentValues=new ContentValues();
//        contentValues.put("id",pro.getId());
        contentValues.put("tenproduct",pro.getTenproduct());
        contentValues.put("hinhanhproduct",pro.getHinhanhproduct());
        contentValues.put("motaproduct",pro.getMotaproduct());
        contentValues.put("idloaisanpham",pro.getIdloaisanpham());
        db.insert("quanlysanphamdaxem",null,contentValues);
    }

    private void ActionViewFlipper() {
        final ArrayList<String> manhinhquangcao = new ArrayList<>();
        manhinhquangcao.add("https://cdn.tgdd.vn/2020/04/banner/690-300-690x300-2.png");
        manhinhquangcao.add("https://cdn.tgdd.vn/2020/03/banner/Tulanh-Maylanh-690-300-640x280.png");
        manhinhquangcao.add("https://cdn.tgdd.vn/2020/04/banner/Lucky-Draw-690-300-690x300.png");
        manhinhquangcao.add("https://cdn.tgdd.vn/2020/04/banner/TV-SS-8K-690-300-690x300.png");
        manhinhquangcao.add("https://cdn.tgdd.vn/2020/03/banner/FFALCON-TV-690-300-690x300.png");
        manhinhquangcao.add("https://cdn.tgdd.vn/2020/03/banner/TVLG690-300-690x300.png");
        manhinhquangcao.add("https://cdn.tgdd.vn/2020/04/banner/ssagt-640x280.png");
        manhinhquangcao.add("https://cdn.tgdd.vn/2020/03/banner/690-300-640x280.png");
        for (int n = 0; n < manhinhquangcao.size(); n++) {
            final ImageView imageView = new ImageView(getApplicationContext());
            Picasso.get().load(manhinhquangcao.get(n)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            viewflipper.addView(imageView);
        }
        viewflipper.setFlipInterval(5000);
        viewflipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewflipper.setInAnimation(animation_slide_in);
        viewflipper.setOutAnimation(animation_slide_out);
    }
    @SuppressLint("WrongViewCast")

    private void Anhxa() {
        mdrawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);
        viewflipper = findViewById(R.id.viewflipper);
        imgMenu = findViewById(R.id.imgMenu);
        imageview_timkiem = findViewById(R.id.imageview_timkiem);
        imageview_giohang = findViewById(R.id.imageview_giohang);
        button_loadmore = findViewById(R.id.button_loadmore);
        textview_tencuahang = findViewById(R.id.textview_tencuahang);
        recyclerview_sanphammoinhat = findViewById(R.id.recyclerview_sanphammoinhat);
        recyclerView2=findViewById(R.id.recyclerview_loaisanpham);
        headerview=navigationView.getHeaderView(0);
        textview_name= headerview.findViewById(R.id.textview_name);
        profile_image= headerview.findViewById(R.id.profile_image);
        navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true);
                mdrawerLayout.closeDrawers();
                switch (menuItem.getItemId()) {
                    case R.id.nav_trangchu:
                        break;
                    case R.id.nav_canhan:
                        Intent intent = new Intent (MainActivity.this, CaNhanActivity.class);
                        startActivity(intent);
                        break;
                    /*case R.id.nav_ketnoiungdung:
                        Toast.makeText(MainActivity.this, "nav_ketnoiungdung", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_thongbao:
                        Toast.makeText(MainActivity.this, "thongbao", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_danhgiaungdung:
                        Toast.makeText(MainActivity.this, "danhgiaungdung", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_caidat:
                        Toast.makeText(MainActivity.this, "caidat", Toast.LENGTH_SHORT).show();
                        break;*/
                }

                return true;
            }
        });
        button_loadmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LoadMoreActivity.class));
            }
        });
        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdrawerLayout.openDrawer(GravityCompat.START);
            }
        });
        imageview_giohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,GioHangActivity.class));
            }
        });
        imageview_timkiem.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                EventBus.getDefault().postSticky(query);
                startActivity(new Intent(MainActivity.this,TimKiemActivity.class));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
   }
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(user1s event) { // lấy từ trong store của EVent bus  user1s là kiểu dữ liệu khai báo vs event bus để lấy ra
        textview_name.setText(""+event.getName());
        Picasso.get()
                .load(""+ event.getImagefb())
                .resize(100, 100)
                // .centerCrop()
                .into(profile_image);
    }

    private void RegNotifi(String topic) { // đăng ký nhận notifi của firebase (theo tên của user )
        FirebaseMessaging.getInstance().subscribeToTopic(topic) // ở trên firebase sẽ gửi theo topic hihi
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Chào mừng đến với HnH Electronics Store";
                        if (!task.isSuccessful()) {
                            msg = "sub phêu";
                        }
                        Toast.makeText(MainActivity.this, ""+msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
