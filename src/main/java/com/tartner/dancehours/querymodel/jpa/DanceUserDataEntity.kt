package com.tartner.dancehours.querymodel.jpa

import com.google.common.base.Objects
import com.tartner.utilities.Empty
import com.tartner.utilities.emptyUUID
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "dance_user", schema = "public", catalog = "dance_hours")
public class DanceUserDataEntity {
    @Id @Type(type = "pg-uuid")
    @Column(name = "user_id", nullable = false, insertable = true, updatable = true)
    public var userId: UUID = emptyUUID()

    @Basic
    @Column(name = "email", nullable = false, insertable = true, updatable = true, length = 254)
    public var email: String = String.Empty

    @Basic
    @Column(name = "first_name", nullable = true, insertable = true, updatable = true, length = 255)
    public var firstName: String = String.Empty

    @Basic
    @Column(name = "last_name", nullable = true, insertable = true, updatable = true, length = 255)
    public var lastName: String = String.Empty

    @Basic
    @Column(name = "is_active", nullable = false, insertable = true, updatable = true)
    public var isActive: Boolean = false

    @JoinTable(name = "dance_user_roles", joinColumns = arrayOf(JoinColumn(name = "user_id")),
        inverseJoinColumns = arrayOf(JoinColumn(name = "role_id")))
    @OneToMany(mappedBy="dance_user_entity", targetEntity = UserRolesEntity::class)
    public var roles: Set<UserRolesEntity> = HashSet<UserRolesEntity>()

    override fun hashCode(): Int {
        return Objects.hashCode(userId)
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {return true }
        if (o == null || javaClass != o.javaClass) { return false }
        val that = o as DanceUserDataEntity
        return Objects.equal(userId, that.userId)
    }
}
