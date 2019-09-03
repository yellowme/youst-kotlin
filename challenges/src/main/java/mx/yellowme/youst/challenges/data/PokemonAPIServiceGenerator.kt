package mx.yellowme.youst.challenges.data

import mx.yellowme.youst.challenges.BuildConfig
import mx.yellowme.youst.core.data.BaseAPIServiceGenerator

//TODO: Extract String URL
class PokemonAPIServiceGenerator: BaseAPIServiceGenerator(BuildConfig.POKEMON_API_URL)
