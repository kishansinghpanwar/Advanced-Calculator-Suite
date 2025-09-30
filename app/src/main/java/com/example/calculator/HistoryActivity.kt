package com.example.calculator

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class HistoryActivity : AppCompatActivity() {
    
    private lateinit var historyList: ListView
    private lateinit var backButton: ImageButton
    private lateinit var clearHistoryButton: Button
    
    private lateinit var historyManager: HistoryManager
    private val historyItems = mutableListOf<String>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        
        initializeViews()
        setupClickListeners()
        loadHistory()
    }
    
    private fun initializeViews() {
        historyList = findViewById(R.id.historyList)
        backButton = findViewById(R.id.backButton)
        clearHistoryButton = findViewById(R.id.clearHistoryButton)
        historyManager = HistoryManager(this)
    }
    
    private fun setupClickListeners() {
        backButton.setOnClickListener {
            finish()
        }
        
        clearHistoryButton.setOnClickListener {
            clearHistory()
        }
        
        historyList.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val selectedItem = historyItems[position]
            // You can implement functionality to reuse the calculation here
            Toast.makeText(this, "Selected: $selectedItem", Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun loadHistory() {
        // Load actual history from SharedPreferences
        val savedHistory = historyManager.getHistory()
        
        if (savedHistory.isEmpty()) {
            // Show sample data if no history exists
            historyItems.addAll(listOf(
                "2 + 3 = 5",
                "10 × 5 = 50",
                "100 ÷ 4 = 25",
                "√16 = 4",
                "sin(30°) = 0.5",
                "2³ = 8",
                "log(100) = 2",
                "5! = 120"
            ))
        } else {
            historyItems.addAll(savedHistory)
        }
        
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, historyItems)
        historyList.adapter = adapter
    }
    
    private fun clearHistory() {
        historyManager.clearHistory()
        historyItems.clear()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, historyItems)
        historyList.adapter = adapter
        Toast.makeText(this, "History cleared", Toast.LENGTH_SHORT).show()
    }
}
