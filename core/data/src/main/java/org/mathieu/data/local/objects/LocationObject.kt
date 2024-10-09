package org.mathieu.data.local.objects

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Ignore
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mathieu.data.remote.responses.CharacterResponse
import org.mathieu.data.remote.responses.LocationResponse
import org.mathieu.domain.models.character.Character
import org.mathieu.domain.models.location.*

/**
 * Represents a location entity stored in the SQLite database. This object provides fields
 * necessary to represent all the attributes of a location from the data source.
 * The object is specifically tailored for SQLite storage using Realm.
 *
 * @property id Unique identifier of the location.
 * @property name Name of the location.
 * @property type Type of the location.
 * @property dimension Dimension of the location.
 * @property residents List of character who have been last seen in the location.
 * @property url Link to the location's own endpoint.
 * @property created Time at wich the location was created in the database.
 */
internal class LocationObject: RealmObject {
    @PrimaryKey
    var id: Int = -1
    var name: String = ""
    var type: String = ""
    var dimension: String = ""
    @Ignore
    var residents: List<String> = emptyList()
    var url: String = ""
    var created: String = ""
}

internal fun LocationResponse.toRealmObject() = LocationObject().also { obj ->
    obj.id = id
    obj.name = name
    obj.type = type
    obj.dimension = dimension
    obj.residents = residents
    obj.url = url
    obj.created = created
}

internal fun LocationObject.toModel() = Location(
    id = id,
    name = name,
    type = type,
    dimension = dimension,
    residents = emptyList()
)