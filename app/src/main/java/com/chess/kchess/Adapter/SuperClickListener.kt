package com.chess.kchess.Adapter

import android.widget.TextView

interface SuperClickListener
{

    fun onClickItem(position:Int)

    fun callOnView(position:Int,rank:TextView,file:TextView){

    }

}