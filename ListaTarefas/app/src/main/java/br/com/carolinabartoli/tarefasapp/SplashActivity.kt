package br.com.carolinabartoli.tarefasapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val handler = Handler()
        handler.postDelayed({abrirTelaLista()},3000)
    }

    fun abrirTelaLista(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}