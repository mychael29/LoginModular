package com.kimochisoft.business.user.entity


data class User (val objectId: String,
                 var avatar: String,
                 var nickname: String,
                 var socialAccount: String?,
                 var lastLogin: String?,
                 var ownerId: String?,
                 var displayName: String?) {

    override fun toString(): String {
        return "User {\n\tname = $displayName,\n\tobjectId = $objectId,\n\tavatar = '$avatar',\n\tsocialAccount = '$socialAccount'\n}"
    }
}