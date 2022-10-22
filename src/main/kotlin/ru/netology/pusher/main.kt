package ru.netology.pusher

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import java.io.FileInputStream

fun main() {

    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(FileInputStream("fcm.json")))
        .build()

    val token =
        "cBxZh32wQtusJHz-QYV7n2:APA91bExwpOJaGUJGjtszJgUqJttcGDmNYSQaUbPVmmJTYPuLjP_RKnEykF8ABTOGTlZ5YL-dUodXgjZyrYFVQJFFO23dgKJTZHnXzx4pQtpKR9sJ8lm2N2IlVPMoxGYtpH5Zbs-mWwH"
    val topic = "После смерти Елизаветы II надзорные власти Великобритании подняли вопрос о реальной стоимости ее активов."
    val text = "Министерство бизнеса, энергетики и промышленной стратегии могут обязать впервые раскрыть инвестиционный доход скончавшейся королевы. Этому способствовало расследование журналистов The Guardian. Они пришли к выводу, что Елизавета могла скрыть часть своих доходов, полученных от инвестиций. Также, по мнению журналистов, она пыталась добиться, чтобы в законодательство внесли поправки, которые позволили скрыть размер ее состояния. Наследство Елизаветы перейдет к ее сыну королю Карлу III и другим членам семьи. Завещание покойной засекречено минимум на 90 лет. В 2021 году Forbes оценил стоимость активов королевы почти в \$500 млн.\n"

    FirebaseApp.initializeApp(options)

    val message = Message.builder()
        .putData("action", "LIKE")
        .putData(
            "content", """{
          "userId": 1,
          "userName": "Vasiliy",
          "postId": 2,
          "postAuthor": "Netology"
        }""".trimIndent()
        )
        .setToken(token)
        .build()

    val newPostMessage = Message.builder()
        .putData("action", "NEW_POST")
        .putData(
            "content", """{
         "postAuthor": "Vasiliy",
         "postText": "$text",
         "postTopic": "$topic"
          }""".trimIndent()
        )
        .setToken(token)
        .build()

    FirebaseMessaging.getInstance().send(newPostMessage)
}