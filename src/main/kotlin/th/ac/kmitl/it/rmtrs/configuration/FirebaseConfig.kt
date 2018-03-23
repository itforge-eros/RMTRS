package th.ac.kmitl.it.rmtrs.configuration

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource

@Configuration
class FirebaseConfig {

    init {
        val serviceAccount = ClassPathResource("try-firebase-41a7b-firebase-adminsdk-1h6hj-c899fda03f.json").inputStream
        val option = FirebaseOptions.Builder().apply {
            setCredentials(GoogleCredentials.fromStream(serviceAccount))
            setDatabaseUrl("https://try-firebase-41a7b.firebaseio.com/")
        }.build()
        FirebaseApp.initializeApp(option)
    }

    @Bean
    fun screeningRef(): DatabaseReference {
        val db = FirebaseDatabase.getInstance().getReference("screenings")
        return db
    }

}