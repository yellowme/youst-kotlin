package mx.yellowme.youst.core.data;

import mx.yellowme.youst.core.domain.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

/**
 * <h1>List Pokemons</h1>
 * <ul>
 * <li>/pokemons - To list all pokemons</li>
 * <li>/pokemons?types=FIRE - Search by type</li>
 * <li>/pokemons?name=Charmander - Search by exact name</li>
 * <li>/pokemons?name_like=charm - Search by name using LIKE modifier</li>
 * <li>/pokemons?types=FIRE&name_like=charm - Search by type and name using LIKE modifier</li>
 * </ul>
 *
 * <h1>Single Pokemon</h1>
 * <ul>
 * <li>/pokemons/{id} - To get a specific pokemon details</li>
 * </ul>
 * <p>
 */
interface PokemonAPIService {
    @GET("/pokemons")
    fun getAll(@QueryMap options: Map<String, String>): Call<MutableList<Pokemon>>

    @GET("/pokemons/{id}")
    fun getById(@Path("id") id: String): Call<Pokemon>
}
