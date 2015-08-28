package com.leaguehelper;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.leaguehelper.beans.Hero;

/**
 * Created by utouch on 15/7/23.
 */
public class ToolBarActivity extends BaseActivity {
    int progress = 0;
    boolean running;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActivitySwipeBackEnable(false);
        setContentView(R.layout.toolbar_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Title");
        toolbar.setSubtitle("Subtitle");
        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);


        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        collapsingToolbarLayout.setTitle("Design Library");






        final CircleProgressView progressview = (CircleProgressView) findViewById(R.id.progress);
        ShapeDrawable bg = new ShapeDrawable(new RectShape());
        int[] pixels = new int[] { 0xFF2E9121, 0xFF2E9121, 0xFF2E9121,
                          0xFF2E9121, 0xFF2E9121, 0xFF2E9121, 0xFFFFFFFF, 0xFFFFFFFF};
        Bitmap bm = Bitmap.createBitmap(pixels, 8, 1, Bitmap.Config.ARGB_8888);
        Shader shader = new BitmapShader(bm,
                          Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        progressview.setRimShader(shader);

        final Runnable r = new Runnable() {
            public void run() {
                running = true;
                while(progress<361) {
                    progressview.incrementProgress();
                    progress++;
                    try {
                        Thread.sleep(15);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                running = false;
            }
        };

        progress = 0;
        progressview.resetCount();
        Thread s = new Thread(r);
        s.start();

        Hero hero = new Hero(null,"aaa","bbb","ccc");

        AppContext.newInstance().getDaoSession().getHeroDao().insert(hero);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                Toast.makeText(this, "action_edit selected", Toast.LENGTH_SHORT)
                                  .show();
                break;
            case R.id.action_share:

                break;
            case R.id.action_settings:

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
