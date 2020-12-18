package com.srisuk.computerrepair.data.datasource

import com.srisuk.computerrepair.data.database.Users
import com.srisuk.computerrepair.data.request.LoginRequest
import com.srisuk.computerrepair.data.response.LoginResponse
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object DataSourceImpl : DataSource {
    override fun login(req: LoginRequest): LoginResponse {
        val response = LoginResponse()

        if (req.username.isBlank()) {
            response.message = "Username empty"
        } else if (req.password.isBlank()) {
            response.message = "Password empty"
        } else {
            val result = transaction {
                addLogger(StdOutSqlLogger)

                Users.select { Users.username eq req.username }
                    .andWhere { Users.password eq req.password }
                    .count()
                    .toInt()
//                    .map { MapObject.toUser(it) }
//                    .single()
            }

            if (result == 0) {
                response.success = false
                response.message = "Password incorrect"
            } else {
                response.success = true
                response.message = "Login success"
            }
        }

        return response
    }
}