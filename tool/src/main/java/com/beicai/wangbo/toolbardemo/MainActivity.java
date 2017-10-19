package com.beicai.wangbo.toolbardemo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar= (Toolbar) findViewById(R.id.toolbar);
        //继续与ActionBar绑定. 如果不调用这个API,就是不与ActionBar绑定.(推荐把ToolBar单独抽离)
        //setSupportActionBar(toolbar);
        //显示导航按钮
        mToolbar.setNavigationIcon(R.mipmap.ic_launcher);
        //设置导航按钮监听事件
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"导航按钮",Toast.LENGTH_SHORT).show();
            }
        });
        //设置Logo
        mToolbar.setLogo(R.mipmap.tool);
        //设置Logo长按监听事件
        mToolbar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(MainActivity.this,"Logo标志",Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        //设置主标题
        mToolbar.setTitle("主标题");
        //设置主标题颜色
        mToolbar.setTitleTextColor(Color.parseColor("#ffff00"));
        //修改主标题的颜色、外观
        mToolbar.setTitleTextAppearance(this,R.style.Theme_ToolBar_Base_Title);
        //设置副标题
        mToolbar.setSubtitle("副标题");
        //设置副标题颜色
        mToolbar.setSubtitleTextColor(Color.parseColor("#ffff00"));
        //修改副标题的颜色、外观
        mToolbar.setSubtitleTextAppearance(this,R.style.Theme_ToolBar_Base_Subtitle);
        //设置菜单弹出样式的文本颜色和背景
        mToolbar.setPopupTheme(R.style.PopupMenu);
        //设置菜单弹出样式    必须放在    设置菜单    前面,否则没有效果;
        //设置菜单
        mToolbar.inflateMenu(R.menu.menu_main);
        //设置菜单及其点击监听
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id=item.getItemId();
                switch (id){
                    case R.id.search:
                        Toast.makeText(MainActivity.this,"搜索",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.notification:
                        Toast.makeText(MainActivity.this,"消息",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_item1:
                        Toast.makeText(MainActivity.this,"分享",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_item2:
                        Toast.makeText(MainActivity.this,"关于我们",Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });


    }
}
