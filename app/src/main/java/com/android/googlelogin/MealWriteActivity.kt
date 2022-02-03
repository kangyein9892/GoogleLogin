package com.android.googlelogin

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*

class MealWriteActivity : AppCompatActivity() {
    lateinit var MealdbManager: MealDBManager
    lateinit var sqlitedb: SQLiteDatabase

    lateinit var calWriteButton: Button
    lateinit var calendarWrite: CalendarView
    lateinit var timeWrite: TimePicker
    lateinit var timeWriteButton: Button
    lateinit var tvCalWrite: TextView
    lateinit var tvTimeWrite: TextView
    lateinit var calBackButton: Button
    lateinit var editTextFood: EditText
    lateinit var editTextKcal: EditText
    lateinit var writeButton: Button
    lateinit var editTextTitle: EditText

    var selectYear: Int = 0
    var selectMonth: Int = 0
    var selectDate: Int = 0
    var selectHour: Int = 0
    var selectMinute: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_write)

        MealdbManager = MealDBManager(this, "mealDB", null, 1)

        calWriteButton = findViewById(R.id.calWriteButton)
        calendarWrite = findViewById(R.id.calendarWrite)
        timeWrite = findViewById(R.id.timeWrite)
        timeWriteButton = findViewById(R.id.timeWriteButton)
        tvCalWrite = findViewById(R.id.tvCalCheck)
        tvTimeWrite = findViewById(R.id.tvTimeWrite)
        calBackButton = findViewById(R.id.calBackButton)
        editTextFood = findViewById(R.id.editTextFood)
        editTextKcal = findViewById(R.id.editTextKcal)
        writeButton = findViewById(R.id.writeButton)
        editTextTitle = findViewById(R.id.editTextTitle)

        calendarWrite.setOnDateChangeListener { view, year, month, date ->
            selectYear = year
            selectMonth = month + 1
            selectDate = date
            calWriteButton.setOnClickListener {
                if (selectMonth < 10) {
                    if (date < 10) {
                        tvCalWrite.text =
                                selectYear.toString() + "년" + " " + "0" + selectMonth.toString() + "월" + " " + "0" + selectDate.toString() + "일"
                    } else {
                        tvCalWrite.text =
                                selectYear.toString() + "년" + " " + "0" + selectMonth.toString() + "월" + " " + selectDate.toString() + "일"
                    }
                } else {
                    if (date < 10) {
                        tvCalWrite.text =
                                selectYear.toString() + "년" + " " + selectMonth.toString() + "월" + " " + "0" + selectDate.toString() + "일"
                    } else {
                        tvCalWrite.text =
                                selectYear.toString() + "년" + " " + selectMonth.toString() + "월" + " " + selectDate.toString() + "일"
                    }
                }
                calendarWrite.visibility = View.INVISIBLE
                calWriteButton.visibility = View.INVISIBLE
                timeWriteButton.visibility = View.VISIBLE
                timeWrite.visibility = View.VISIBLE
                calBackButton.visibility = View.VISIBLE
                tvCalWrite.visibility = View.VISIBLE
            }
            timeWrite.setOnTimeChangedListener { picker, hour, minute ->
                selectHour = hour
                selectMinute = minute
                timeWriteButton.setOnClickListener {
                    if (selectHour < 10) {
                        if (selectMinute < 10) {
                            tvTimeWrite.text =
                                    "0" + selectHour.toString() + "시" + " " + "0" + selectMinute.toString() + "분"
                        } else {
                            tvTimeWrite.text =
                                    "0" + selectHour.toString() + "시" + " " + selectMinute.toString() + "분"
                        }
                    } else {
                        if (selectMinute < 10) {
                            tvTimeWrite.text =
                                    selectHour.toString() + "시" + " " + "0" + selectMinute.toString() + "분"
                        } else {
                            tvTimeWrite.text =
                                    selectHour.toString() + "시" + " " + selectMinute.toString() + "분"
                        }
                    }
                    tvCalWrite.visibility = View.VISIBLE
                    tvTimeWrite.visibility = View.VISIBLE
                    editTextTitle.visibility = View.VISIBLE
                    editTextFood.visibility = View.VISIBLE
                    editTextKcal.visibility = View.VISIBLE
                    writeButton.visibility = View.VISIBLE
                }
            }
            writeButton.setOnClickListener {
                var str_year: String = selectYear.toString()
                var str_month: String = selectMonth.toString()
                var str_date: String = selectDate.toString()
                var str_hour: String = selectHour.toString()
                var str_minute: String = selectMinute.toString()
                var str_title: String = editTextTitle.text.toString()
                var str_food: String = editTextFood.text.toString()
                var str_kcal:String = editTextKcal.text.toString()

                sqlitedb = MealdbManager.writableDatabase
                sqlitedb.execSQL("INSERT INTO meal2 VALUES('"+str_title+"', " +str_year+", " +str_month+", " +str_date+", " +str_hour+", " +str_minute+", '" +str_food+"', " +str_kcal+")")
                sqlitedb.close()

                Toast.makeText(applicationContext, "입력 완료되었습니다.", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MealChoiceActivity::class.java)
                startActivity(intent)
            }
        }

        calBackButton.setOnClickListener {
            calendarWrite.visibility = View.VISIBLE
            calWriteButton.visibility = View.VISIBLE
            timeWriteButton.visibility = View.INVISIBLE
            timeWrite.visibility = View.INVISIBLE
            tvCalWrite.visibility = View.INVISIBLE
            tvTimeWrite.visibility = View.INVISIBLE
            calBackButton.visibility = View.INVISIBLE
            editTextTitle.visibility = View.INVISIBLE
            editTextFood.visibility = View.INVISIBLE
            editTextKcal.visibility = View.INVISIBLE
            writeButton.visibility = View.INVISIBLE

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.main -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.sport -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.water -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.mental -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.meal -> {
                val intent = Intent(this, MealChoiceActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}