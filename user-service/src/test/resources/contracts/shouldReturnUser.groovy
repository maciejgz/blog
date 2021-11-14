package contracts

import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpStatus

Contract.make {
    description "getUser: Should return user1"
    request {
        method("GET")
        url("/user/user1")
    }
    response {
        status HttpStatus.OK.value()
        body(["username": "user1", "password": "pass1"])
        headers {
            contentType("application/json")
        }
    }
}