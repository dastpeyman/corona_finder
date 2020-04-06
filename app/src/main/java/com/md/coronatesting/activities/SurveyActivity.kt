package com.md.coronatesting.activities

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.md.coronatesting.R
import com.md.coronatesting.model.CustomStep
import com.quickbirdstudios.surveykit.*
import com.quickbirdstudios.surveykit.result.TaskResult
import com.quickbirdstudios.surveykit.steps.CompletionStep
import com.quickbirdstudios.surveykit.steps.InstructionStep
import com.quickbirdstudios.surveykit.steps.QuestionStep
import com.quickbirdstudios.surveykit.survey.SurveyView


class SurveyActivity : AppCompatActivity() {

    private lateinit var survey: SurveyView
    private lateinit var container: ViewGroup


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)
        survey = findViewById(R.id.survey_view)
        container = findViewById(R.id.surveyContainer)
        setupSurvey(survey)

    }

    private fun setupSurvey(surveyView: SurveyView) {

        val steps = listOf(
            InstructionStep(

                title = this.resources.getString(R.string.intro_title),
                buttonText = this.resources.getString(R.string.intro_start)

            ),
            CustomStep(),
            QuestionStep(
                title = this.resources.getString(R.string.quit_or_continue_question_text_1),
                text = this.resources.getString(R.string.how_old_text),
                nextButton = this.resources.getString(R.string.next),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.yes)),
                        TextChoice(this.resources.getString(R.string.no))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.quit_or_continue_question_text_5),
                text = this.resources.getString(R.string.how_old_text),
                nextButton = this.resources.getString(R.string.next),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.yes)),
                        TextChoice(this.resources.getString(R.string.no))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.quit_or_continue_question_text_2),
                text = this.resources.getString(R.string.how_old_text),
                nextButton = this.resources.getString(R.string.next),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.yes)),
                        TextChoice(this.resources.getString(R.string.no))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.quit_or_continue_question_text_3),
                text = this.resources.getString(R.string.how_old_text),
                nextButton = this.resources.getString(R.string.next),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.yes)),
                        TextChoice(this.resources.getString(R.string.no))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.quit_or_continue_question_text_4),
                text = this.resources.getString(R.string.how_old_text),
                nextButton = this.resources.getString(R.string.next),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.yes)),
                        TextChoice(this.resources.getString(R.string.no))
                    )
                )
            ),
            //-------------------------------------
            QuestionStep(
                title = this.resources.getString(R.string.quit_or_continue_question_text_6),
                text = this.resources.getString(R.string.how_old_text),
                nextButton = this.resources.getString(R.string.next),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.yes)),
                        TextChoice(this.resources.getString(R.string.no))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.quit_or_continue_question_text_7),
                text = this.resources.getString(R.string.how_old_text),
                nextButton = this.resources.getString(R.string.next),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.yes)),
                        TextChoice(this.resources.getString(R.string.no))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.quit_or_continue_question_text_8),
                text = this.resources.getString(R.string.how_old_text),
                nextButton = this.resources.getString(R.string.next),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.yes)),
                        TextChoice(this.resources.getString(R.string.no))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.quit_or_continue_question_text_9),
                text = this.resources.getString(R.string.how_old_text),
                nextButton = this.resources.getString(R.string.next),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.yes)),
                        TextChoice(this.resources.getString(R.string.no))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.quit_or_continue_question_text_10),
                text = this.resources.getString(R.string.how_old_text),
                nextButton = this.resources.getString(R.string.next),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.yes)),
                        TextChoice(this.resources.getString(R.string.no))
                    )
                )
            ),
            //------------------------------------------------
            QuestionStep(
                title = this.resources.getString(R.string.quit_or_continue_question_text_11),
                text = this.resources.getString(R.string.how_old_text),
                nextButton = this.resources.getString(R.string.next),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.yes)),
                        TextChoice(this.resources.getString(R.string.no))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.quit_or_continue_question_text_12),
                text = this.resources.getString(R.string.how_old_text),
                nextButton = this.resources.getString(R.string.next),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.yes)),
                        TextChoice(this.resources.getString(R.string.no))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.quit_or_continue_question_text_13),
                text = this.resources.getString(R.string.how_old_text),
                nextButton = this.resources.getString(R.string.next),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.yes)),
                        TextChoice(this.resources.getString(R.string.no))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.quit_or_continue_question_text_14),
                text = this.resources.getString(R.string.how_old_text),
                nextButton = this.resources.getString(R.string.next),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.yes)),
                        TextChoice(this.resources.getString(R.string.no))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.quit_or_continue_question_text_15),
                text = this.resources.getString(R.string.how_old_text),
                nextButton = this.resources.getString(R.string.next),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.yes)),
                        TextChoice(this.resources.getString(R.string.no))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.quit_or_continue_question_text_16),
                text = this.resources.getString(R.string.how_old_text),
                nextButton = this.resources.getString(R.string.next),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.yes)),
                        TextChoice(this.resources.getString(R.string.no))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.quit_or_continue_question_text_17),
                text = this.resources.getString(R.string.how_old_text),
                nextButton = this.resources.getString(R.string.next),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.yes)),
                        TextChoice(this.resources.getString(R.string.no))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.quit_or_continue_question_text_18),
                text = this.resources.getString(R.string.how_old_text),
                nextButton = this.resources.getString(R.string.next),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.yes)),
                        TextChoice(this.resources.getString(R.string.no))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.quit_or_continue_question_text_19),
                text = this.resources.getString(R.string.how_old_text),
                nextButton = this.resources.getString(R.string.next),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.yes)),
                        TextChoice(this.resources.getString(R.string.no))
                    )
                )
            ),
            QuestionStep(
                title = this.resources.getString(R.string.quit_or_continue_question_text_20),
                text = this.resources.getString(R.string.how_old_text),
                nextButton = this.resources.getString(R.string.next),
                answerFormat = AnswerFormat.SingleChoiceAnswerFormat(
                    textChoices = listOf(
                        TextChoice(this.resources.getString(R.string.yes)),
                        TextChoice(this.resources.getString(R.string.no))
                    )
                )
            ),


            CompletionStep(
                title = this.resources.getString(R.string.finish_question_title),
                text = this.resources.getString(R.string.finish_question_text),
                buttonText = this.resources.getString(R.string.finish_question_submit)
            )
        )

        val task = NavigableOrderedTask(steps = steps)


        task.setNavigationRule(
            steps[1].id,
            NavigationRule.ConditionalDirectionStepNavigationRule(
                resultToStepIdentifierMapper = { input ->
                    when (input) {
                        "بله" -> steps[2].id

                        "خیر" -> steps[3].id


                        else -> null
                    }

                }
            )
        )
        task.setNavigationRule(
            steps[2].id,
            NavigationRule.ConditionalDirectionStepNavigationRule(
                resultToStepIdentifierMapper = { input ->
                    when (input) {
                        "بله" -> steps[5].id

                        "خیر" -> steps[3].id


                        else -> null
                    }

                }
            )
        )

        task.setNavigationRule(
            steps[3].id,
            NavigationRule.ConditionalDirectionStepNavigationRule(
                resultToStepIdentifierMapper = { input ->
                    when (input) {
                        "بله" -> steps[5].id

                        "خیر" -> steps[4].id


                        else -> null
                    }

                }
            )
        )
        task.setNavigationRule(
            steps[4].id,
            NavigationRule.ConditionalDirectionStepNavigationRule(
                resultToStepIdentifierMapper = { input ->
                    when (input) {
                        "بله" -> steps[5].id

                        "خیر" -> steps[5].id


                        else -> null
                    }

                }
            )
        )
        task.setNavigationRule(
            steps[5].id,
            NavigationRule.ConditionalDirectionStepNavigationRule(
                resultToStepIdentifierMapper = { input ->
                    when (input) {
                        "بله" -> steps[6].id

                        "خیر" -> steps[11].id


                        else -> null
                    }

                }
            )
        )
        task.setNavigationRule(
            steps[10].id,
            NavigationRule.DirectStepNavigationRule(
                destinationStepStepIdentifier = steps[21].id
            )
        )



        surveyView.onSurveyFinish = { taskResult: TaskResult, reason: FinishReason ->
            if (reason == FinishReason.Completed) {
                var i = 0
                var count = 0
                taskResult.results.forEach { stepResult ->

                    val m = stepResult.results[0].stringIdentifier
                    if (i in 1..5)
                        if (m == "بله")
                            count++
//                    container.removeAllViews()

                    i++
                }
                if (count > 0) {
                    val intent = Intent(this, ResultActivity::class.java)
                    intent.putExtra("result", "Positive")
                    startActivityForResult(intent, 1)
                } else {
                    val intent = Intent(this, ResultActivity::class.java)
                    intent.putExtra("result", "Negative")
                    startActivityForResult(intent, 1)
                }
            }
        }

        val configuration = SurveyTheme(
            themeColorDark = ContextCompat.getColor(this, R.color.cyan_dark),
            themeColor = ContextCompat.getColor(this, R.color.cyan_normal),
            textColor = ContextCompat.getColor(this, R.color.cyan_text)

        )

        surveyView.start(task, configuration)


    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
//            survey.backPressed()
            showDialog()
            true
        } else false
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == 1) {
            finish()
        }
    }

    private fun showDialog() {
        // Initialize a new instance of
        val builder = AlertDialog.Builder(this)

        // Set the alert dialog title
        builder.setTitle("خروج")

        // Display a message on alert dialog
        builder.setMessage("آیا میخواهید از نظر سنجی خارج شوید؟")

        // Set a positive button and its click listener on alert dialog
        builder.setPositiveButton("بله") { dialog, which ->
            // Do something when user press the positive button
            finish()
            // Change the app background color
        }


        // Display a negative button on alert dialog
        builder.setNegativeButton("خیر") { dialog, which ->
            dialog.dismiss()
        }

        // Finally, make the alert dialog using builder
        val dialog: AlertDialog = builder.create()

        // Display the alert dialog on app interface
        dialog.show()
    }
}

