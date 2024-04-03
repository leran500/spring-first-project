package com.example.demo.repository

import com.example.demo.dao.UserDAO
import com.example.demo.dao.models.UserModel
import com.example.demo.dto.CreateUserDto
import com.example.demo.dto.ListUserDto
import com.example.demo.dto.UpdateUserDto
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root
import org.apache.logging.log4j.message.Message
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import kotlin.reflect.full.memberProperties


@Repository
class UserRepositoryImpl(private val userDao: UserDAO) : UserRepository {
    @PersistenceContext
    lateinit var entityManager: EntityManager;
    override fun getById(id: String): UserModel = userDao.getReferenceById(id);

    override fun list(listMessageDto: ListUserDto, pageable: Pageable): Page<UserModel?> {
        val cb: CriteriaBuilder = entityManager.criteriaBuilder;
        val query: CriteriaQuery<UserModel> = cb.createQuery(UserModel::class.java)
        val root: Root<UserModel> = query.from(UserModel::class.java)

        val predicates = mutableListOf<Predicate>()
        listMessageDto::class.memberProperties.forEach { prop ->
            if (prop.getter.call(listMessageDto) != null) {
                predicates.add(cb.equal(root.get<String>(prop.name), prop.getter.call(listMessageDto)))
            }
        }

        query.select(root).where(*predicates.toTypedArray())

        // Create sorting
        if (pageable.sort.isSorted) {
            val orders = pageable.sort.toList()
            query.orderBy(orders.map { order ->
                if (order.isAscending) cb.asc(root.get<String>(order.property))
                else cb.desc(root.get<String>(order.property))
            })
        }

        val typedQuery = entityManager.createQuery(query)
        // Apply pagination
        typedQuery.firstResult = pageable.offset.toInt()
        typedQuery.maxResults = pageable.pageSize
        val resultList: List<UserModel> = typedQuery.resultList


        return PageImpl(resultList, pageable, resultList.size.toLong());
    }

    override fun updateUser(id: String, updateUserDto: UpdateUserDto): UserModel = userDao.save(     UserModel(
        firstName = updateUserDto.firstName,
        lastName = updateUserDto.lastName,
        email = updateUserDto.email,
        balance = updateUserDto.balance
    ))

    override fun create(createMessageDto: CreateUserDto): UserModel = userDao.save(
        UserModel(
            firstName = createMessageDto.firstName,
            lastName = createMessageDto.lastName,
            email = createMessageDto.email,
            balance = createMessageDto.balance
        )
    );

}