package host.bento.pantanal

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.widget.*

class ProfessionalActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; gravity = Gravity.CENTER; setPadding(24,24,24,24) }
        layout.addView(TextView(this).apply {
            text = "Área profissional\n\nCadastro via Cadastur (guia) ou CNPJ (pousada), validação administrativa e perfil sincronizado serão implementados na próxima etapa."
            textSize = 20f
        })
        layout.addView(Button(this).apply { text = "Voltar"; setOnClickListener { finish() } })
        setContentView(layout)
    }
}
