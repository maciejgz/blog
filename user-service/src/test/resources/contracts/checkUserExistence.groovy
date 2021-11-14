package contracts

import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpStatus

Contract.make {
    description "getUser: check user1 existence"
    request {
        method("HEAD")
        url("/user/user1")
    }
    response {
        status HttpStatus.OK.value()
    }
}