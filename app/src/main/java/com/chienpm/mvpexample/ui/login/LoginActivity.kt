package com.chienpm.mvpexample.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.chienpm.mvpexample.R
import com.chienpm.mvpexample.ui.posts.PostActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(),
    LoginContract.View {


    private val presenter =
        LoginPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        button.setOnClickListener{validateCredentials()}
    }

    override fun onResume() {
        super.onResume()
        presenter.subscribe()
    }

    private fun validateCredentials() {
        presenter.login(username.text.toString(),
            password.text.toString())

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
    }

    override fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress.visibility = View.GONE
    }

    override fun setUsernameError() {
        username.error = getString(R.string.username_error)
    }

    override fun setPasswordError() {
        password.error = getString(R.string.password_error)
    }

    override fun setLoginSucceed(msg: String) {
        Toast.makeText(this, "Login succeed! $msg", Toast.LENGTH_LONG).show()
    }

    override fun setLoginFailed(reason: String) {
        username.error = reason
//        Toast.makeText(this, "Login failed! $reason", Toast.LENGTH_LONG).show()
    }

    override fun navigateToHome() {
        startActivity(Intent(this, PostActivity::class.java))
    }
}
