package mx.yellowme.youst.challenges.pokemon

import mx.yellowme.youst.core.data.APIServiceGenerator
import mx.yellowme.youst.core.data.PokemonAPIService
import mx.yellowme.youst.core.domain.Pokemon
import mx.yellowme.youst.challenges.pokemon.index.PokemonListViewHolder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * TODO: Add javadoc
 */
object PokemonListLoader {
    fun loadData(view: mx.yellowme.youst.challenges.ChallengeWithListActivity<Pokemon, PokemonListViewHolder>) {
        val service = APIServiceGenerator.createService(PokemonAPIService::class.java)
        val call = service.getAll(HashMap())

        view.setState(mx.yellowme.youst.challenges.ChallengeWithListActivity.ListState.LOADING)
        call.enqueue(object : Callback<MutableList<Pokemon>> {
            override fun onResponse(
                call: Call<MutableList<Pokemon>>, response: Response<MutableList<Pokemon>>
            ) {
                view.setState(mx.yellowme.youst.challenges.ChallengeWithListActivity.ListState.LOADED)
                if (response.isSuccessful) {
                    view.display(response.body())
                }
            }

            override fun onFailure(call: Call<MutableList<Pokemon>>, t: Throwable) {
                view.setState(mx.yellowme.youst.challenges.ChallengeWithListActivity.ListState.LOADED)
                view.toast("Error")
            }
        })
    }
}