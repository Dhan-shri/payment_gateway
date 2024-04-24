package com.dhanshri.online_pay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.dhanshri.online_pay.databinding.ActivityMainBinding
import com.razorpay.Checkout
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding= DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.idBtnMakePaymanet.setOnClickListener {
            val amt = binding.idEdtAmt.text.toString()
            val amount = Math.round(amt.toFloat()*100).toInt()

            val checkout = Checkout()

            // create key from the setting section from RAZORPAYcommenting key for now key: - rzp_test_1DP5mmOlF5G5ag
            checkout.setKeyID("key")

            checkout.setImage(R.drawable.ic_launcher_foreground)

            val obj = JSONObject()

            try {
                obj.put("name", "Online Payment")
                obj.put("description", "Test Payment")
                obj.put("theme.color", "#0093DD")
                obj.put("currency", "INR")
                obj.put("amount", amount)
                obj.put("prefill.contact", "9999999999")
                obj.put("prefill.email", "dhanshri9545@gmail.com")

                checkout.open(this, obj)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun onPaymentSuccess(p0: String?) {
        Toast.makeText(this, "Payment Success: $p0", Toast.LENGTH_SHORT).show()
    }

    fun onPaymentError(p0: Int, p1: String?) {
        Toast.makeText(this, "Payment Error: $p1", Toast.LENGTH_SHORT).show()
    }
}