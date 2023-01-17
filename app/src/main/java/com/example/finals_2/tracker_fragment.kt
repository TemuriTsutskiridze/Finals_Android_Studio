package com.example.finals_2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.math.log10


class tracker_fragment : Fragment(R.layout.fragment_tracker_fragment) {
    lateinit var creditNumberEditText: EditText
    lateinit var trackTextView: TextView
    lateinit var cardTextView: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        creditNumberEditText = view.findViewById(R.id.creditNumberEditText)
        trackTextView = view.findViewById(R.id.trackTextView)
        cardTextView = view.findViewById(R.id.cardTextView)

        trackTextView.setOnClickListener {
            var creditNumber = creditNumberEditText.text.toString().toLong()
            var digit: Int
            var remainedDigit: Int
            var product: Int
            var checkSum: Int = 0

            if (creditNumber < 0){
                Toast.makeText(activity, "Wrong Credit Card Number, Try Again", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            // calculates the length of credit card
            var length: Int = (log10(creditNumber.toDouble()) + 1).toInt();

            var first_two_digits: Int = (creditNumber / Math.pow(10.0, (length - 2).toDouble())).toInt()
            var first_digit: Int = (creditNumber / Math.pow(10.0, (length - 1).toDouble())).toInt()

            // calculate the checkSum

            while (creditNumber > 0){
                digit = (creditNumber % 100 / 10).toInt()

                product = digit * 2

                if(product >= 10){
                    product = product % 10 + product / 10
                    checkSum += product
                }else {
                    checkSum += product
                }

                // add remained digits
                remainedDigit = (creditNumber % 10).toInt()
                checkSum += remainedDigit

                creditNumber = creditNumber / 100
            }


            // checking whether or not credit card number is valid (checkSum should be ended with '0')
            var valid: Boolean = checkSum % 10 == 0

            // print what type of credit card user has
            if(valid && length == 15 && (first_two_digits == 34) || first_two_digits == 37){
                cardTextView.text = "AMEx"
            }else if(valid && length == 16 && (first_two_digits == 51 || first_two_digits == 52 || first_two_digits == 53 || first_two_digits == 54 || first_two_digits == 55)){
                cardTextView.text = "MASTERCARD"
            }else if(valid && (length == 13 || length == 16) && first_digit == 4){
                cardTextView.text = "VISA"
            }else {
                cardTextView.text = "INVALID"
            }


        }
    }


}