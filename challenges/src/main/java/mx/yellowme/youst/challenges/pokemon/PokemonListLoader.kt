package mx.yellowme.youst.challenges.pokemon

import mx.yellowme.youst.challenges.common.ChallengeWithListActivity
import mx.yellowme.youst.challenges.components.GenericListModel
import mx.yellowme.youst.challenges.components.GenericListState
import mx.yellowme.youst.challenges.data.PokemonAPIService
import mx.yellowme.youst.challenges.data.PokemonAPIServiceGenerator
import mx.yellowme.youst.challenges.domain.Pokemon
import mx.yellowme.youst.challenges.pokemon.index.PokemonListViewHolder
import mx.yellowme.youst.core.extensions.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * TODO: Add javadoc
 */
object PokemonListLoader {
    fun loadData(view: ChallengeWithListActivity<Pokemon, PokemonListViewHolder>) {
        val service = PokemonAPIServiceGenerator().createService(PokemonAPIService::class.java)
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

            override fun onFailure(call: Call<MutableList<Pokemon>>, t: Throwable) {
                view.setState(ChallengeWithListActivity.ListState.LOADED)
                view.toast("Error")
            }
        })
    }

    fun loadData(view: GenericListModel<Pokemon, PokemonListViewHolder>) {
        val service = PokemonAPIServiceGenerator().createService(PokemonAPIService::class.java)
        val call = service.getAll(HashMap())

        view.setState(GenericListState.LOADING)
        call.enqueue(object : Callback<MutableList<Pokemon>> {
            override fun onResponse(
                call: Call<MutableList<Pokemon>>, response: Response<MutableList<Pokemon>>
            ) {
                view.setState(GenericListState.LOADED)
                if (response.isSuccessful) {
                    view.display(response.body())
                }
            }

            override fun onFailure(call: Call<MutableList<Pokemon>>, t: Throwable) {
                view.setState(GenericListState.LOADED)
            }
        })
    }
}
