package com.rayanandisheh.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.rayanandisheh.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var hasNumber: Boolean = false
    private var hasOperator = false
    private var hasDecimal = false
    private var isNegative = false
    private var canAddNumber = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun onNumberClicked(view: View) {
        if (canAddNumber) {
            binding.txtScreen.append((view as Button).text)
            hasNumber = true
            canAddNumber = true
        }
    }

    fun onOperatorClicked(view: View) {
        if (hasNumber && !hasOperator) {
            binding.txtScreen.append((view as Button).text)
            hasDecimal = false
            hasNumber = false
            hasOperator = true
            canAddNumber = true
        }
    }

    fun onClearClicked(view: View) {
        binding.txtScreen.text = ""
        hasNumber = false
        hasDecimal = false
        hasOperator = false
        canAddNumber = true
    }

    fun onResultClicked(view: View) {

        var math = binding.txtScreen.text.toString()
        if (math.startsWith("-")) {
            math = math.substring(1)
            isNegative = true

        }

        if (hasNumber) {
            try {
                if (math.contains("+")) doMath("+", math)
                if (math.contains("-")) doMath("-", math)
                if (math.contains("*")) doMath("*", math)
                if (math.contains("/")) doMath("/", math)
                if (math.contains("@")) doMath("@", math)
                if (math.contains("%")) doMath("%", math)
            } catch (e: Exception) {
                binding.txtScreen.text = e.toString()
            }
        }

    }

    fun onDecimalClicked(view: View) {
        if (!hasDecimal && hasNumber) {
            binding.txtScreen.append(".")
            hasDecimal = true
            hasNumber = false

        }
    }


    private fun doMath(s: String, math: String) {

        val strSplit = math.split(s)
        var numOne = strSplit[0].toDouble()
        val numTow = strSplit[1].toDouble()

        if (isNegative) numOne *= (-1)


        var result = when (s) {
            "+" -> numOne + numTow
            "-" -> numOne - numTow
            "*" -> numOne * numTow
            "/" -> numOne / numTow
            "@" -> (numOne * numTow) / 100
            "%" -> numOne % numTow
            else -> ""

        }

        if (result == -0.0) result = 0.0

        binding.txtScreen.text = result.toString()
        hasNumber = true
        hasDecimal = true
        hasOperator = false
        isNegative = false
        canAddNumber = false

    }

}