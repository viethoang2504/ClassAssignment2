package com.example.simplelist

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {

    private lateinit var edtNumber: EditText
    private lateinit var radioEven: RadioButton
    private lateinit var radioOdd: RadioButton
    private lateinit var radioSquare: RadioButton
    private lateinit var btnShow: Button
    private lateinit var listView: ListView
    private lateinit var tvError: TextView
    private lateinit var adapter: ArrayAdapter<Int>
    private val numberList = mutableListOf<Int>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        edtNumber = findViewById(R.id.edtNumber)
        radioEven = findViewById(R.id.radioEven)
        radioOdd = findViewById(R.id.radioOdd)
        radioSquare = findViewById(R.id.radioSquare)
        btnShow = findViewById(R.id.btnShow)
        listView = findViewById(R.id.listView)
        tvError = findViewById(R.id.tvError)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numberList)
        listView.adapter = adapter

        btnShow.setOnClickListener {
            val input = edtNumber.text.toString()
            if (input.isEmpty()) {
                tvError.text = "Vui lòng nhập số nguyên dương"
                tvError.visibility = View.VISIBLE
                return@setOnClickListener
            }

            val n = input.toIntOrNull()
            if (n == null || n <= 0) {
                tvError.text = "Vui lòng nhập số nguyên dương lớn hơn 0"
                tvError.visibility = View.VISIBLE
                return@setOnClickListener
            }

            tvError.visibility = View.GONE
            numberList.clear()

            when {
                radioEven.isChecked -> {
                    for (i in 0..n step 2) {
                        numberList.add(i)
                    }
                }
                radioOdd.isChecked -> {
                    for (i in 1..n step 2) {
                        numberList.add(i)
                    }
                }
                radioSquare.isChecked -> {
                    var i = 0
                    while (i * i <= n) {
                        numberList.add(i * i)
                        i++
                    }
                }
                else -> {
                    tvError.text = "Vui lòng chọn loại số"
                    tvError.visibility = View.VISIBLE
                    return@setOnClickListener
                }
            }

            adapter.notifyDataSetChanged()
        }
    }


}
