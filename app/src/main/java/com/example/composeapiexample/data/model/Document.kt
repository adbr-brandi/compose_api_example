package com.example.composeapiexample.data.model

data class Document(
    val collection: String,
    val datetime: String,
    val displaySitename: String?,
    val docURL: String,
    val height: Double,
    val imageURL: String,
    val thumbnailURL: String?,
    val width: Double,
) {
    // TODO: retrofit 자체적으로 시리얼라이즈 하는 어뎁터가 있다고 함
    companion object {
        fun fromJson(json: Map<String, Any>): Document = Document(
            collection = json["collection"] as String,
            datetime = json["datetime"] as String,
            displaySitename = json["display_sitename"] as String,
            docURL = json["doc_url"] as String,
            height = json["height"] as Double,
            imageURL = json["image_url"] as String,
            thumbnailURL = json["thumbnailURL"] as String?,
            width = json["width"] as Double,
        )


    }
}
