package com.example.xutilsdownload;

import android.Manifest;
import android.Manifest.permission;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.charset.MalformedInputException;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

  @ViewInject(R.id.button)
  Button button;
  @ViewInject(R.id.imageView)
  ImageView imageView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    x.Ext.init(getApplication());
    x.view().inject(this);

  }

  @Event(value = R.id.button)
  private void myClickListener(View view) throws Exception {
//    File file = new File(Environment.getExternalStorageDirectory() + "/test.txt");
//    FileOutputStream os = new FileOutputStream(file);
//    os.write("sdasdad".getBytes());
//    os.flush();
//    os.close();
    Toast.makeText(this, "执行联网", Toast.LENGTH_SHORT).show();
    RequestParams params = new RequestParams(
        "http://08.imgmini.eastday.com//mobile//20170925//20170925150134_77ae353e7f15f1fecc294dec794238b3_3_mwpm_03200403.jpg");
    params.setSaveFilePath(Environment.getExternalStorageDirectory()+"/myapp.jpeg");
    params.setAutoRename(true);
    Callback.Cancelable cancelable = x.http().get(params, new Callback.ProgressCallback<File>() {
      @Override
      public void onSuccess(File result) {
        Log.d("data", "成功");
        imageView.setImageBitmap(BitmapFactory.decodeFile(result.getAbsolutePath()));
      }

      @Override
      public void onError(Throwable ex, boolean isOnCallback) {
        Log.d("data", "错误日志：" + ex.toString());
        Toast.makeText(MainActivity.this, "出错了", Toast.LENGTH_SHORT).show();
      }

      @Override
      public void onCancelled(CancelledException cex) {

      }

      @Override
      public void onFinished() {
        Log.d("data", "联网结束");
      }

      @Override
      public void onWaiting() {
        Log.d("data", "等待联网");
      }

      @Override
      public void onStarted() {
        Log.d("data", "联网开始");
      }

      @Override
      public void onLoading(long total, long current, boolean isDownloading) {
        Log.d("data", "正在下载");
      }
    });
  }

  @Event(value = R.id.imageView)
  private void checkPermission(View view) {
    setPermissions();
  }

  static final String[] PERMISSION = new String[]{
      Manifest.permission.WRITE_EXTERNAL_STORAGE,// 写入权限
      Manifest.permission.READ_EXTERNAL_STORAGE,  //读取权限
      Manifest.permission.INTERNET       //读取设备信息
  };

  //6.0以后要在代码中动态添加权限
  private void setPermissions() {
    if (ContextCompat.checkSelfPermission(MainActivity.this,
        PERMISSION[0]) != PackageManager.PERMISSION_GRANTED) {
      Log.d("data", "权限申请");
      Toast.makeText(this, "申请权限", Toast.LENGTH_SHORT).show();
      //Android 6.0申请权限
      ActivityCompat.requestPermissions(this, PERMISSION, 1);
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, String[] permissions,
      int[] grantResults) {
    if (requestCode == 1) {
      if (ContextCompat.checkSelfPermission(MainActivity.this,
          PERMISSION[0]) == PackageManager.PERMISSION_GRANTED) {
        Toast.makeText(this, "写入申请成功", Toast.LENGTH_SHORT).show();
      } else {
        Toast.makeText(this, "写入申请失败", Toast.LENGTH_SHORT).show();
      }
      if (ContextCompat.checkSelfPermission(MainActivity.this,
          PERMISSION[1]) == PackageManager.PERMISSION_GRANTED) {
        Toast.makeText(this, "读取申请成功", Toast.LENGTH_SHORT).show();
      } else {
        Toast.makeText(this, "读取申请失败", Toast.LENGTH_SHORT).show();
      }
      if (ContextCompat.checkSelfPermission(MainActivity.this,
          PERMISSION[2]) == PackageManager.PERMISSION_GRANTED) {
        Toast.makeText(this, "联网申请成功", Toast.LENGTH_SHORT).show();
      } else {
        Toast.makeText(this, "联网申请失败", Toast.LENGTH_SHORT).show();
      }
    }
  }

  @Override
  protected void onResume() {
    super.onResume();
//    setPermissions();

  }
}
