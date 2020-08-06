package com.charlye934.loginddagger.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.charlye934.loginddagger.R;
import com.charlye934.loginddagger.http.TwitchAPI;
import com.charlye934.loginddagger.http.twitch.Game;
import com.charlye934.loginddagger.http.twitch.Twitch;
import com.charlye934.loginddagger.root.App;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements  LoginActivityMVP.View{

    @Inject
    LoginActivityMVP.Presenter presenter;

    @Inject
    TwitchAPI twitchAPI;

    EditText firstName, lastName;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((App) getApplication()).getComponent().inject(this);

        firstName = findViewById(R.id.etFirstName);
        lastName = findViewById(R.id.etLastName);
        loginButton = findViewById(R.id.btnLogin);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.loginButtonClicked();
            }
        });

        //EJEMPLO USO TWITCH CON RETROFIT
        Call<Twitch> call = twitchAPI.getTopGames("lejipm1mqporigkqljiipi5zuqby2m");

        call.enqueue(new Callback<Twitch>() {
            @Override
            public void onResponse(Call<Twitch> call, Response<Twitch> response) {
                List<Game> topGames = response.body().getGame();
                for(Game game : topGames){
                    System.out.println(game.getName());
                }
            }

            @Override
            public void onFailure(Call<Twitch> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.getCurrentUser();
    }

    @Override
    public String getFirstName() {
        return this.firstName.getText().toString();
    }

    @Override
    public String getLastName() {
        return this.lastName.getText().toString();
    }

    @Override
    public void showUserNotAvaible() {
        Toast.makeText(this,"Error el usuario no esta disponible",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showInputError() {
        Toast.makeText(this,"Error el nombre y apellido pueden estar vacio",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUserSaved() {
        Toast.makeText(this,"Usuario guardado correctamente",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName.setText(firstName);
    }

    @Override
    public void setLasNmae(String lastName) {
        this.lastName.setText(lastName);
    }
}