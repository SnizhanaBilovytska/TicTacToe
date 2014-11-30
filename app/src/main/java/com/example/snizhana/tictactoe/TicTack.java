package com.example.snizhana.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class TicTack extends Activity implements View.OnClickListener {
    Button buttonRed;
    Button buttonBlue;
    public boolean redColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tack);
        buttonRed = (Button)findViewById(R.id.redBut);
        buttonRed.setOnClickListener(this);
        buttonBlue = (Button)findViewById(R.id.blueBut);
        buttonBlue.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Выйти:
                android.os.Process.killProcess(android.os.Process.myPid());
                return true;
            case R.id.НоваяИгра:
                onRestart();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(TicTack.this, Game.class);
        if(view.getId() == R.id.redBut){
           redColor = true;
        }
        if(view.getId() == R.id.blueBut){
            redColor = false;
        }
        startActivity(intent);
    }

}
