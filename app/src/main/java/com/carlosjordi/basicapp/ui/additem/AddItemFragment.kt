package com.carlosjordi.basicapp.ui.additem

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.carlosjordi.basicapp.databinding.FragmentAddItemBinding
import com.carlosjordi.basicapp.utils.ITEM_LIST
import com.carlosjordi.basicapp.entity.Item
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

const val REQUEST_TAKE_PHOTO = 1
const val AUTHORITY = "com.carlosjordi.basicapp"

class AddItemFragment : Fragment() {

    lateinit var binding: FragmentAddItemBinding
    private lateinit var currentPhotoPath: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddItemBinding.inflate(inflater, container, false)

        // lanzar la camara cuando se le de a la imagen
        binding.itemImage.setOnClickListener { dispatchTakePictureIntent() }

        binding.addButton.setOnClickListener {
            addItem()
            galleryAddPic()
            navigateToMainScreen()
        }

        return binding.root
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // chekamos que exista almenos una app que pueda encargarse de nuestro Intent
            takePictureIntent.resolveActivity(requireActivity().packageManager)?.also { _ ->
                val photoFile = try {
                    createImageFile()
                } catch (e: IOException) {
                    null
                }
                // continuamos solo si photoFile no es null
                photoFile?.also { file ->
                    val photoURI = FileProvider.getUriForFile(
                        requireActivity().applicationContext,
                        AUTHORITY,
                        file
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            setPic()
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir = activity?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        ).apply {
            currentPhotoPath = absolutePath
        }
    }

    @Suppress("DEPRECATION")
    private fun galleryAddPic() {
        Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).also {
            val file = File(currentPhotoPath)
            it.data = Uri.fromFile(file)
            activity?.sendBroadcast(it)
        }
    }

    private fun setPic() {
        // Obtenemos las dimensiones del ImageView
        val targetW: Int = binding.itemImage.width
        val targetH: Int = binding.itemImage.height

        val bmOptions = BitmapFactory.Options().apply {
            // Obtenemos las dimensiones del bitmap
            inJustDecodeBounds = true

            val photoW: Int = outWidth
            val photoH: Int = outHeight

            // Para escalar la imagen
            val scaleFactor: Int = Math.min(photoW / targetW, photoH / targetH)

            // Decode the image file into a Bitmap sized to fill the View
            inJustDecodeBounds = false
            inSampleSize = scaleFactor
            inPurgeable = true
        }
        BitmapFactory.decodeFile(currentPhotoPath, bmOptions)?.also { bitmap ->
            binding.itemImage.setImageBitmap(bitmap)
        }
    }

    private fun addItem() {
        val id = ITEM_LIST[ITEM_LIST.size - 1].id + 1
        val title = binding.titleInput.text.toString()
        val description = binding.descriptionInput.text.toString()
        val image = (binding.itemImage.drawable as BitmapDrawable).bitmap
        val item = Item(id, title, description, null, image)
        ITEM_LIST.add(item)
    }

    private fun navigateToMainScreen() {
        findNavController()
            .navigate(AddItemFragmentDirections.actionAddItemFragmentToItemListFragment())
    }
}
