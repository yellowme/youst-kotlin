package mx.yellowme.youst.challenges.data

import mx.yellowme.youst.challenges.domain.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

/**
 * ## List All
 *
 * - /pokemons - To list all pokemons
 * - /pokemons?types=FIRE - Search by type
 * - /pokemons?name=Charmander - Search by exact name
 * - /pokemons?name_like=charm - Search by name using LIKE modifier
 * - /pokemons?types=FIRE&name_like=charm - Search by type and name using LIKE modifier
 *
 * ## Single Pokemon
 *
 * - /pokemons/{id} - To get a specific pokemon details
 */
interface PokemonAPIService {
    @GET("/pokemons")
    fun getAll(@QueryMap options: Map<String, String>): Call<MutableList<Pokemon>>

    @GET("/pokemons/{id}")
    fun getById(@Path("id") id: String): Call<Pokemon>
}
