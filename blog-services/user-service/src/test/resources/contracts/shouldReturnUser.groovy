package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "Should return user1"
    request {
        method("GET")
        url("/user/user1")
    }
    response {
        status 200
        body(["username": "user1", "password": "pass1"])
        headers {
            contentType("application/json")
        }
    }
}