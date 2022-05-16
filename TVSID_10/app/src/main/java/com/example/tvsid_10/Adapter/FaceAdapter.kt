package com.example.tvsid_10.Adapter

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.tvsid_10.R

class FaceAdapter: RecyclerView.Adapter<FaceAdapter.ViewHolder> {
    private var faceList: ArrayList<Bitmap>
    private var context: Context

    constructor(faceList: ArrayList<Bitmap>, context: Context) : super() {
        this.faceList = faceList
        this.context = context
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgFace: ImageView = itemView!!.findViewById(R.id.img_face)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_face, parent, false)
        val viewHolder = ViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bitmap = faceList[position]
        holder.imgFace.setImageBitmap(bitmap)
    }

    override fun getItemCount(): Int {
        return faceList.size
    }
}