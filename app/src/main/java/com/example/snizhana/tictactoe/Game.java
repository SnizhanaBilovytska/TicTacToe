package com.example.snizhana.tictactoe;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game extends Activity implements View.OnClickListener {
    private static boolean redColor;
    private static boolean myColorValue;
    private List<Integer> root = new ArrayList<Integer>();
    private List<Integer> my = new ArrayList<Integer>();
    private int buttons[] = new int[9];
    Random rand = new Random();
    private TicTack tictack = new TicTack();

    public Game() {
        redColor = tictack.redColor;
        myColorValue = !redColor;
        buttons[0] = R.id.button11;
        buttons[1] = R.id.button12;
        buttons[2] = R.id.button13;
        buttons[3] = R.id.button21;
        buttons[4] = R.id.button22;
        buttons[5] = R.id.button23;
        buttons[6] = R.id.button31;
        buttons[7] = R.id.button32;
        buttons[8] = R.id.button33;
    }

    @Override
    protected void onStart() {
        super.onStart();
        randWhyFirst();
    }

    private void randWhyFirst() {
        if (rand.nextInt(2) == 1) { //me
            randFirstStep();
        }
    }

    private void randFirstStep() {
        int r = rand.nextInt(9);
        Button bt;
        switch (r) {
            case 0:
                bt = ((Button) findViewById(R.id.button11));
                drawImage(bt, myColorValue);
                my.add(R.id.button11);
                break;
            case 1:
                bt = ((Button) findViewById(R.id.button12));
                drawImage(bt, myColorValue);
                my.add(R.id.button12);
                break;
            case 2:
                bt = ((Button) findViewById(R.id.button13));
                drawImage(bt, myColorValue);
                my.add(R.id.button13);
                break;
            case 3:
                bt = ((Button) findViewById(R.id.button21));
                drawImage(bt, myColorValue);
                my.add(R.id.button21);
                break;
            case 4:
                bt = ((Button) findViewById(R.id.button22));
                drawImage(bt, myColorValue);
                my.add(R.id.button22);
                break;
            case 5:
                bt = ((Button) findViewById(R.id.button23));
                drawImage(bt, myColorValue);
                my.add(R.id.button23);
                break;
            case 6:
                bt = ((Button) findViewById(R.id.button31));
                drawImage(bt, myColorValue);
                my.add(R.id.button31);
                break;
            case 7:
                bt = ((Button) findViewById(R.id.button32));
                drawImage(bt, myColorValue);
                my.add(R.id.button32);
                break;
            case 8:
                bt = ((Button) findViewById(R.id.button33));
                drawImage(bt, myColorValue);
                my.add(R.id.button33);
                break;
        }
    }


    @Override
    public void onClick(View view) {
        Button bt = ((Button) findViewById(view.getId()));
        root.add((view.getId()));
        drawImage(bt, redColor);
        checkMyself();
    }

    private void drawImage(Button bt, boolean colorValue) {
        if (colorValue)
            bt.setBackgroundResource(R.drawable.redfigure);
        else
            bt.setBackgroundResource(R.drawable.bluefigure);
        bt.setClickable(false);
    }

    private void checkMyself() {
     /*   if (my.size() >= 2) {
            if(!doMyStep())
                analyzeRoot();
        }
        else*/
            analyzeRoot();
    }

    private boolean doMyStep( ) {
        int indexOfBut = -1;
        int myBut = -1;
        int myBut2 = -1;
        myBut = my.get(my.size() - 1);
        myBut2 = my.get(my.size() - 2);
        int math = Math.abs(myBut - myBut2);
        switch (math) {
            case 1:
                indexOfBut = oneSpace(myBut, myBut2);
                break;
            case 2:
                indexOfBut =twoSpace(myBut, myBut2);
                break;
            case 3:
                indexOfBut = treeSpace(myBut, myBut2);
                break;
            case 4:
                indexOfBut = fourSpace(myBut, myBut2);
                break;
            case 6:
                if (myBut < myBut2 && checkBut(myBut + 3))
                    indexOfBut = myBut + 3;
                else if (myBut > myBut2 && checkBut(myBut2 + 3))
                    indexOfBut = myBut2 + 3;
                break;
            case 8:
                if (checkBut(4))
                    indexOfBut = 4;
                break;
        }
        if(indexOfBut != -1) {
            Button bt = ((Button) findViewById(buttons[indexOfBut]));
            my.add(buttons[indexOfBut]);
            drawImage(bt, myColorValue);
            return true;
        }
        return false;
    }


    private void analyzeRoot() {
        if (root.size() == 1) {
            doFirstStepAfterRoot();
            return;
        } else if (root.size() == 5) {
            finis();
        } else {
            if (checkWon(this.root)) {
                whyWon("Поздравляю!");
                return;
            } else if (checkWon(this.my)) {
                whyWon("Вы проиграли");
                return;
            }
            doNextStep();
            return;
        }
    }

    private void doFirstStepAfterRoot() {
        Button bt;
        int indexOfBut = 0;
        int rootBut = root.get(0);
        indexOfBut = randomBut(rootBut, -1);
        bt = ((Button) findViewById(buttons[indexOfBut]));
        my.add(buttons[indexOfBut]);
        drawImage(bt, myColorValue);
    }

    private void doNextStep() {
        Button bt;
        int indexOfBut = -1;
        int rootBut = -1;
        int rootBut2 = -1;
        int myBut = -1;
        int myBut2 = -1;
        for (int i = 0; i < 8; i++) {
            if (buttons[i] == root.get(root.size() - 1))
                rootBut = i;
            if (buttons[i] == root.get(root.size() - 2))
                rootBut2 = i;
        }
        indexOfBut = checkSpace(rootBut, rootBut2);
        bt = ((Button) findViewById(buttons[indexOfBut]));
        my.add(buttons[indexOfBut]);
        drawImage(bt, myColorValue);
        if(checkWon(this.my) ||(my.size() == 5))
            finis();
    }

    private int checkSpace(int rootBut, int rootBut2) {
        int indexOfBut = -1;
        int math = Math.abs(rootBut - rootBut2);
        if (math == 5 || math == 7)
            return checkPreviousBut( rootBut, rootBut2);
        else {
            switch (math) {
                case 1:
                    indexOfBut = oneSpace(rootBut, rootBut2);
                    break;
                case 2:
                    if ((rootBut == 7 && rootBut2 == 5) || (rootBut == 5 && rootBut2 == 7) || (rootBut == 1 && rootBut2 == 3) || (rootBut == 3 && rootBut2 == 1))
                        indexOfBut = checkPreviousBut(rootBut, rootBut2);
                    else
                        indexOfBut = twoSpace(rootBut, rootBut2);
                    break;
                case 3:
                    indexOfBut = treeSpace(rootBut, rootBut2);
                    break;
                case 4:
                    if ((rootBut == 7 && rootBut2 == 3) || (rootBut == 3 && rootBut2 == 7) || (rootBut == 1 && rootBut2 == 5) || (rootBut == 5 && rootBut2 == 1))
                        indexOfBut = checkPreviousBut(rootBut, rootBut2);
                    else
                        indexOfBut = fourSpace(rootBut, rootBut2);
                    break;
                case 6:
                    if (rootBut < rootBut2 && checkBut(rootBut + 3))
                        indexOfBut = rootBut + 3;
                    else if (rootBut > rootBut2 && checkBut(rootBut2 + 3))
                        indexOfBut = rootBut2 + 3;
                    break;
                case 8:
                    if (checkBut(4))
                        indexOfBut = 4;
                    break;
                        }
                    }
        if(indexOfBut != -1)
            return indexOfBut;
        return randomBut(rootBut, rootBut2);
    }


    private int oneSpace(int rootBut, int rootBut2) {
        if ((rootBut == 0 || rootBut == 3 || rootBut == 6) && checkBut(rootBut2 + 1))
            return rootBut2 + 1;
        else if ((rootBut == 2 || rootBut == 5 || rootBut == 8) && checkBut(rootBut2 - 1))
            return rootBut2 - 1;
        else if (rootBut == 1 || rootBut == 4 || rootBut == 7) {
            if ((rootBut2 < rootBut) && checkBut(rootBut + 1))
                return rootBut + 1;
            else if (checkBut(rootBut - 1))
                return rootBut - 1;
        }
        return -1;
    }

    private int twoSpace(int rootBut, int rootBut2) {
        if ((rootBut == 2 || rootBut2 == 2) && checkBut(6))
            return 6;
        else if ((rootBut == 6 || rootBut2 == 6) && checkBut(2))
            return 2;
        else if ((rootBut == 4 || rootBut2 == 4) && (rootBut == 6 || rootBut2 == 6) && checkBut(2))
            return 2;
        else if ((rootBut == 4 || rootBut2 == 4) && (rootBut == 2 || rootBut2 == 2) && checkBut(6))
            return 6;
        else if ((rootBut == 0 || rootBut == 3 || rootBut == 6) && checkBut(rootBut + 1))
            return rootBut + 1;
        else if ((rootBut2 == 0 || rootBut2 == 3 || rootBut2 == 6) && checkBut(rootBut2 + 1))
            return rootBut2 + 1;
        else if ((rootBut == 2 || rootBut == 5 || rootBut == 8) && checkBut(rootBut - 1))
            return rootBut - 1;
        else if ((rootBut2 == 2 || rootBut2 == 5 || rootBut2 == 8) && checkBut(rootBut2 - 1))
            return rootBut2 - 1;
        return -1;
    }

    private int treeSpace(int rootBut, int rootBut2) {
        if ((rootBut == 2 || rootBut == 1 || rootBut == 0) && checkBut(rootBut2 + 3))
            return rootBut2 + 3;
        else if ((rootBut2 == 2 || rootBut2 == 1 || rootBut2 == 0) && checkBut(rootBut + 3))
            return rootBut + 3;
        else if ((rootBut == 6 || rootBut == 7 || rootBut == 8) && checkBut(rootBut2 - 3))
            return rootBut2 - 3;
        else if ((rootBut2 == 6 || rootBut2 == 7 || rootBut2 == 8) && checkBut(rootBut - 3))
            return rootBut - 3;
        else if (rootBut < rootBut2) {
            if ((rootBut == 3 || rootBut == 4 || rootBut == 5) && checkBut(rootBut - 3))
                return rootBut - 3;
            else if ((rootBut2 == 3 || rootBut2 == 4 || rootBut2 == 5) && checkBut(rootBut2 + 3))
                return rootBut2 + 3;
        } else if (rootBut > rootBut2) {
            if ((rootBut == 3 || rootBut == 4 || rootBut == 5) && checkBut(rootBut + 3))
                return rootBut + 3;
            else if ((rootBut2 == 3 || rootBut2 == 4 || rootBut2 == 5) && checkBut(rootBut2 - 3))
                return rootBut2 - 3;
        }
        return -1;
    }

    private int fourSpace(int rootBut, int rootBut2) {
        if (rootBut == 2 && checkBut(rootBut + 2))
            return rootBut + 2;
        else if (rootBut2 == 2 && checkBut(rootBut2 + 2))
            return rootBut2 + 2;
        else if (rootBut == 0 && checkBut(rootBut2 + 4))
            return rootBut2 + 4;
        else if (rootBut2 == 0 && checkBut(rootBut2 + 4))
            return rootBut2 + 4;
        else if (rootBut == 8 && checkBut(rootBut2 - 4))
            return rootBut2 - 4;
        else if (rootBut2 == 8 && checkBut(rootBut - 4))
            return rootBut - 4;
        else if (rootBut < rootBut2) {
            if (rootBut == 4 && checkBut(0))
                return 0;
            else if (rootBut2 == 4 && checkBut(8))
                return 8;
        }
        else if (rootBut > rootBut2) {
            if (rootBut == 4 && checkBut(8))
                return 8;
            else if (rootBut2 == 4 && checkBut(0))
                return 0;
        }
        return -1;
    }

    private int checkPreviousBut(int rootBut, int rootBut2){
        if(root.size() >= 3){
            int rootBut3 =  root.get(root.size() - 3);
            return checkSpace( rootBut, rootBut3);
        }
        return randomBut( rootBut, rootBut2);
    }

    private int randomBut(int rootBut, int rootBut2){
        int indexOfBut = -1;
        int myBut = -1;
        int myBut2 = -1;
        if (my.size() == 1)
            myBut = my.get(0);
        if (my.size() == 2)
            myBut2 = my.get(1);
        while (true) {
            indexOfBut = rand.nextInt(9);
            if (indexOfBut != rootBut && indexOfBut != rootBut2 && indexOfBut != myBut && indexOfBut != myBut2 && checkBut(indexOfBut))
                break;
        }
        return indexOfBut;
    }

    private boolean checkBut(int indexOfBut) {
        for(int item : my) {
            if (buttons[indexOfBut] == item)
                return false;
        }
        for(int item : root) {
            if (buttons[indexOfBut] == item)
                return false;
        }
        return true;
    }

    private boolean checkWon(List<Integer> list) {
        if (list.contains(buttons[0]) && list.contains(buttons[1]) && list.contains(buttons[2])) return true;
        if (list.contains(buttons[3]) && list.contains(buttons[4]) && list.contains(buttons[5])) return true;
        if (list.contains(buttons[6]) && list.contains(buttons[7]) && list.contains(buttons[8])) return true;
        if (list.contains(buttons[0]) && list.contains(buttons[3]) && list.contains(buttons[6])) return true;
        if (list.contains(buttons[1]) && list.contains(buttons[4]) && list.contains(buttons[7])) return true;
        if (list.contains(buttons[2]) && list.contains(buttons[5]) && list.contains(buttons[8])) return true;
        if (list.contains(buttons[0]) && list.contains(buttons[4]) && list.contains(buttons[8])) return true;
        if (list.contains(buttons[2]) && list.contains(buttons[4]) && list.contains(buttons[6])) return true;
        return false;
    }

    private void finis(){
        if (checkWon(this.root)) {
            whyWon("Поздравляю!");
        } else if (checkWon(this.my)) {
            whyWon("Вы проиграли");
        } else
            whyWon("Ничья");
    }


    private void whyWon(String message) {
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setTitle(message);
        dlgAlert.setPositiveButton("Заново", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               // onRestart();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
        dlgAlert.setNegativeButton("Выход", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
        dlgAlert.create().show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game2);
        findViewById(R.id.button11).setOnClickListener(this);
        findViewById(R.id.button12).setOnClickListener(this);
        findViewById(R.id.button13).setOnClickListener(this);
        findViewById(R.id.button21).setOnClickListener(this);
        findViewById(R.id.button22).setOnClickListener(this);
        findViewById(R.id.button23).setOnClickListener(this);
        findViewById(R.id.button31).setOnClickListener(this);
        findViewById(R.id.button32).setOnClickListener(this);
        findViewById(R.id.button33).setOnClickListener(this);
    }

}
