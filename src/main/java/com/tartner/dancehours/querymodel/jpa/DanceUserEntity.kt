package com.tartner.dancehours.querymodel.jpa

import com.google.common.base.Objects
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "dance_user", schema = "public", catalog = "dance_hours")
public class DanceUserEntity {
    @Id @Type(type = "pg-uuid")
    @Column(name = "user_id", nullable = false, insertable = true, updatable = true)
    public var userId: UUID? = null

    @Basic
    @Column(name = "email", nullable = false, insertable = true, updatable = true, length = 254)
    public var email: String? = null

    @Basic
    @Column(name = "first_name", nullable = true, insertable = true, updatable = true, length = 255)
    public var firstName: String? = null

    @Basic
    @Column(name = "last_name", nullable = true, insertable = true, updatable = true, length = 255)
    public var lastName: String? = null

    @Basic
    @Column(name = "is_active", nullable = false, insertable = true, updatable = true)
    public var isActive: Boolean = false

    @OneToMany(mappedBy="dance_user_entity", targetEntity = javaClass<UserRolesEntity>())
    @JoinTable(name = "dance_user_roles", joinColumns = arrayOf(JoinColumn(name = "user_id")),
        inverseJoinColumns = arrayOf(JoinColumn(name = "role_id")))
    public var roles: Set<UserRolesEntity> = HashSet<UserRolesEntity>()

    override fun hashCode(): Int {
        return Objects.hashCode(userId)
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {return true }
        if (o == null || javaClass != o.javaClass) { return false }
        val that = o as DanceUserEntity
        return Objects.equal(userId, that.userId)
    }
}
