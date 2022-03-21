package com.example.databindingexercise3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.databindingexercise3.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Person person;
    private ActivityMainBinding mainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        person = new Person("老王","西安",22);
        mainBinding.setPerson(person);
        mainBinding.setPersonHandler(new PersonHandler());
    }
    public class PersonHandler{
        public void changePersonName(){
            person.setName("张三" + new Random().nextInt(100));
            person.setAge(new Random().nextInt(100));
        }
        public void changePersonAge(){
            person.setAddress("丽江" + new Random().nextInt(100));
            person.setAge(new Random().nextInt(100));
        }
    }
}