package com.example.uconeandroid.utils

import android.content.Context
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.uconeandroid.R


fun Context.showDialog(success: Boolean) {

    val builder = AlertDialog.Builder(this)

    val inflater = LayoutInflater.from(this)
    val dialogView = inflater.inflate(R.layout.dialog, null)

    builder.setView(dialogView)

    val dialog = builder.create()

    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

    val changeIcon = if (success) R.drawable.check else R.drawable.error
    dialogView.findViewById<ImageView>(R.id.dialogImage).setImageResource(changeIcon)

    val changeTitle = if (success) "Congratulation 🎉" else "Sorry!"
    dialogView.findViewById<TextView>(R.id.dialogTitle).text = changeTitle

    val changeDesc = if (success) "The product is added successfully 🎉" else "Product addition failed"
    dialogView.findViewById<TextView>(R.id.dialogDesc).text = changeDesc

    dialogView.findViewById<TextView>(R.id.dialogOkButton).setOnClickListener {
        dialog.dismiss()
    }

    dialog.show()
}