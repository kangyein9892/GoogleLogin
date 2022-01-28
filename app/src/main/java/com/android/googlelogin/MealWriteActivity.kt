package com.android.googlelogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*

class MealWriteActivity : AppCompatActivity() {

    lateinit var calWriteButton: Button
    lateinit var calendarWrite: CalendarView
    lateinit var timeWrite: TimePicker
    lateinit var timeWriteButton: Button
    lateinit var tvCalWrite: TextView
    lateinit var tvTimeWrite: TextView
    lateinit var calBackButton: Button
    lateinit var editImage: ImageView
    lateinit var editTextFood: EditText
    lateinit var editTextKcal: EditText
    lateinit var writeButton: Button

    var selectYear: Int = 0
    var selectMonth: Int = 0
    var selectDate: Int = 0
    var selectHour: Int = 0
    var selectMinute: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_write)

        calWriteButton = findViewById(R.id.calWriteButton)
        calendarWrite = findViewById(R.id.calendarWrite)
        timeWrite = findViewById(R.id.timeWrite)
        timeWriteButton = findViewById(R.id.timeWriteButton)
        tvCalWrite = findViewById(R.id.tvCalWrite)
        tvTimeWrite= findViewById(R.id.tvTimeWrite)
        calBackButton = findViewById(R.id.calBackButton)
        editImage = findViewById(R.id.editimage)
        editTextFood = findViewById(R.id.editTextFood)
        editTextKcal = findViewById(R.id.editTextKcal)
        writeButton = findViewById(R.id.writeButton)

        calendarWrite.setOnDateChangeListener { view, year, month, date ->
            selectYear = year
            selectMonth = month + 1
            selectDate = date
            calWriteButton.setOnClickListener {
                if(selectMonth < 10) {
                    if (date < 10) {
                        tvCalWrite.text = selectYear.toString() + "년" + " " + "0" + selectMonth.toString() + "월" + " " + "0" + selectDate.toString() + "일"
                    }
                    else {
                        tvCalWrite.text =  selectYear.toString() + "년" + " " + "0" + selectMonth.toString() + "월" + " " + selectDate.toString() + "일"
                    }
                }
                else {
                    if (date < 10) {
                        tvCalWrite.text =  selectYear.toString() + "년" + " " + selectMonth.toString() + "월" + " " + "0" + selectDate.toString() + "일"
                    }
                    else {
                        tvCalWrite.text =  selectYear.toString() + "년" + " " + selectMonth.toString() + "월" + " " + selectDate.toString() + "일"
                    }
                }
                calendarWrite.visibility = View.INVISIBLE
                calWriteButton.visibility = View.INVISIBLE
                timeWriteButton.visibility = View.VISIBLE
                timeWrite.visibility = View.VISIBLE
                calBackButton.visibility = View.VISIBLE
            }
        }

        calBackButton.setOnClickListener {
            calendarWrite.visibility = View.VISIBLE
            calWriteButton.visibility = View.VISIBLE
            timeWriteButton.visibility = View.INVISIBLE
            timeWrite.visibility = View.INVISIBLE
            calBackButton.visibility = View.INVISIBLE
            editImage.visibility = View. INVISIBLE
            editTextFood.visibility = View.INVISIBLE
            editTextKcal.visibility = View.INVISIBLE
            writeButton.visibility = View.INVISIBLE

        }

        timeWrite.setOnTimeChangedListener { picker, hour, minute ->
            selectHour = hour
            selectMinute = minute
            timeWriteButton.setOnClickListener {
                if(selectHour < 10) {
                    if (selectMinute < 10) {
                        tvTimeWrite.text = "0" + selectHour.toString() + "시" + " " + "0" + selectMinute.toString() + "분"
                    }
                    else {
                        tvTimeWrite.text = "0" + selectHour.toString() + "시" + " " + selectMinute.toString() + "분"
                    }
                }
                else {
                    if(selectMinute < 10) {
                        tvTimeWrite.text = selectHour.toString() + "시" + " " + "0" + selectMinute.toString() + "분"
                    }
                    else {
                        tvTimeWrite.text = selectHour.toString() + "시" + " " + selectMinute.toString() + "분"
                    }
                }
                tvCalWrite.visibility = View.VISIBLE
                tvTimeWrite.visibility = View.VISIBLE
                editImage.visibility = View. VISIBLE
                editTextFood.visibility = View.VISIBLE
                editTextKcal.visibility = View.VISIBLE
                writeButton.visibility = View.VISIBLE
            }
        }

        writeButton.setOnClickListener {
            Toast.makeText(applicationContext, "입력 완료되었습니다.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MealCheckActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_meal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId) {
            R.id.main -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.sport -> {
                val intent = Intent(this, SportActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.water -> {
                val intent = Intent(this, WaterActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.mental -> {
                val intent = Intent(this, MentalActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.meal -> {
                val intent = Intent(this, MealCheckActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}