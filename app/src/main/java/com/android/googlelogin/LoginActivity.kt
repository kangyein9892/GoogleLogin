package com.android.googlelogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : AppCompatActivity() {

    var googleSignClient : GoogleSignInClient? = null
    val RC_SIGN_IN = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        var googleSignInClient = GoogleSignIn.getClient(this, gso)

        val google_sign_in_button = findViewById<SignInButton>(R.id.google_sign_in_button)

        google_sign_in_button.setOnClickListener {
            var signInIntent = googleSignInClient?.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
    }
    fun fun_MoveNextPage() {
        var currentUser = FirebaseAuth.getInstance().currentUser
        if(currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            this.finish()
        }
    }
    fun fun_FirebaseAuthWithGoogle(acct : GoogleSignInAccount?) {
        var credential = GoogleAuthProvider.getCredential(acct?.idToken, null)
        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {
            task ->
                if(task.isSuccessful){
                    fun_MoveNextPage()
                }
        }
    }

    override fun onResume() {
        super.onResume()
        fun_MoveNextPage()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try{
                val account = task.getResult(ApiException::class.java)
                fun_FirebaseAuthWithGoogle(account)
            }
            catch(e: ApiException) {

            }
        }
        else{

        }
    }
}