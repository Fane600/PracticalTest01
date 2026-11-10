package ro.pub.cs.systems.eim.practicaltest01

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PracticalTest01SecondaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practical_test01_secondary)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val totalTextView = findViewById<TextView>(R.id.total_text_view)
        val okButton = findViewById<Button>(R.id.ok_button)
        val cancelButton = findViewById<Button>(R.id.cancel_button)

        val total = intent.getIntExtra("total", 0)
        totalTextView.text = total.toString()
        Log.d("PracticalTest01", "Secondary activity opened with total = $total")

        okButton.setOnClickListener {
            Log.d("PracticalTest01", "Ok pressed")
            setResult(RESULT_OK)
            finish()
        }

        cancelButton.setOnClickListener {
            Log.d("PracticalTest01", "Cancel pressed")
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}