package com.example.dev_agro.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import okhttp3.MultipartBody
import retrofit2.http.*

// -----------------------------
// Common
// -----------------------------
@Serializable
data class ApiMessage(
    val message: String? = null
)

@Serializable
enum class ProductTypeEnum {
    @SerialName("DAIRY") DAIRY,
    @SerialName("MEAT") MEAT,
    @SerialName("EGG") EGG,
    @SerialName("LEATHER") LEATHER
}

// -----------------------------
// USER
// -----------------------------
interface UserApiService {

    // POST /api/user
    @POST("api/user")
    suspend fun createUser(
        @Body body: CreateUserRequest
    ): UserDto

    // PUT /api/user/:userId
    @PUT("api/user/{userId}")
    suspend fun updateUser(
        @Path("userId") userId: String,
        @Body body: UpdateUserRequest
    ): UserDto

    // GET /api/user/me
    @GET("api/user/me")
    suspend fun getMe(): UserDto
}

@Serializable
data class CreateUserRequest(
    val email: String,
    val password: String,
    val name: String? = null
)

@Serializable
data class UpdateUserRequest(
    val name: String? = null
)

@Serializable
data class UserDto(
    val id: String? = null,
    val email: String,
    val name: String? = null
)

// -----------------------------
// PRODUCT
// -----------------------------
interface ProductApiService {

    // POST /api/product/:farmId  (multipart, pictures[])
    // Using String parts is simpler; Retrofit will form-data encode them.
    @Multipart
    @POST("api/product/{farmId}")
    suspend fun createProduct(
        @Path("farmId") farmId: String,
        @Part("name") name: String,
        @Part("type") type: String,              // pass enum.name, e.g. ProductTypeEnum.DAIRY.name
        @Part("description") description: String,
        @Part pictures: List<MultipartBody.Part> = emptyList()
    ): GetProductDto

    // PUT /api/product/:productId
    @PUT("api/product/{productId}")
    suspend fun updateProduct(
        @Path("productId") productId: String,
        @Body body: UpdateProductRequest
    ): GetProductDto

    // GET /api/product/:productId
    @GET("api/product/{productId}")
    suspend fun getProductById(
        @Path("productId") productId: String
    ): GetProductDto

    // GET /api/product/lastproductscreated
    @GET("api/product/lastproductscreated")
    suspend fun getLastProductsCreated(): List<GetProductDto>
}

@Serializable
data class UpdateProductRequest(
    val name: String? = null,
    val description: String? = null,
    val type: ProductTypeEnum? = null
)

@Serializable
data class SearchProductRequest(
    val name: String? = null,
    val search: String? = null,
    val type: List<ProductTypeEnum>? = null,
    val limit: Int? = null,
    val skip: Int? = null
)

@Serializable
data class GetProductDto(
    val id: String? = null,
    val name: String,
    val description: String? = null,
    val type: ProductTypeEnum,
    val farm: String? = null,
    val pictures: List<GetPictureDetailDto> = emptyList()
)

@Serializable
data class GetPictureDetailDto(
    val key: String,
    val name: String,
    val url: String
)

@Serializable
data class PaginatedProductsResponse(
    val items: List<GetProductDto> = emptyList(),
    val total: Int? = null
)

// -----------------------------
// FARM
// -----------------------------
interface FarmApiService {

    // POST /api/farm
    @POST("api/farm")
    suspend fun createFarm(
        @Body body: CreateFarmRequest
    ): FarmDto
}

@Serializable
data class CreateFarmRequest(
    val name: String,
    val location: String? = null
)

@Serializable
data class FarmDto(
    val id: String? = null,
    val name: String,
    val location: String? = null
)

// -----------------------------
// AGENT (AI) - /api/v1/agent/*
// -----------------------------
interface AgentApiService {

    // POST /api/v1/agent/describe-image
    @POST("api/v1/agent/describe-image")
    suspend fun describeImage(@Body body: AgentImageRequest): AgentTextResponse

    // POST /api/v1/agent/analyze-agriculture
    @POST("api/v1/agent/analyze-agriculture")
    suspend fun analyzeAgriculture(@Body body: AgentTextRequest): AgentTextResponse

    // POST /api/v1/agent/social-media-captions
    @POST("api/v1/agent/social-media-captions")
    suspend fun socialMediaCaptions(@Body body: AgentTextRequest): AgentTextResponse

    // POST /api/v1/agent/marketing-description
    @POST("api/v1/agent/marketing-description")
    suspend fun marketingDescription(@Body body: AgentTextRequest): AgentTextResponse

    // POST /api/v1/agent/veterinary-diagnosis
    @POST("api/v1/agent/veterinary-diagnosis")
    suspend fun veterinaryDiagnosis(@Body body: AgentTextRequest): AgentTextResponse

    // POST /api/v1/agent/health-check
    @POST("api/v1/agent/health-check")
    suspend fun healthCheck(@Body body: AgentTextRequest = AgentTextRequest("")): AgentTextResponse
}

@Serializable
data class AgentImageRequest(
    val imageUrl: String,
    val prompt: String? = null
)

@Serializable
data class AgentTextRequest(
    val prompt: String
)

@Serializable
data class AgentTextResponse(
    val result: String? = null,
    val data: String? = null
)

// -----------------------------
// MISC (Ping root)
// -----------------------------
interface AppApiService {
    // GET /api
    @GET("api")
    suspend fun ping(): ApiMessage
}
