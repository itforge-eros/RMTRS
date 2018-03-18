package th.ac.kmitl.it.rmtrs.model

import org.hibernate.annotations.SQLDelete
import th.ac.kmitl.it.rmtrs.definition.Role
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
@SQLDelete(sql = "UPDATE employee SET is_active = false WHERE id = ?")
data class Employee(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        var username: String = "default",
        var password: String = "default",
        var title: String = "default",
        var name: String = "default",
        var surname: String = "default",
        var phone: String = "default",
        var role: Role = Role.STAFF
): BaseModel()