package com.prgx.birdsendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText phoneInput;
    EditText messageInput;
    Button sendMessageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneInput = findViewById(R.id.editTextNumber);
        messageInput = findViewById(R.id.editTextTextMessage);
        sendMessageBtn = findViewById(R.id.send_button);

        Uri webpage = Uri.parse("https://wa.me/5541992378001?text=Tenho%20interesse%20em%20comprar%20seu%20carro");

        final String number = "+554196037530";
        String text = "Olá, eu sou uma mensagem enviada por um app feito pelo Othon";

        sendMessageBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        boolean isWhatsAppInstalled = isAppInstalled(MainActivity.this, "com.whatsapp");

                        if (isWhatsAppInstalled){
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + phoneInput.getText() + "&text=" + messageInput.getText()));
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(MainActivity.this, "WhatsApp não instalado", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }

    private boolean isAppInstalled(Context context, String s) {
        try{
            context.getPackageManager().getPackageInfo(s, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}