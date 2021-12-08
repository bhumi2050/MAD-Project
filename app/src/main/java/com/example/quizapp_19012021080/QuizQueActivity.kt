package com.example.quizapp_19012021080

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.core.content.ContextCompat




class QuizQueActivity : AppCompatActivity(), View.OnClickListener {
    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Questions>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswers: Int = 0
    private var mUserName: String? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_que)
        setStatusBarTransparent()
        supportActionBar?.hide()

        mUserName = intent.getStringExtra(Constants.USER_NAME)


        val tvoptionone = findViewById<TextView>(R.id.tv_option_one)
        val tvoptiontwo = findViewById<TextView>(R.id.tv_option_two)
        val tvoptionthree = findViewById<TextView>(R.id.tv_option_three)
        val tvoptionfour = findViewById<TextView>(R.id.tv_option_four)
        val btnsubmit = findViewById<Button>(R.id.btn_submit)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val tvprogress = findViewById<TextView>(R.id.tv_progress)
        val tvquestion = findViewById<TextView>(R.id.tv_question)
        val ivimage = findViewById<ImageView>(R.id.iv_image)

        mQuestionsList = Constants.getQuestions()
        setQuestion()

        tvoptionone.setOnClickListener(this)
        tvoptiontwo.setOnClickListener(this)
        tvoptionthree.setOnClickListener(this)
        tvoptionfour.setOnClickListener(this)
        btnsubmit.setOnClickListener(this)


        val questionsList = Constants.getQuestions()
        Log.i("Questions Size", "${questionsList.size}")
        for (i in questionsList) {
            Log.e("Questions", i.question)
        }

        val currentPosition = 1 // Default and the first question position
        val question: Questions? = questionsList[currentPosition - 1] // Getting the question from the list with the help of current position.



        progressBar.progress = currentPosition // Setting the current progress in the progressbar using the position of question
        tvprogress.text = "$currentPosition" + "/" + progressBar.getMax() // Setting up the progress text

        tvquestion.text = question!!.question
        ivimage.setImageResource(question.image)
        tvoptionone.text = question.optionOne
        tvoptiontwo.text = question.optionTwo
        tvoptionthree.text = question.optionThree
        tvoptionfour.text = question.optionFour
    }

    private fun setQuestion() {
        val tvoptionone = findViewById<TextView>(R.id.tv_option_one)
        val tvoptiontwo = findViewById<TextView>(R.id.tv_option_two)
        val tvoptionthree = findViewById<TextView>(R.id.tv_option_three)
        val tvoptionfour = findViewById<TextView>(R.id.tv_option_four)
        val btnsubmit = findViewById<Button>(R.id.btn_submit)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val tvprogress = findViewById<TextView>(R.id.tv_progress)
        val tvquestion = findViewById<TextView>(R.id.tv_question)
        val ivimage = findViewById<ImageView>(R.id.iv_image)

        val question = mQuestionsList!!.get(mCurrentPosition - 1) // Getting the question from the list with the help of current position.
        defaultOptionsView()
        if(mCurrentPosition == mQuestionsList!!.size){
            btnsubmit.text = "FINISH"
        }else
            btnsubmit.text = "SUBMIT"

        progressBar.progress = mCurrentPosition
        tvprogress.text = "$mCurrentPosition" + "/" + progressBar.getMax()

        tvquestion.text = question.question
        ivimage.setImageResource(question.image)
        tvoptionone.text = question.optionOne
        tvoptiontwo.text = question.optionTwo
        tvoptionthree.text = question.optionThree
        tvoptionfour.text = question.optionFour
    }

    private fun defaultOptionsView() {
        val tvoptionone = findViewById<TextView>(R.id.tv_option_one)
        val tvoptiontwo = findViewById<TextView>(R.id.tv_option_two)
        val tvoptionthree = findViewById<TextView>(R.id.tv_option_three)
        val tvoptionfour = findViewById<TextView>(R.id.tv_option_four)


        val options = ArrayList<TextView>()
        options.add(0, tvoptionone)
        options.add(1, tvoptiontwo)
        options.add(2, tvoptionthree)
        options.add(3, tvoptionfour)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this@QuizQueActivity,
                R.drawable.default_option_border_bg
            )
        }
    }

    override fun onClick(p0: View?) {
        val tvoptionone = findViewById<TextView>(R.id.tv_option_one)
        val tvoptiontwo = findViewById<TextView>(R.id.tv_option_two)
        val tvoptionthree = findViewById<TextView>(R.id.tv_option_three)
        val tvoptionfour = findViewById<TextView>(R.id.tv_option_four)
        val btnsubmit = findViewById<Button>(R.id.btn_submit)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val tvprogress = findViewById<TextView>(R.id.tv_progress)
        val tvquestion = findViewById<TextView>(R.id.tv_question)
        val ivimage = findViewById<ImageView>(R.id.iv_image)

        when(p0?.id){
            R.id.tv_option_one ->{
                selectedOptionView(tvoptionone,1)
            }
            R.id.tv_option_two ->{
                selectedOptionView(tvoptiontwo,2)
            }
            R.id.tv_option_three ->{
                selectedOptionView(tvoptionthree,3)
            }
            R.id.tv_option_four->{
                selectedOptionView(tvoptionfour,4)
            }
            R.id.btn_submit ->{
                if(mSelectedOptionPosition == 0){
                    mCurrentPosition++

                    when{
                        mCurrentPosition <= mQuestionsList!!.size ->{
                            setQuestion()
                        }else ->{
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList!!.size)
                            startActivity(intent)

                    }
                    }
                }else{
                    val question = mQuestionsList?.get(mCurrentPosition -1)
                    if(question!!.correctAnswer != mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }else{
                        mCorrectAnswers++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if(mCurrentPosition == mQuestionsList!!.size){
                        btnsubmit.text = "FINISH"
                    }else{
                        btnsubmit.text = "GO TO NEXT QUESTION!"
                    }
                    mSelectedOptionPosition = 0

                }
            }
        }

    }
    private fun answerView(answer: Int, drawableView: Int){
        val tvoptionone = findViewById<TextView>(R.id.tv_option_one)
        val tvoptiontwo = findViewById<TextView>(R.id.tv_option_two)
        val tvoptionthree = findViewById<TextView>(R.id.tv_option_three)
        val tvoptionfour = findViewById<TextView>(R.id.tv_option_four)
        val btnsubmit = findViewById<Button>(R.id.btn_submit)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val tvprogress = findViewById<TextView>(R.id.tv_progress)
        val tvquestion = findViewById<TextView>(R.id.tv_question)
        val ivimage = findViewById<ImageView>(R.id.iv_image)

        when(answer){
            1->{
                tvoptionone.background = ContextCompat.getDrawable(
                    this,drawableView
                )
            }
            2->{
                tvoptiontwo.background = ContextCompat.getDrawable(
                    this,drawableView
                )
            }
            3->{
                tvoptionthree.background = ContextCompat.getDrawable(
                    this,drawableView
                )
            }
            4->{
                tvoptionfour.background = ContextCompat.getDrawable(
                    this,drawableView
                )
            }
        }
    }
    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {

        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(
            Color.parseColor("#363A43")
        )
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this@QuizQueActivity,
            R.drawable.selected_option_border_bg
        )
    }

    private fun setStatusBarTransparent() {
        if (Build.VERSION.SDK_INT in 19..20) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
            }
        }
        if (Build.VERSION.SDK_INT >= 19) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    private fun setWindowFlag(bits: Int, on: Boolean){
        val winParameters=window.attributes
        if(on) {
            winParameters.flags = winParameters.flags or bits
        }else{
            winParameters.flags=winParameters.flags and bits.inv()
        }
        window.attributes=winParameters
    }


}