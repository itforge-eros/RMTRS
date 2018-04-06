package th.ac.kmitl.it.rmtrs.model

import com.fasterxml.jackson.annotation.JsonProperty
import th.ac.kmitl.it.rmtrs.definition.Role
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotNull

@Entity
data class Account(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        @NotNull
        var username: String = "default",

        @NotNull
        var password: String = "default",

        @NotNull
        var title: String = "default",

        @NotNull
        var fname: String = "default",

        @NotNull
        var lname: String = "default",

        @NotNull
        var phone: String = "default",

        @NotNull
        var role: Role
): BaseModel()