package com.wolfsekhar.passwordgenerator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.slider.LabelFormatter;
import com.google.android.material.slider.Slider;
import com.wolfsekhar.passwordgenerator.model.Generator;

public class GeneratorActivity extends AppCompatActivity {
    private ClipboardManager clipboardManager;
    private ClipData data;
    private TextView textViewPassword,textViewLabelLength;
    private Button buttonCopy,buttonMinus,buttonAdd,buttonGenerate;
    private Slider slider;
    private MaterialCheckBox checkBoxSmallAlphabet,checkBoxCapitalAlphabet,checkBoxCharacter,
                                checkBoxNumber;
    private int length = 8;
    private final Generator generator = new Generator();
    private String password="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setViewsWithId();

        buttonCopy.setOnClickListener(l ->{
            saveToClipboard(Uri.parse(password));
            if (clipboardManager.getPrimaryClipDescription().getLabel().equals("passwordGenerator")){
                Toast.makeText(GeneratorActivity.this, "Copied To Clipboard", Toast.LENGTH_SHORT).show();
            }
        });

        buttonMinus.setOnClickListener( l -> {
            if (length !=8){
                length--;
                slider.setValue(length);
                textViewLabelLength.setText(String.valueOf(length));
            }
        });

        buttonAdd.setOnClickListener(l -> {
            if (length !=256){
                length++;
                slider.setValue(length);
                textViewLabelLength.setText(String.valueOf(length));
            }
        });

        buttonGenerate.setOnClickListener(l -> {
            password = generator.generatePassword(checkBoxSmallAlphabet.isChecked(),
                    checkBoxCapitalAlphabet.isChecked(),
                    checkBoxCharacter.isChecked(),
                    checkBoxNumber.isChecked(),length);
            textViewPassword.setText(password);
        });

       slider.addOnChangeListener((slider, value, fromUser) -> {
           length = (int) value;
           textViewLabelLength.setText(String.valueOf(length));
       });

    }

    private void setViewsWithId(){
        textViewPassword = findViewById(R.id.textview_password);
        textViewLabelLength = findViewById(R.id.textview_label_size);

        buttonCopy = findViewById(R.id.button_copy);
        buttonMinus = findViewById(R.id.button_minus);
        buttonAdd = findViewById(R.id.button_plus);
        buttonGenerate = findViewById(R.id.button_generate);

        slider = new Slider(this);
        slider = findViewById(R.id.slider);

        checkBoxSmallAlphabet = findViewById(R.id.checkbox_smallAlphabets);
        checkBoxCapitalAlphabet = findViewById(R.id.checkbox_capitalAlphabets);
        checkBoxCharacter = findViewById(R.id.checkbox_characters);
        checkBoxNumber = findViewById(R.id.checkbox_numbers);
    }

    private void saveToClipboard(Uri password){
        clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        data = ClipData.newRawUri("passwordGenerator",password);
        clipboardManager.setPrimaryClip(data);
    }


}