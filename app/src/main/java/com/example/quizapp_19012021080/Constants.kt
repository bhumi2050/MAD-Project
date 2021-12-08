package com.example.quizapp_19012021080

object Constants {
    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_question"
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun getQuestions(): ArrayList<Questions>{
        val questionList = java.util.ArrayList<Questions>()

        //1
        val que1 = Questions(
            1, "What is the name of this aircraft?",
            R.drawable.ic_f16_1,
            "F-16", "Rafale",
            "F-22", "Mirage 2000",
            1

        )
        questionList.add(que1)

        //2
        val que2 = Questions(
            1, "What is the name of this aircraft?",
            R.drawable.ic_mig21_2,
            "Mig-29", "Mig-21",
            "Mil Mi-17", "Mil Mi-35",
            2

        )
        questionList.add(que2)

        //3
        val que3 = Questions(
            1, "What is the name of this aircraft?",
            R.drawable.ic_rafale_3,
            "LCA Tejas", "Mirage 2000",
            "SU-30 MKI", "Dassault Rafale",
            4

        )
        questionList.add(que3)

        //4
        val que4 = Questions(
            1, "What is the name of this aircraft?",
            R.drawable.ic_chinook_4,
            "Apache AH-64E", "Chinook CH-47",
            "HAL Rudra", "Mil Mi-17",
            2

        )
        questionList.add(que4)

        //5
        val que5 = Questions(
            1, "What is the name of this aircraft?",
            R.drawable.ic_c130j_5,
            "Antonov AN-32", "C-130J Hercules",
            "C-17 Globemaster", "Dornier Do-228",
            2

        )
        questionList.add(que5)

        //6
        val que6 = Questions(
            1, "What is the name of this aircraft?",
            R.drawable.ic_apache_6,
            "HAL LCA", "HAL Rudra",
            "Apache AH-64E", "Chinnok CH-47",
            3

        )
        questionList.add(que6)

        //7
        val que7 = Questions(
            1, "What is the name of this aircraft?",
            R.drawable.ic_mi26_7,
            "Mil Mi-26", "Mil Mi-17",
            "Mil Mi-17 V5", "Mil Mi-8",
            1

        )
        questionList.add(que7)

        //8
        val que8 = Questions(
            1, "What is the name of this aircraft?",
            R.drawable.ic_tejas_8,
            "LCA Tejas", "Mirage 2000",
            "Rafale", "Mig-29",
            1

        )
        questionList.add(que8)

        //9
        val que9 = Questions(
            1, "What is the name of this aircraft?",
            R.drawable.ic_avro_9,
            "AN-32", "IL-76",
            "Boeing-707", "HS Avro",
            4

        )
        questionList.add(que9)

        //10
        val que10 = Questions(
            1, "What is the name of this aircraft?",
            R.drawable.ic_netraawacs_10,
            "Do-228 AWACS", "IL-76 AWACS",
            "Netra AWACS", "Airbus C295",
            3

        )
        questionList.add(que10)

        return questionList

    }
}