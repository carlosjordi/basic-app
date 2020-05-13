package com.carlosjordi.basicapp.ui.additem

import android.text.Editable
import android.text.TextWatcher
import com.carlosjordi.basicapp.R
import com.carlosjordi.basicapp.databinding.FragmentAddItemBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


/**
 * Se encarga de chekar que nuestro input no este vacio y actualiza el mensaje de error
 * en el [TextInputLayout].
 *
 * Retorna true si tiene contenido, false en caso contrario.
 */
fun TextInputEditText.isValidField(tilField: TextInputLayout, errorMessage: Int): Boolean {
    if (text.isNullOrBlank()) {
        tilField.error = context.resources.getString(errorMessage)
        return false
    }
    tilField.error = null
    return true
}

fun validateField(input: TextInputEditText, til: TextInputLayout, errorMessage: Int) {
    input.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            input.isValidField(til, errorMessage)
        }
    })
}

fun addValidations(binding: FragmentAddItemBinding) {
    validateField(
        binding.titleInput,
        binding.tilTitle,
        R.string.error_message_title
    )
    validateField(
        binding.descriptionInput,
        binding.tilDescription,
        R.string.error_message_description
    )
}

fun isValidImage(binding: FragmentAddItemBinding, photoTaken: Boolean): Boolean {
    if (!photoTaken) {
        binding.errorMessageImage.text =
            binding.errorMessageImage.context.resources.getString(R.string.error_message_image)
        return false
    }
    binding.errorMessageImage.text = ""
    return true
}

fun isValidItem(binding: FragmentAddItemBinding): Boolean {
    return binding.titleInput.isValidField(
        binding.tilTitle,
        R.string.error_message_title
    )
            && binding.descriptionInput.isValidField(
        binding.tilDescription,
        R.string.error_message_description
    )
}