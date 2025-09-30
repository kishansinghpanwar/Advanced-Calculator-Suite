package com.example.calculator

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class CalculatorGridAdapter(
    private val context: Context,
    private val calculatorTypes: List<CalculatorType>,
    private val onItemClick: (CalculatorType) -> Unit
) : BaseAdapter() {

    override fun getCount(): Int = calculatorTypes.size

    override fun getItem(position: Int): CalculatorType = calculatorTypes[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_calculator_type, parent, false)
        
        val calculatorType = getItem(position)
        
        val iconView = view.findViewById<ImageView>(R.id.calculatorIcon)
        val nameView = view.findViewById<TextView>(R.id.calculatorName)
        val descView = view.findViewById<TextView>(R.id.calculatorDescription)
        
        iconView.setImageResource(calculatorType.icon)
        nameView.text = calculatorType.name
        descView.text = calculatorType.description
        
        view.setOnClickListener {
            onItemClick(calculatorType)
        }
        
        return view
    }
}

