package br.concrete.bootcamp_concrete_julho_2020

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_login.*

private const val PASSWORD_MESSAGE = "Sua senha deve conter:\n Oito caracteres.\n Uma letra maiúscula.\n Uma letra minúscula.\n Um número.\n Um caracter especial."
private const val EMAIL_EMPTY_MESSAGE = "O campo email está vazio."
private const val PASSWORD_EMPTY_MESSAGE = "O campo password está vazio."

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login.setOnClickListener {
            val passwordValidator = PasswordValidator()
            when {
                email.text.isEmpty() -> {
                    setUpPasswordDialog(EMAIL_EMPTY_MESSAGE)
                }
                password.text.isEmpty() -> {
                    setUpPasswordDialog(PASSWORD_EMPTY_MESSAGE)
                }
                !passwordValidator.validate(password.text.toString()) -> {
                    setUpPasswordDialog(PASSWORD_MESSAGE)
                }
                else -> {
                    startNewActivity()
                }
            }
        }

        clear.setOnClickListener {
            email.text.clear()
            password.text.clear()
        }
    }


    private fun setUpPasswordDialog(message: String) {
        val alertDialog: AlertDialog? = this.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton("Ok",
                    DialogInterface.OnClickListener { dialog, id ->
                        password.text.clear()
                    })
                /*setNegativeButton("Cancel",
                        DialogInterface.OnClickListener { dialog, id ->

                        })*/
                setMessage(message)
            }
            builder.create()
        }
        alertDialog?.show()
    }

    private fun startNewActivity() {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }
}