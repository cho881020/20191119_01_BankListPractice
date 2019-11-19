package com.tjoeun.a20191119_01_banklistpractice.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.tjoeun.a20191119_01_banklistpractice.R
import com.tjoeun.a20191119_01_banklistpractice.datas.Bank
import com.tjoeun.a20191119_01_banklistpractice.datas.DeliveryCompany

class DeliveryCompanyAdapter(context:Context, res:Int, list:ArrayList<DeliveryCompany>)
    : ArrayAdapter<DeliveryCompany> (context, res, list) {

    var mContext = context
    var mList = list
    var inf = LayoutInflater.from(mContext)

    constructor(context: Context, list: ArrayList<DeliveryCompany>) : this(context, R.layout.delivery_company_list_item, list)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView

        if (tempRow == null) {
            tempRow = inf.inflate(R.layout.delivery_company_list_item, null)
        }

        var row = tempRow!!

        var deliveryCompanyLogoImg = row.findViewById<ImageView>(R.id.deliveryCompanyLogoImg)
        var deliveryCompanyNameTxt = row.findViewById<TextView>(R.id.deliveryCompanyNameTxt)

        var data = mList.get(position)

        Glide.with(mContext).load(data.logo).into(deliveryCompanyLogoImg)
        deliveryCompanyNameTxt.text = data.name

        return row

    }


}