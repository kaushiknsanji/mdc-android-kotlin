package com.google.codelabs.mdc.kotlin.shipping

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.shipping_info_activity.*

class ShippingInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shipping_info_activity)

        // Get the Root View of the layout
        val rootView = findViewById<View>(android.R.id.content)

        // Get all the TextInputLayouts
        val textInputLayouts = Utils.findViewsWithType(rootView, TextInputLayout::class.java)

        // Register a click listener on the Save button
        save_button.setOnClickListener {
            // Boolean to check if there are no errors made in the Text Input
            // Defaulted to True to indicate no errors are present
            var noErrors = true

            // Iterate over all the TextInputLayouts to examine them for any errors
            textInputLayouts.forEach { textField: TextInputLayout ->
                if (textField.editText?.text.toString().isEmpty()) {
                    // If the Text field is empty, then show the appropriate error text
                    // and consider it as an error
                    textField.error = getString(R.string.error_string)
                    noErrors = false
                } else {
                    // Clear any previously set error message
                    textField.error = null
                }
            }

            if (noErrors) {
                // When there are no errors, all fields are valid!
            }
        }
    }
}
