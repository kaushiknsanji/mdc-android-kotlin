package com.google.codelabs.mdc.kotlin.shrine

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.shr_login_fragment.*
import kotlinx.android.synthetic.main.shr_login_fragment.view.*

/**
 * Fragment representing the login screen for Shrine.
 */
class LoginFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.shr_login_fragment, container, false)

        // Set an error if the password is less than 8 characters
        // If password is valid, clear the error and advance to the next screen
        view.next_button.setOnClickListener {
            if (!isPasswordValid(password_edit_text.text)) {
                // Set the Password field error when the entered password is invalid
                password_text_input.error = getString(R.string.shr_error_password)
            } else {
                // When the entered password is valid

                // Clear the error on the Password field
                password_text_input.error = null
                // Navigate to the next Fragment
                (activity as NavigationHost).navigateTo(ProductGridFragment(), false)
            }
        }

        // Clear the error when 8 or more characters are entered
        view.password_edit_text.setOnKeyListener { _, _, _ ->
            if (isPasswordValid(password_edit_text.text)) {
                // Clear the error on the Password field
                password_text_input.error = null
            }
            // Returning false as we do not wish to consume the event
            false
        }

        return view
    }

    /**
     * Method that checks for Password validity.
     * @param text [Editable] instance of the Password
     * [com.google.android.material.textfield.TextInputEditText] field containing
     * the user entered password.
     *
     * @return `true`/valid if the [text] is not `null` and
     * its length is equal to or more than 8 characters; `false` otherwise
     */
    private fun isPasswordValid(text: Editable?): Boolean = (text != null && text.length >= 8)

}
