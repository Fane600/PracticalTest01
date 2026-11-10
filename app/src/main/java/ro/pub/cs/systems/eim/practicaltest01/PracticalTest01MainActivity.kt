package ro.pub.cs.systems.eim.practicaltest01

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PracticalTest01MainActivity : AppCompatActivity() {

    private lateinit var leftEditText: EditText
    private lateinit var rightEditText: EditText

    private val secondaryActivityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                Toast.makeText(this, "Result: OK", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Result: Cancel", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practical_test01_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        leftEditText = findViewById(R.id.left_edit_text)
        rightEditText = findViewById(R.id.right_edit_text)
        val leftButton = findViewById<Button>(R.id.left_button)
        val rightButton = findViewById<Button>(R.id.right_button)
        val navigateButton = findViewById<Button>(R.id.navigate_button)

        leftButton.setOnClickListener {
            val value = leftEditText.text.toString().toInt()
            leftEditText.setText((value + 1).toString())
        }

        rightButton.setOnClickListener {
            val value = rightEditText.text.toString().toInt()
            rightEditText.setText((value + 1).toString())
        }

        navigateButton.setOnClickListener {
            val left = leftEditText.text.toString().toInt()
            val right = rightEditText.text.toString().toInt()
            val intent = Intent(this, PracticalTest01SecondaryActivity::class.java)
            intent.putExtra("total", left + right)
            secondaryActivityLauncher.launch(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("leftCount", leftEditText.text.toString())
        outState.putString("rightCount", rightEditText.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        leftEditText.setText(savedInstanceState.getString("leftCount", "0"))
        rightEditText.setText(savedInstanceState.getString("rightCount", "0"))
    }
}