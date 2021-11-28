package com.test.tyroassignment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.test.tyroassignment.API.ApiClient;
import com.test.tyroassignment.API.ApiInterface;
import com.test.tyroassignment.API.Data;
import com.test.tyroassignment.API.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import hiennguyen.me.circleseekbar.CircleSeekBar;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    ImageButton navIcon, filterButton, filterCheck;
    TextInputLayout duration, trainer, equipment, difficulty;
    CircleSeekBar circleSeekBar;

    DrawerLayout layout;

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    ApiInterface apiInterface;

    List<Data> datalist = new ArrayList<Data>();
    ArrayList<String> trainerArray = new ArrayList<>();
    ArrayList<Model> trainerList = new ArrayList<>();
    CustomAdapter trainerAdapter, difficultyAdapter, equipAdapter;
    SliderAdapter adapter;

    float DURATION = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        navIcon = findViewById(R.id.navIcon);
        filterButton = findViewById(R.id.filterIcon);
        filterCheck = findViewById(R.id.filterCheck);
        recyclerView = findViewById(R.id.recyclerView);
        layout = findViewById(R.id.activity_main_layout);
        duration = findViewById(R.id.duration_dropdown);
        trainer = findViewById(R.id.trainer_dropdown);
        difficulty = findViewById(R.id.difficulty_dropdown);
        equipment = findViewById(R.id.equipment_dropdown);
        circleSeekBar = findViewById(R.id.slider);


        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setHasFixedSize(true);
        recyclerAdapter = new RecyclerAdapter(this, datalist);
        recyclerView.setAdapter(recyclerAdapter);
        doStuff();


        adapter = new SliderAdapter(MainActivity.this, 0, new String[]{""}, DURATION);
        adapter.setDropDownViewResource(R.layout.duration_layout);
        ((AutoCompleteTextView) (Objects.requireNonNull(duration.getEditText()))).setDropDownBackgroundResource(R.drawable.shape);
        ((AutoCompleteTextView) (Objects.requireNonNull(duration.getEditText()))).setAdapter(adapter);

        final String[] equipArray = new String[]{"Yes"};
        ArrayList<Model> equipList = new ArrayList<>();
        for (String value : equipArray) {
            Model model = new Model();
            model.setTitle(value);
            model.setSelected(false);
            equipList.add(model);
        }
        equipAdapter = new CustomAdapter(MainActivity.this, 0, equipList, false);
        ((AutoCompleteTextView) (Objects.requireNonNull(equipment.getEditText()))).setDropDownBackgroundResource(R.drawable.shape);
        ((AutoCompleteTextView) (Objects.requireNonNull(equipment.getEditText()))).setAdapter(equipAdapter);


        String[] difficultyArray = new String[]{"Beginner", "Intermediate", "Advanced"};
        ArrayList<Model> difficultyList = new ArrayList<>();
        for (String s : difficultyArray) {
            Model model = new Model();
            model.setTitle(s);
            model.setSelected(false);
            difficultyList.add(model);
        }
        difficultyAdapter = new CustomAdapter(MainActivity.this, 0, difficultyList, false);
        ((AutoCompleteTextView) (Objects.requireNonNull(difficulty.getEditText()))).setDropDownBackgroundResource(R.drawable.shape);
        ((AutoCompleteTextView) (Objects.requireNonNull(difficulty.getEditText()))).setAdapter(difficultyAdapter);


        navIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout.openDrawer(GravityCompat.END);
            }
        });
        filterCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filterData();
                layout.closeDrawer(GravityCompat.END);
            }
        });

    }

    public <T> ArrayList<T> removeDuplicates(ArrayList<T> list) {
        ArrayList<T> newList = new ArrayList<T>();
        for (T element : list) {
            if (!newList.contains(element)) {
                newList.add(element);
            }
        }
        return newList;
    }

    public void filterData() {
        DURATION = adapter.getDURATION();
        ArrayList<Data> filteredItems = new ArrayList<Data>();
        List<Model> equip = new ArrayList<Model>(equipAdapter.getList());
        List<Model> diff = new ArrayList<Model>(difficultyAdapter.getList());
        List<Model> trainer = new ArrayList<Model>(trainerAdapter.getList());

        for (Data item : datalist) {
            boolean equipMatched = true;
            if (equip.size() > 0 && item.getEquipments().isEmpty()) {
                equipMatched = false;
            }
            List<Data> list = new ArrayList<>();
            List<Data> list2 = new ArrayList<>();
            boolean diffMatched = true;
            if (diff.size() > 0) {
                for (int i = 0; i < diff.size(); i++) {
                    list.add(item);
                    diffMatched = true;

                    if (!diff.get(i).getTitle().equals(item.getDifficultyLevelName())) {
                        list.remove(item);
                    }
                }
                if (list.isEmpty()) {
                    diffMatched = false;
                }
            }
            boolean trainerMatched = true;
            if (trainer.size() > 0) {
                for (int i = 0; i < trainer.size(); i++) {

                    list2.add(item);
                    trainerMatched = true;

                    if (!trainer.get(i).getTitle().equals(item.getTrainerName())) {
                        list2.remove(item);
                    }
                }
                if (list2.isEmpty()) {
                    diffMatched = false;
                }
            }
            boolean durationMatched = true;
            if ((float) item.getDuration() / 100 >= DURATION) {
                durationMatched = false;
            }
            if (equipMatched && diffMatched && trainerMatched && durationMatched) {
                filteredItems.add(item);
                filteredItems.addAll(list);
            }
        }
        filteredItems = removeDuplicates(filteredItems);
        recyclerAdapter.filterData(filteredItems);
        recyclerView.setAdapter(recyclerAdapter);
    }

    public void doStuff() {
        Call<Response> call = apiInterface.getData(0);

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    if (Objects.requireNonNull(response.body()).getSuccess()) {
                        datalist = response.body().getData();
                        recyclerAdapter = new RecyclerAdapter(MainActivity.this, datalist);
                        recyclerView.setAdapter(recyclerAdapter);
                        recyclerAdapter.notifyDataSetChanged();

                        for (int i = 0; i < datalist.size(); i++) {
                            trainerArray.add(datalist.get(i).getTrainerName());
                        }
                        trainerArray = removeDuplicates(trainerArray);
                        for (int i = 0; i < trainerArray.size(); i++) {
                            Model model = new Model();
                            model.setTitle(trainerArray.get(i));
                            model.setSelected(false);
                            trainerList.add(model);
                        }
                        trainerAdapter = new CustomAdapter(MainActivity.this, 0, trainerList, true);
                        ((AutoCompleteTextView) (Objects.requireNonNull(trainer.getEditText()))).setDropDownBackgroundResource(R.drawable.shape);
                        ((AutoCompleteTextView) (Objects.requireNonNull(trainer.getEditText()))).setAdapter(trainerAdapter);

                    } else {
                        Snackbar.make(layout, "Please try again later!", BaseTransientBottomBar.LENGTH_SHORT).show();
                    }
                } else {
                    Snackbar.make(layout, "Error! Could not connect to the server.", BaseTransientBottomBar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                progressDialog.dismiss();
                Snackbar.make(layout, "Error! Connection failed. " + t.getMessage(), BaseTransientBottomBar.LENGTH_INDEFINITE).show();
            }
        });
    }
}