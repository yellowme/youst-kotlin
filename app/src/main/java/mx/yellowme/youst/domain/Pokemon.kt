package mx.yellowme.youst.domain;

/**
 * Main application domain model.
 */
class Pokemon {
    var id: String? = null
    var types: List<PokemonType>? = null
    var region: String? = null
    var generation: String? = null
    var imageUrl: String? = null
    var name: String? = null
}
