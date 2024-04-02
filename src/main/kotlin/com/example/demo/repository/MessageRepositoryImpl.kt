package com.example.demo.repository

import com.example.demo.dao.MessageDAO
import com.example.demo.dao.models.MessageModel
import com.example.demo.dto.CreateMessageDto
import com.example.demo.dto.ListMessageDto
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import kotlin.reflect.full.memberProperties


@Repository
class MessageRepositoryImpl(private val messageDao: MessageDAO) : MessageRepository {
    @PersistenceContext
    lateinit var entityManager: EntityManager;
    override fun getById(id: String): MessageModel = messageDao.getReferenceById(id);

    override fun list(listMessageDto: ListMessageDto, pageable: Pageable): Page<MessageModel?> {
        val cb: CriteriaBuilder = entityManager.criteriaBuilder;
        val query: CriteriaQuery<MessageModel> = cb.createQuery(MessageModel::class.java)
        val root: Root<MessageModel> = query.from(MessageModel::class.java)

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
        val resultList: List<MessageModel> = typedQuery.resultList


        return PageImpl(resultList, pageable, resultList.size.toLong());
    }

    override fun deleteById(id: String) {
        TODO("Not yet implemented")
    }

    override fun create(createMessageDto: CreateMessageDto): MessageModel = messageDao.save(
        MessageModel(
            firstName = createMessageDto.firstName,
            lastName = createMessageDto.lastName,
            email = createMessageDto.email,
            balance = createMessageDto.balance
        )
    );

}