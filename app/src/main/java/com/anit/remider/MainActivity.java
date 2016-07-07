package com.anit.remider;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.anit.remider.adapter.TabAdapter;
import com.anit.remider.dialog.AddingTaskDialogFragment;
import com.anit.remider.fragment.CurrentTaskFragment;
import com.anit.remider.fragment.DoneTaskFragment;
import com.anit.remider.fragment.SplashFragment;
import com.anit.remider.model.ModelTask;

public class MainActivity extends AppCompatActivity implements AddingTaskDialogFragment.AddinTaskListener {

    FragmentManager fragmentManager;
    PreferenceHelper preferenceHelper;


    TabAdapter tabAdapter;

    private CurrentTaskFragment currentTaskFragment;
    private DoneTaskFragment doneTaskFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        PreferenceHelper.getInstance().init(getApplicationContext());
        preferenceHelper = PreferenceHelper.getInstance();
        fragmentManager = getFragmentManager();

        runSplash();

        setUI();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem splash_item = menu.findItem(R.id.action_splash);
        splash_item.setChecked(preferenceHelper.getBoolean(PreferenceHelper.SPLASH_IS_INVISIBLE));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_splash) {
            item.setChecked(!item.isChecked());
            preferenceHelper.putBoolean(PreferenceHelper.SPLASH_IS_INVISIBLE, item.isChecked());

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    void setUI(){

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if(toolbar != null){
            toolbar.setTitleTextColor(getResources().getColor(R.color.wite));
            setSupportActionBar(toolbar);
        }


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.current_task));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.done_task));

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        tabAdapter = new TabAdapter(fragmentManager,2);
        viewPager.setAdapter(tabAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }


        });



        currentTaskFragment = (CurrentTaskFragment) tabAdapter.getItem(TabAdapter.CURRENT_TASK_POSITION);
        doneTaskFragment    = (DoneTaskFragment) tabAdapter.getItem(TabAdapter.DONE_TASK_POSITION);
    }


    /**
     * Вешаем на Fab
     * @param view
     */
    public void onClickFab(View view) {
        DialogFragment addingTaskDialogFragment = new  AddingTaskDialogFragment();
        addingTaskDialogFragment.show(fragmentManager,"AddingTaskDialogFragment");
    }

    void runSplash() {

        if (!preferenceHelper.getBoolean(PreferenceHelper.SPLASH_IS_INVISIBLE)) {

            SplashFragment splashFragment = new SplashFragment();
           fragmentManager.beginTransaction()
                   .replace(R.id.content_frame, splashFragment)
                   .addToBackStack(null)
                   .commit();
        }




    }

    @Override
    public void onTaskAdded(ModelTask newTask) {
        currentTaskFragment.addTask(newTask);

    }

    @Override
    public void onTaskAddingCencel() {
        Toast.makeText(this,"Task added!",Toast.LENGTH_SHORT).show();
    }
}
