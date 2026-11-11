package com.example.presencemanager

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.app.AlertDialog
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.ImageView
import android.widget.TextView
import android.text.util.Linkify
import android.text.method.LinkMovementMethod

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val cardGroupes = findViewById<View>(R.id.cardGroupes)
        val cardAppel = findViewById<View>(R.id.cardAppel)
        val cardHistorique = findViewById<View>(R.id.cardHistorique)
        val cardStatistiques = findViewById<View>(R.id.cardStatistiques)
        val imgProfil = findViewById<ImageView>(R.id.imgProfil)

        val detecteur = GestureDetector(
            this,
            object : GestureDetector.SimpleOnGestureListener() {

                override fun onDown(e: MotionEvent): Boolean {
                    return true
                }

                override fun onDoubleTap(e: MotionEvent): Boolean {
                    AlertDialog.Builder(this@DashboardActivity)
                        .setTitle("À propos de PrésenceManager")
                        .setMessage(
                            "Application de gestion des présences — Version 1.0"
                        )
                        .setPositiveButton("OK", null)
                        .show()

                    return true
                }
            }
        )

        imgProfil.setOnTouchListener { _, evenement ->
            detecteur.onTouchEvent(evenement)
            true
        }

        cardGroupes.setOnClickListener {
            startActivity(Intent(this, GroupesActivity::class.java))
        }

        cardAppel.setOnClickListener {
            startActivity(Intent(this, AppelActivity::class.java))
        }

        cardHistorique.setOnClickListener {
            startActivity(Intent(this, HistoriqueActivity::class.java))
        }

        cardStatistiques.setOnClickListener {
            startActivity(Intent(this, StatistiquesActivity::class.java))
        }

        cardGroupes.setOnLongClickListener {
            Toast.makeText(
                this,
                "Groupes : sélectionner ou consulter les groupes",
                Toast.LENGTH_SHORT
            ).show()
            true
        }

        cardAppel.setOnLongClickListener {
            Toast.makeText(
                this,
                "Faire l'appel : démarrer une séance",
                Toast.LENGTH_SHORT
            ).show()
            true
        }

        findViewById<TextView>(R.id.txtFooterDashboard).setOnClickListener {
            afficherInfosDeveloppeur()
        }
    }

    private fun afficherInfosDeveloppeur() {
        val dialogue = AlertDialog.Builder(this)
            .setTitle("Informations du développeur")
            .setMessage(
                "Application : PrésenceManager\n" +
                        "Version : 1.0\n" +
                        "Développeur : ELBADI Rayane\n" +
                        "GitHub : https://github.com/ELBADIRayane/PresenceManager"
            )
            .setPositiveButton("OK", null)
            .show()

        dialogue.findViewById<TextView>(android.R.id.message)?.let { texte ->
            Linkify.addLinks(texte, Linkify.WEB_URLS)
            texte.movementMethod = LinkMovementMethod.getInstance()
        }
    }
}
