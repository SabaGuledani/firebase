package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import com.example.firebase.databinding.ActivityLogInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class LogInActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = binding.email
        val password = binding.password
        val btn = binding.createbtn

        btn.setOnClickListener { 
            if (email.editText?.text.toString().isValidEmail() && password.editText?.text.toString() != ""
                && password.editText?.text.toString().length >= 6 ){
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(email.editText?.text.toString(),password.editText?.text.toString())
                    .addOnCompleteListener {task ->
                        if(task.isSuccessful){
                            Toast.makeText(this, "წარმატებით დარეგისტრირდით!", Toast.LENGTH_SHORT).show()
                            
                        }else{
                            Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show()
                        }
                        
                    }
                
            }
        }

    }

    fun String.isValidEmail() =
        !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()


}