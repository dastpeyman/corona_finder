package com.mohsen.coronafinder.model

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import com.mohsen.coronafinder.R
import com.quickbirdstudios.surveykit.StepIdentifier
import com.quickbirdstudios.surveykit.SurveyTheme
import com.quickbirdstudios.surveykit.backend.views.step.StepView
import com.quickbirdstudios.surveykit.result.QuestionResult
import com.quickbirdstudios.surveykit.result.StepResult
import com.quickbirdstudios.surveykit.steps.Step
import kotlinx.android.synthetic.main.custom_step.view.*
import java.util.*
import kotlin.math.pow
import kotlin.math.round

class CustomStep : Step {
    private var h=0.0
    private var w=0.0
    private var indicator=0.0
    override val isOptional: Boolean = true
    override val id: StepIdentifier =
        StepIdentifier()
    val tmp = id

    override fun createView(context: Context, stepResult: StepResult?): StepView {
        return object : StepView(context, id, isOptional) {

            override fun setupViews() = Unit

            val root = View.inflate(context, R.layout.custom_step, this)


            override fun createResults(): QuestionResult =
                CustomResult(
                    "",
                    "stringIdentifier",
                    id,
                    Date(),
                    Date()
                )

            override fun isValidInput(): Boolean = this@CustomStep.isOptional

            override var isOptional: Boolean = this@CustomStep.isOptional
            override val id: StepIdentifier = tmp

            override fun style(surveyTheme: SurveyTheme) {
                // do styling here
            }

            init {
                np_old.minValue=1
                np_old.maxValue=150
                np_old.value=20


                np_height.minValue=1
                np_height.maxValue=250
                np_height.value=170
                h= np_height.value.toDouble()

                np_weight.minValue=1
                np_weight.maxValue=200
                np_weight.value=58
                w= np_weight.value.toDouble()
                indicator= 20.0
                txtIndicator.text = "شاخص توده بدنی : $indicator وزن مطلوب"

                root.findViewById<Button>(R.id.continue_button)
                    .setOnClickListener {
                        onNextListener(createResults()) }
                root.findViewById<RelativeLayout>(R.id.headerBackButton)
                    .setOnClickListener { onBackListener(createResults()) }

                np_height.setOnValueChangedListener { picker, oldVal, newVal ->
                    h = newVal.toDouble()
                    w = np_weight.value.toDouble()
                    val temp = (h / 100).pow(2.0)
                    indicator = round(w/temp)
                    when {
                        indicator<19 -> txtIndicator.text = "شاخص توده بدنی : $indicator کمبود وزن"
                        indicator in 19..25 -> txtIndicator.text = "شاخص توده بدنی : $indicator وزن مطلوب"
                        indicator>25 -> txtIndicator.text = "شاخص توده بدنی : $indicator اضافه وزن"
                    }
                }
                np_weight.setOnValueChangedListener { picker, oldVal, newVal ->
                    w = newVal.toDouble()
                    h= np_height.value.toDouble()
                    val temp = (h / 100).pow(2.0)
                    indicator = round(w/temp)
                    when {
                        indicator<19 -> txtIndicator.text = "شاخص توده بدنی : $indicator کمبود وزن"
                        indicator in 19..25 -> txtIndicator.text = "شاخص توده بدنی : $indicator وزن مطلوب"
                        indicator>25 -> txtIndicator.text = "شاخص توده بدنی : $indicator اضافه وزن"
                    }
                }
            }
        }
    }
}
