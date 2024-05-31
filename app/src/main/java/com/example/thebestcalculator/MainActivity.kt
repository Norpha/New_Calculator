package com.example.thebestcalculator

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

import com.example.thebestcalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var num1 = ""
    private var num2 = ""
    private var operation = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        val numberButtons = listOf<Button>(
            binding.btn0, binding.btn1, binding.btn2, binding.btn3,
            binding.btn4, binding.btn5, binding.btn6, binding.btn7,
            binding.btn8, binding.btn9
        )

        numberButtons.forEach { button ->
            button.setOnClickListener {
                if (operation.isEmpty()) {
                    num1 += button.text.toString()
                    binding.resultTextView.text = num1
                } else {
                    num2 += button.text.toString()
                    binding.resultTextView.text = num1 + operation + num2
                }
            }
        }

        binding.btnDot.setOnClickListener {
            if (operation.isEmpty()) {
                if (!num1.contains('.')) {
                    num1 += "."
                    binding.resultTextView.text = num1
                }
            } else {
                if (!num2.contains('.')) {
                    num2 += "."
                    binding.resultTextView.text = num1 + operation + num2
                }
            }
        }

        binding.btnClear.setOnClickListener {
            num1 = ""
            num2 = ""
            operation = ""
            binding.resultTextView.text = "0"
        }

        binding.btnBack.setOnClickListener {
            if (operation.isEmpty()) {
                if (num1.isNotEmpty()) {
                    num1 = num1.substring(0, num1.length - 1)
                    binding.resultTextView.text = if (num1.isEmpty()) "0" else num1
                }
            } else {
                if (num2.isNotEmpty()) {
                    num2 = num2.substring(0, num2.length - 1)
                    binding.resultTextView.text = num1 + operation + num2
                }
            }
        }

        binding.btnAdd.setOnClickListener { setOperation("+") }
        binding.btnSubtract.setOnClickListener { setOperation("-") }
        binding.btnMultiply.setOnClickListener { setOperation("*") }
        binding.btnDivide.setOnClickListener { setOperation("/") }

        binding.btnEquals.setOnClickListener {
            val result = when (operation) {
                "+" -> num1.toDouble() + num2.toDouble()
                "-" -> num1.toDouble() - num2.toDouble()
                "*" -> num1.toDouble() * num2.toDouble()
                "/" -> num1.toDouble() / num2.toDouble()
                else -> 0.0
            }
            binding.resultTextView.text = result.toString()
            num1 = result.toString()
            num2 = ""
            operation = ""
        }
    }

    private fun setOperation(op: String) {
        if (num1.isNotEmpty() && num2.isEmpty()) {
            operation = op
            binding.resultTextView.text = num1 + operation
        }
    }
}
