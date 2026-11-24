package host.bento.pantanal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.*

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(32, 48, 32, 48)
            setBackgroundColor(Color.rgb(22, 61, 43))
        }
        fun button(label: String, target: Class<*>) = Button(this).apply {
            text = label
            textSize = 18f
            setOnClickListener { startActivity(Intent(this@MainActivity, target)) }
        }
        layout.addView(TextView(this).apply {
            text = "PANTANAL\nO Pantanal na palma da mão"
            textSize = 26f; gravity = Gravity.CENTER; setTextColor(Color.WHITE)
            setPadding(0,0,0,32)
        })
        layout.addView(button("AVISTAMENTOS — Offline", SightingsActivity::class.java))
        layout.addView(button("GUIA — Área profissional", ProfessionalActivity::class.java))
        layout.addView(button("POUSADA — Área profissional", ProfessionalActivity::class.java))
        setContentView(layout)
    }
}
