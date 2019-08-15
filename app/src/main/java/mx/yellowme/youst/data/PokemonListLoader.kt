package mx.yellowme.youst.data;

import mx.yellowme.youst.presentation.challenges.ChallengeWithListActivity
import mx.yellowme.youst.core.extensions.toast
import mx.yellowme.youst.pokemon.index.PokemonListViewHolder
import mx.yellowme.youst.core.domain.Pokemon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * TODO: Add javadoc
 */
object PokemonListLoader {
    fun loadData(view: ChallengeWithListActivity<Pokemon, PokemonListViewHolder>) {
        val service = APIServiceGenerator.createService(PokemonAPIService::class.java)
        val call = service.getAll(HashMap())

        view.setState(ChallengeWithListActivity.ListState.LOADING)
        call.enqueue(object : Callback<MutableList<Pokemon>> {
            override fun onResponse(
                    call: Call<MutableList<Pokemon>>, response: Response<MutableList<Pokemon>>
            ) {
                view.setState(ChallengeWithListActivity.ListState.LOADED)
                if (response.isSuccessful) {
                    view.display(response.body())
                }
            }

            override fun onFailure(
                    call: Call<MutableList<Pokemon>>, t: Throwable
            ) {
                view.setState(ChallengeWithListActivity.ListState.LOADED)
                view.toast("Error")
            }
        })
    }
}
