package kg.sunrise.sabs.ui.still.setting
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kg.sunrise.sabs.R
import kotlinx.android.synthetic.main.activity_edit_restaurant.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream


class EditRestaurantActivity : AppCompatActivity() {
    var uri: Uri? = null
    var fileHome: File? = null
    private val CAMERA_REQUEST = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme2)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_restaurant)
        init()
    }
    private fun init() {
        constraint_add_image.setOnClickListener(View.OnClickListener {
            image()
        })
        camera.setOnClickListener(View.OnClickListener {
            image()
        })
    }
    private fun image(){
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_PICK
        startActivityForResult(intent, 100)
        image_restaurant.setImageURI(uri)
    }
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 100) {
            assert(data != null)
            uri = data!!.data
            val imageStream: InputStream?
            try {
                imageStream = contentResolver.openInputStream(uri!!)
                val selectedImage = BitmapFactory.decodeStream(imageStream)
                val uri1: Uri? = getImageUri(this, selectedImage)
                val path: String? = getRealPathFromURI(this, uri1)
                fileHome = null
                fileHome = File(path)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
            image_restaurant.setImageURI(uri)
        } else if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            // Фотка сделана, извлекаем картинку
            assert(data != null)
            val thumbnailBitmap = data!!.extras!!["data"] as Bitmap?
            image_restaurant.setImageBitmap(thumbnailBitmap)
        }
    }
    private fun getImageUri(inContext: Context, inImage: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(
            inContext.contentResolver,
            inImage,
            "Title",
            null
        )
        return Uri.parse(path)
    }

    private fun getRealPathFromURI(
        context: Context,
        contentUri: Uri?
    ): String? {
        var cursor: Cursor? = null
        return try {
            val proj =
                arrayOf(MediaStore.Images.Media.DATA)
            cursor = context.contentResolver.query(contentUri!!, proj, null, null, null)
            val column_index = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            cursor.getString(column_index)
        } finally {
            cursor?.close()
        }
    }


//    fun onAlert(view: View?) {
//        val builder = AlertDialog.Builder(this)
//        val alert = builder.show()
//        val frameView = FrameLayout(this)
//        val alertDialog = builder.create()
//        val inflater = alertDialog.layoutInflater
//        val dialoglayout: View = inflater.inflate(R.layout.custom_alert, frameView)
//        alertDialog.setView(dialoglayout)
//        val visa = dialoglayout.findViewById<ImageView>(R.id.visa)
//        visa.setOnClickListener {
//        }
//        val image = dialoglayout.findViewById<ImageView>(R.id.pay24)
//        image.setOnClickListener {
//
//        }
//        alertDialog.setCancelable(true)
//        alertDialog.setOnCancelListener { finish() }
//        alertDialog.show()
//    }
}