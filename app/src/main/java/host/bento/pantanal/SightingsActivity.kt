package host.bento.pantanal

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.*
import org.json.JSONArray
import org.json.JSONObject

class SightingsActivity : Activity() {
    private val prefs by lazy { getSharedPreferences("sightings_local", MODE_PRIVATE) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; setPadding(24,24,24,24) }
        root.addView(TextView(this).apply { text = "Avistamentos (registro local)"; textSize = 22f })
        val species = EditText(this).apply { hint = "Animal / espécie / sinal" }
        val notes = EditText(this).apply { hint = "Observações"; minLines = 3 }
        root.addView(species); root.addView(notes)
        root.addView(Button(this).apply {
            text = "Salvar avistamento neste aparelho"
            setOnClickListener {
                if (species.text.isBlank()) { species.error = "Informe o avistamento"; return@setOnClickListener }
                val list = JSONArray(prefs.getString("items", "[]"))
                list.put(JSONObject().put("species", species.text.toString()).put("notes", notes.text.toString()).put("createdAt", System.currentTimeMillis()))
                prefs.edit().putString("items", list.toString()).apply()
                species.text.clear(); notes.text.clear(); refresh()
            }
        })
        val listView = TextView(this).apply { textSize = 16f; setPadding(0,24,0,0) }
        root.addView(listView)
        root.addView(Button(this).apply { text = "Voltar"; setOnClickListener { finish() } })
        setContentView(root)
        refreshInto(listView)
    }
    private var viewRef: TextView? = null
    private fun refresh() { viewRef?.let { refreshInto(it) } }
    private fun refreshInto(v: TextView) {
        viewRef = v
        val items = JSONArray(prefs.getString("items", "[]"))
        val out = StringBuilder("Registros salvos neste aparelho: ${items.length()}\n\n")
        for (i in items.length()-1 downTo 0) {
            val item = items.getJSONObject(i)
            out.append("• ").append(item.optString("species")).append("\n")
            if (item.optString("notes").isNotBlank()) out.append(item.optString("notes")).append("\n")
            out.append("\n")
        }
        v.text = out.toString()
    }
}
